package com.seviceBookingBackendapp.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Email 
{
	private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;

    @Autowired private JavaMailSender javaMailSender;

    
	Email() 
	{
	}


	public String getRecipient() {
		return recipient;
	}


	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}


	public String getMsgBody() {
		return msgBody;
	}


	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getAttachment() {
		return attachment;
	}


	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public void sendSimpleMail(String reciver,String url)
    {
		
        SimpleMailMessage mailMessage = new SimpleMailMessage();
	    mailMessage.setFrom("chouhanakash515@gmail.com");
	    mailMessage.setTo(reciver);
	    mailMessage.setSubject("Registration Link");
	    mailMessage.setText(url);

    	javaMailSender.send(mailMessage);

    }
}
