package com.algaworks.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.core.properties.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.FakeEmailService;
import com.algaworks.algafood.infrastructure.service.email.SandboxEmailService;
import com.algaworks.algafood.infrastructure.service.email.SmtpEmailService;

@Configuration
public class EmailConfig {
	
	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EnvioEmailService emailService() {
		
		switch(emailProperties.getImpl()) {
			case SMTP: {
				return new SmtpEmailService();
			}
			case SANDBOX: {
				return new SandboxEmailService();
			}
			default: {
				return new FakeEmailService();
			}
		}
		
	}
	
}
