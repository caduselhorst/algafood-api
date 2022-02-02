package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FormaPagamentoInput {

	@ApiModelProperty(example = "Dinheiro", required = true)
	@NotBlank
	private String descricao;
	
}
