package com.we.springboot.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;


/**
 * email 测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailTests {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.from}")
    private String from;

    @Test
    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        // 接收地址
        message.setTo("2441421223@qq.com");
        // 标题
        message.setSubject("一封简单的邮件");
        // 内容
        message.setText("使用Spring Boot发送简单邮件。");
        javaMailSender.send(message);

    }

    @Test
    public void sendHtmlEmail() {
        MimeMessage message;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("2441421223@qq.com");
            helper.setSubject("一封HTML格式的邮件");
            // 带HTML格式的内容
            helper.setText("<p style='color:#42b983'>使用Spring Boot发送HTML格式邮件。</p>", true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendAttachmentsMail() {
        MimeMessage message;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("2441421223@qq.com");
            helper.setSubject("一封带附件的邮件");
            helper.setText("详情参见附件内容！");
            // 传入附件
            ClassPathResource resource = new ClassPathResource("/file/email.docx");
            helper.addAttachment("email.docx", resource.getFile());
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendInlineMail() {
        MimeMessage message;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("2441421223@qq.com");
            helper.setSubject("一封带静态资源的邮件");
            helper.setText("<html><body>pic：<img src='cid:img'/></body></html>", true);
            // 传入附件
            ClassPathResource resource = new ClassPathResource("/static/img.jpg");
            helper.addInline("img", resource.getFile());
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendTemplateEmail() {
        MimeMessage message;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("2441421223@qq.com");
            helper.setSubject("摸板测试");
            // 处理邮件模板
            Context context = new Context();
            context.setVariable("code", "code");
            String template = templateEngine.process("emailTemplate", context);
            helper.setText(template, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
