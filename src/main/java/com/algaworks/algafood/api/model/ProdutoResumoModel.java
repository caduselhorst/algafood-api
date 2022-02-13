package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoResumoModel extends RepresentationModel<ProdutoResumoModel> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Pizza calabreza")
	private String nome;
	
	@ApiModelProperty(example = "50.51")
	private BigDecimal preco;


}
