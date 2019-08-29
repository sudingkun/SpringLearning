package com.we.springboot.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.we.springboot.shiro.bean.User;
import lombok.Setter;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * @author sudingkun
 */
@Setter
public class KickoutSessionControlFilter extends AccessControlFilter {

    private static final String KICKOUT_SESSION = "kickout";

    private static final String AJAX_REQUEST = "XMLHttpRequest";

    private static final String REQUEST_HEADER = "X-Requested-With";

    /**
     * 踢出后到的地址
     */
    private String kickoutUrl;
    /**
     * false踢出之前登录的
     * true踢出之后登录的
     */
    private boolean kickoutAfter = false;
    /**
     * 同一个帐号最大会话数
     */
    private int maxSession = 1;

    private SessionManager sessionManager;

    private Cache<String, Deque<Serializable>> cache;

    /**
     * 设置Cache的key的前缀
     */
    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro_login_cache");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        //如果没有登录，直接进行之后的流程
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return true;
        }

        User user = (User) subject.getPrincipal();
        String username = user.getUsername();
        Session session = subject.getSession();
        Serializable sessionId = session.getId();

        // 初始化用户的队列放到缓存里
        Deque<Serializable> deque = cache.get(username);
        if (deque == null) {
            deque = new LinkedList<>();
            cache.put(username, deque);
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!deque.contains(sessionId) && session.getAttribute(KICKOUT_SESSION) == null) {
            //将sessionId存入队列
            deque.push(sessionId);
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            //判断踢出后者还是前者
            kickoutSessionId = kickoutAfter ? deque.removeFirst() : deque.removeLast();
            //踢出后再更新下缓存队列
            cache.put(username, deque);

            //获取被踢出的sessionId的session对象
            Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
            if (kickoutSession != null) {
                //设置会话的kickout属性表示踢出了
                kickoutSession.setAttribute(KICKOUT_SESSION, true);
            }
        }

        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute(KICKOUT_SESSION) != null) {
            //退出登录
            subject.logout();
            saveRequest(request);

            Map<String, String> resultMap = new HashMap<>(2);
            //判断是不是Ajax请求
            if (AJAX_REQUEST.equalsIgnoreCase(((HttpServletRequest) request).getHeader(REQUEST_HEADER))) {
                resultMap.put("code", "300");
                resultMap.put("msg", "您已经在其他地方登录，请重新登录！");
                //输出json串
//                out(response, resultMap);
                WebUtils.toHttp(response).sendError(300,"您已经在其他地方登录，请重新登录！");
            } else {
                //重定向
                WebUtils.issueRedirect(request, response, kickoutUrl);
            }
            return false;
        }
        return true;
    }

    private void out(ServletResponse response, Map<String, String> resultMap) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(resultMap));
            out.flush();
            out.close();
        } catch (Exception e) {
            System.err.println("KickoutSessionFilter.class 输出JSON异常，可以忽略。");
        }
    }
}