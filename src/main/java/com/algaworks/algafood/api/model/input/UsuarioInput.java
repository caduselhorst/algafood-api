package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UsuarioInput {

	@ApiModelProperty(value = "Nome do usuário", example = "João da Silva", required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(value = "E-mail do usuário", example = "joao_silva@email.com", required = true)
	@Email
	@NotBlank
	private String email;
	
	
}
