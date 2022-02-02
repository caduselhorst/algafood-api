package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnderecoModel {

	@ApiModelProperty(example = "68902-020")
	private String cep;
	
	@ApiModelProperty(example = "R Leopoldo Machado")
	private String logradouro;
	
	@ApiModelProperty(example = "3270")
	private String numero;
	
	@ApiModelProperty(example = "Apto. N")
	private String complemento;
	
	@ApiModelProperty(example = "Beirol")
	private String bairro;
	
	private CidadeResumoModel cidade;
	
}
