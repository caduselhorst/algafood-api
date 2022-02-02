package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CozinhaInput {

	@ApiModelProperty(example = "Japonesa", required = true)
	@NotBlank
	private String nome;
	
}
