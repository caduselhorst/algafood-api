package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SenhaInput {

	@ApiModelProperty(value = "Senha atual", example = "S3nh@", required = true)
	@NotBlank
	private String senhaAtual;
	
	@ApiModelProperty(value = "Nova senha", example = "N0v@S3nh@", required = true)
	@NotBlank
	private String novaSenha;
	
}
