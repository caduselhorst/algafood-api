package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaInput extends UsuarioInput {

	@ApiModelProperty(value = "Senha do usuário", example = "S3nh@", required = true)
	@NotBlank
	private String senha;
	
}
