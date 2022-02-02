package com.algaworks.algafood.core.properties;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
@ConfigurationProperties("algafood.email")
@Component
public class EmailProperties {

	@NotNull
	private String remetente;
	
	private Sandbox sandbox = new Sandbox();
	
	private TipoImplementacaoEnvioEmail impl;
	
	public enum TipoImplementacaoEnvioEmail {
		FAKE,SMTP,SANDBOX
	}
	
	@Getter
	@Setter
	public class Sandbox {
		private String destinatario;
	}
}
