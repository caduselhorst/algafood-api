package com.algaworks.algafood.infrastructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.algaworks.algafood.core.freenarker.FreeMarkerTemplateProcessor;
import com.algaworks.algafood.core.properties.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

public class SmtpEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private FreeMarkerTemplateProcessor templateProcessor;
	
	@Override
	public void enviar(Mensagem mensagem) {
		
		try {
			
			String corpo = templateProcessor.processarTemplate(mensagem);
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(mensagem.getDestinatarios().toArray(new String[] {}));
			helper.setSubject(mensagem.getAssunto());
			helper.setText(corpo, true);
			
			
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi posível enviar o email", e);
		}
		
	}
	
}
