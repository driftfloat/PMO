package com.pmo.dashboard.entity;

import java.security.GeneralSecurityException;
import java.util.Properties;

import com.sun.mail.util.MailSSLSocketFactory;


/**
 * 邮件发送基本信息
 * @author Devin
 * @since 2018-3-26
 *
 */
public class MailBaseInfo {
	//发送邮件的服务器IP和端口
    private String mailServerHost;
    private String mailServerPort="25";
    //邮件发送者的地址
    private String sendAddress;
    //邮件接收者的地址
    private String acceptAddress;
    //登录邮件发送服务器的用户名和密码
    private String userName;
    private String password;
    //是否需要身份验证
    private boolean validate=false;
    //邮件主题
    private String subject;
    //邮件文本内容
    private String context;
    //邮件属性
    public Properties getProperties() throws GeneralSecurityException
    {
       Properties p=new Properties();
       MailSSLSocketFactory sf = new MailSSLSocketFactory();
       sf.setTrustAllHosts(true);
       //开始赋值
       p.put("mail.smtp.host",this.mailServerHost);
       p.put("mail.smtp.port",this.mailServerPort);
       p.put("mail.smtp.auth","true"); 
       p.put("mail.smtp.ssl.enable", "true");
       p.put("mail.smtp.ssl.socketFactory", sf);
       //返回信息
       return p;
    }
	public String getMailServerHost() {
		return mailServerHost;
	}
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}
	public String getMailServerPort() {
		return mailServerPort;
	}
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}
	public String getSendAddress() {
		return sendAddress;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	public String getAcceptAddress() {
		return acceptAddress;
	}
	public void setAcceptAddress(String acceptAddress) {
		this.acceptAddress = acceptAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
    
}
