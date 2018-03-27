package com.pmo.dashboard.util;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.pmo.dashboard.entity.MailBaseInfo;
public class SendEmailUtil {
	
	
     //发送方法
     public boolean send(MailBaseInfo mailinfo) throws GeneralSecurityException
     {
        MyAuthenticator authenticator=null;    
        Properties pro=mailinfo.getProperties();
        if(mailinfo.isValidate())
        {
           //如需要验证则创建一个密码验证器
           authenticator=new MyAuthenticator(mailinfo.getUserName(),mailinfo.getPassword());
        }
        Session sendMailSession=Session.getDefaultInstance(pro,authenticator);
        //根据Session创建一个邮件消息
        Message mailMessage=new MimeMessage(sendMailSession);
        //创建邮件发送者地址
        try{
              Address from=new InternetAddress(mailinfo.getSendAddress());
              //设置邮件消息的发送者
              mailMessage.setFrom(from);
              //创建邮件的接收者地址,并设置到邮件消息中
              Address to=new InternetAddress(mailinfo.getAcceptAddress());
              mailMessage.setRecipient(Message.RecipientType.TO,to);
              //设置邮件消息的主题
              mailMessage.setSubject(mailinfo.getSubject());
              //设置邮件消息发送的时间
              mailMessage.setSentDate(new Date());
              //设置邮件消息内容
              String mailcontext=mailinfo.getContext();
              mailMessage.setText(mailcontext);
              //组装附件
              //MimeBodyPart file=new MimeBodyPart();
              //file.attachFile("C:/Documents and Settings/Administrator/桌面/Image.rar");
              //MimeMultipart msgMultipart = new MimeMultipart("mixed");
              //msgMultipart.addBodyPart(file);
              //msgMultipart.setSubType("mixed");
              //mailMessage.setContent(msgMultipart);
              //mailMessage.saveChanges();
              //发送
              Transport.send(mailMessage);
              return true;
          } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          return false;
     }
     
}