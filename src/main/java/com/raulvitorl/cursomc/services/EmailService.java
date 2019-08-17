package com.raulvitorl.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.raulvitorl.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage smm);
	
	
	
}
