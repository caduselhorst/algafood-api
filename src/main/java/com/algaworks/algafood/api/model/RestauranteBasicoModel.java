package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteBasicoModel extends RepresentationModel<RestauranteBasicoModel> {
	
	private Long id;
	
	@ApiModelProperty(example = "CozinhaMineira")
	private String nome;
	
	@ApiModelProperty(example = "12.0")
	private BigDecimal taxaFrete;
	
	private CozinhaModel cozinha;

}
