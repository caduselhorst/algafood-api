package com.algaworks.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnderecoInput {
	
	@ApiModelProperty(example = "68902-020", required = true)
	@NotBlank
	private String cep;
	
	@ApiModelProperty(example = "R Lepoldo Machado", required = true)
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "3270", required = true)
	@NotBlank
	private String numero;
	
	@ApiModelProperty(example = "Apto. N")
	private String complemento;
	
	@ApiModelProperty(example = "Beirol", required = true)
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
	

}
