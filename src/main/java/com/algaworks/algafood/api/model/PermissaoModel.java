package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoModel {

	@ApiModelProperty(value = "ID da permissão", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome da permissão", example = "CONSULTAR_PRODUTOS")
	private String nome;
	
	@ApiModelProperty(value = "Descrição da permissão", example = "Permite consultar os produtos")
	private String descricao;

}
