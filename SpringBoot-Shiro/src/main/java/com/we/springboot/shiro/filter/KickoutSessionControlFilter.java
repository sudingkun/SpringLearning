package com.we.springboot.shiro.filter;

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
import java.io.IOException;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;


/**
 * @author sudingkun
 */
@Setter
public class KickoutSessionControlFilter extends AccessControlFilter {

    private static final String KICKOUT_SESSION = "kickout";

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
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!deque.contains(sessionId) && session.getAttribute(KICKOUT_SESSION) == null) {
            //将sessionId存入队列
            deque.push(sessionId);
            //将用户的sessionId队列缓存
            cache.put(username, deque);
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > maxSession) {
            //判断踢出后者还是前者
            Serializable kickoutSessionId = kickoutAfter ? deque.removeFirst() : deque.removeLast();
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
            //重定向
            WebUtils.issueRedirect(request, response, kickoutUrl);
            return false;
        }
        return true;
    }

}