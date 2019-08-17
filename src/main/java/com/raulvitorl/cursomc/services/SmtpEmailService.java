package com.raulvitorl.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService{

	@Autowired
	MailSender mailSender;
	@Autowired
	JavaMailSender javaMailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage smm) {
		LOG.info("Enviando Email...");
		mailSender.send(smm);
		LOG.info("Email enviado!");	
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage mms) {
		
		LOG.info("Enviando Email HTML...");
		javaMailSender.send(mms);
		LOG.info("Email enviado!");	
	}

}
