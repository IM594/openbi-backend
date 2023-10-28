package com.elec5619.bi.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class EmailNotificationService {

    public void sendNotificationEmail(String recipientEmail, String subject, String content) throws MessagingException {
        // 邮件服务器配置
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.office365.com"); // Gmail SMTP 主机名
        properties.put("mail.smtp.port", "587"); // 使用 SSL 的端口号
        properties.put("mail.smtp.auth", "true"); // 启用身份验证
        properties.put("mail.smtp.starttls.enable", "true"); // 启用 SSL 加密

        // 发送者邮箱认证信息
        final String senderEmail = "hmlmtest2023@outlook.com";
        final String senderPassword = "2023test";

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // 创建邮件
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject(subject);
        message.setText(content);

        // 发送邮件
        Transport.send(message);
    }

    // 发送邮件给管理员
    public boolean sendEmailToAdmin(String userName, String email, String content, String recipientEmail) throws MessagingException {

        // 邮件服务器配置
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.office365.com"); // Gmail SMTP 主机名
        properties.put("mail.smtp.port", "587"); // 使用 SSL 的端口号
        properties.put("mail.smtp.auth", "true"); // 启用身份验证
        properties.put("mail.smtp.starttls.enable", "true"); // 启用 SSL 加密

        // 发送者邮箱认证信息
        final String senderEmail = "hmlmtest2023@outlook.com";
        final String senderPassword = "2023test";

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // 创建邮件
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject(userName + " contact you");
        message.setText("The user " + userName + " with email " + email + " contact you, the message is " + content + ", please reply as soon as possible.");

        // 发送邮件
        Transport.send(message);

        // 返回发送成功
        return true;
    }

    // 其他邮件服务相关方法...
}

