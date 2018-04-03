package com.pmo.dashboard.util;


import com.pmo.dashboard.entity.MailBaseInfo;


/**
 * 邮件发送工具类
 * @author Devin
 * @since 2018-3-26
 *
 */
public class SendUtil {
       
	   
	   //邮件发送类型
	   public static final String type = "smtp.qq.com";
	   //邮件发送端口
	   public static final String port = "465";
	   //是否验证身份
	   public static final boolean validate = true;
	   //用户名
	   public static final String username = "pmo001@qq.com";
	   //密码
	   public static final String pwd = "yhysknykewbqdicc";
	   //邮件发送者
	   public static final String sendAddress = "pmo001@qq.com";
	   
	   private static MailBaseInfo mi = new MailBaseInfo();
	   
	   private static SendEmailUtil sms = new SendEmailUtil();
	
	   public static boolean send(String accept,String subject,String context){
		       
	        try{
	        	   /**
			        * 发送基本设置
			        */
			       mi.setMailServerHost(type);
			       mi.setMailServerPort(port);
			       mi.setValidate(validate);
			       mi.setUserName(username);
			       mi.setPassword(pwd);
			       mi.setSendAddress(sendAddress);	   
			   
		           //设置邮件接收者
		           mi.setAcceptAddress(accept);
		           //设置邮件主题
		           mi.setSubject(subject);
		           //设置发送内容
		           mi.setContext(context);
		         
		           //发送
		           sms.send(mi);
		           System.out.println("发送完毕!");
		           return true;
	          }catch(Exception e){
	        	   e.printStackTrace();
	          }
	          return false;
	   }
	   
//	   public static void main(String args[]){
//		   send("zhoupeng@chinasofti.com","Pmo系统邮件发送测试","Pmo系统邮件发送测试,请勿回复");
//	   }
}
