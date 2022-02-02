package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GrupoInput {

	@ApiModelProperty(example = "Gerentes", required = true)
	@NotBlank
	private String nome;
	
}
