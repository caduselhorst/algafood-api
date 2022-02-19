package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoModel extends RepresentationModel<PermissaoModel> {

	@ApiModelProperty(value = "ID da permissão", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome da permissão", example = "CONSULTAR_PRODUTOS")
	private String nome;
	
	@ApiModelProperty(value = "Descrição da permissão", example = "Permite consultar os produtos")
	private String descricao;

}
