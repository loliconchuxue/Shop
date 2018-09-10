package com.lcz.shop.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
    public static final String HOST = "smtp.qq.com";
    public static final String PROTOCOL = "smtp";   
    public static final int PORT = 25;
    public static final String FROM = "1763892585@qq.com";//发件人的email
    public static final String PWD = "gppygtvjcqgidcdc";//这里设置的是发件人的授权码
	
	
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.transport.protocol" , PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth" , true);
		//设置发送的协议
		//props.setProperty("mail.transport.protocol", "SMTP");
		
		//设置发送邮件的服务器
		//props.setProperty("mail.host", "smtp.126.com");
		//props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//设置发送人的帐号和密码
				return new PasswordAuthentication(FROM, PWD);
			}
		};

		Session session = Session.getInstance(props, auth);
		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		//设置发送者
		message.setFrom(new InternetAddress(FROM));
		InternetAddress[] address = {new InternetAddress(email)};
		//设置发送方式与接收者
		message.setRecipients(Message.RecipientType.TO, address); 

		//设置邮件主题
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");
		message.setSentDate(new Date());
		//设置邮件内容
			//在这里首先要将邮件发送成功之后，该url才有用
		//String url="http://localhost:8080/store_v5/UserServlet?method=active&code="+emailMsg;
		String msg="<h1>验证码:  "+emailMsg+"</h1>";
		//String content="<h1>来自购物天堂的激活邮件!激活请点击以下链接!</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
		message.setContent(msg, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送
	    /*Transport transport = session.getTransport();
		transport.connect();
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();*/
		System.out.println("万里长征最后一步了");
		//在发送时，速度比较慢
		Transport.send(message);
	}
}
