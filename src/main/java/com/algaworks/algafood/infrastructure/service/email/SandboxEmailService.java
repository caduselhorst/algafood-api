package com.algaworks.algafood.infrastructure.service.email;

import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.algaworks.algafood.core.freenarker.FreeMarkerTemplateProcessor;
import com.algaworks.algafood.core.properties.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

public class SandboxEmailService implements EnvioEmailService {
	
	@Autowired
	private FreeMarkerTemplateProcessor templateProcessor;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void enviar(Mensagem mensagem) {
		
		try {
			
			Set<String> destinatarioSendbox = new HashSet<>();
			destinatarioSendbox.add(emailProperties.getSandbox().getDestinatario());
			
			
			String corpo = templateProcessor.processarTemplate(mensagem);
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(destinatarioSendbox.toArray(new String[] {}));
			helper.setSubject(mensagem.getAssunto());
			helper.setText(corpo, true);
			
			
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi posível enviar o email", e);
		}
		
	}

	
	
}
