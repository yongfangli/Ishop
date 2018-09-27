package com.thinkgem.jeesite.modules.postManeger.message;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Max;

import org.apache.commons.mail.Email;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.sun.tools.corba.se.idl.StringGen;
import com.thinkgem.jeesite.common.config.EmailConfig;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;

@Service
public class EmailMessageService {

	private Properties prop;
	final String CODEKEY = "CODE";

	private Transport ts;

	private Session session;
	private MimeMessage message;
	private final static String DEFAULT_CONTENTTYPE = "text/html;charset=UTF-8";
	private String code;

	public void initSession(boolean debug) {
		Properties prop = new Properties();
		prop.setProperty(EmailConfig.SERVER_ADDRESS_KEY, EmailConfig.getServerAddress());
		prop.setProperty(EmailConfig.PROTOCAL_NAME_KEY, EmailConfig.getProtocalName());
		prop.setProperty(EmailConfig.AUTH_SET_KEY, EmailConfig.getAuthSetting());
		prop.put(EmailConfig.ENABLE_PSW, EmailConfig.getEnablepsw());
		session = Session.getInstance(prop);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(debug);
	}

	private void getTransport() {
		try {
			ts = session.getTransport();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			getTransport();
			ts.connect(EmailConfig.getServerAddress(), EmailConfig.getUserName(), EmailConfig.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MimeMessage createSimpleMail(String from, String to) throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress(from));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		// 邮件的标题
		message.setSubject("只包含文本的简单邮件");
		// 邮件的文本内容
		message.setContent("你好啊！", "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}

	public void sendTest(boolean debug) {
		initSession(debug);
		connect();
		try {
			buildMessage("1594188122@qq.com", new String[] { "1594188122@qq.com" }, "注册验证码", createRegisterContent());
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initMessage() {
		message = new MimeMessage(session);
	}

	private void buildFrom(String from) throws MessagingException {
		message.setFrom(new InternetAddress(from));
	}

	private void buildTo(String[] to) throws MessagingException {
		InternetAddress[] addresses = new InternetAddress[to.length];
		int i = 0;
		for (String toa : to) {
			addresses[i] = new InternetAddress(toa);
			i++;
		}
		message.setRecipients(Message.RecipientType.TO, addresses);
	}

	private void buildTitle(String title) throws MessagingException {
		message.setSubject(title);
	}

	private void buildContent(String content) throws MessagingException {
		message.setContent(content, DEFAULT_CONTENTTYPE);
	}

	/**
	 * @param from
	 * @param to
	 * @param title
	 * @param content
	 * @throws MessagingException
	 */
	public void buildMessage(String from, String[] to, String title, String content) throws MessagingException {
		initMessage();
		buildFrom(from);
		buildTo(to);
		buildTitle(title);
		buildContent(content);
	}

	public String createRegisterContent() {
		String code = "";
		int i = (int) (Math.random() * 100000 + 99999);
		StringBuffer b = new StringBuffer();
		setCode(String.valueOf(i));
		b.append("<!DOCTYPE html>");
		b.append("<html><head>");
		b.append("<title>注册校验码</title>");
		b.append("<meta charset=\"utf-8\">");
		b.append("<meta content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\" name=\"viewport\">");
		b.append("</head><body style=\"font-size: 0.9rem;background-color: #f8fbc8;color: #5d5a5a;\">");
		b.append("<div>");
		b.append("<p>感谢您注册该网站，本次注册验证码为:<span style=\"border-radius: 3px;background: #f9f931;color: #82827b;\">");
		b.append(i);
		b.append("</span>，验证码在2分钟之内有效!</p></div>");
		b.append("</body></html>");
		return b.toString();

	}

	public static void main(String[] arg) {
		int i = (int) (Math.random() * 100000 + 99999);
		System.err.println(String.valueOf(i));
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
