package com.algaworks.algafood.core.freenarker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;
import com.algaworks.algafood.infrastructure.service.email.EmailException;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class FreeMarkerTemplateProcessor {
	
	@Autowired
	private Configuration freeMarkerConfig;	
	
	public String processarTemplate(Mensagem mensage) {
		try {
			
			Template template = freeMarkerConfig.getTemplate(mensage.getCorpo());
			
			
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensage.getVariaveis());
					
		} catch (Exception e) {
			throw new EmailException("Não foi possível montar o template do e-mail", e);
		}
		
	}

}
