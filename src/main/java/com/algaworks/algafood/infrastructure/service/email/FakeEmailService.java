package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.core.freenarker.FreeMarkerTemplateProcessor;
import com.algaworks.algafood.core.properties.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEmailService implements EnvioEmailService {
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private FreeMarkerTemplateProcessor templateProcessor;

	@Override
	public void enviar(Mensagem mensagem) {
			
		String corpo = templateProcessor.processarTemplate(mensagem);

		log.info(String.format("Enviado email. De: %s Para: %s Assunto: %s Corpo: %s", 
				emailProperties.getRemetente(),
				mensagem.getDestinatarios(), 
				mensagem.getAssunto(),
				corpo));

	}

}
