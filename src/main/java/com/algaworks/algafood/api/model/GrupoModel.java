package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GrupoModel {

	@ApiModelProperty(value = "ID do grupo", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do grupo", example = "Gerentes")
	private String nome;
	
}
