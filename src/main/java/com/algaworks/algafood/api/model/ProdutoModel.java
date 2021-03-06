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
public class ProdutoModel extends RepresentationModel<ProdutoModel> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Pizza Calabreza - 12 fatias")
	private String nome;
	
	@ApiModelProperty(example = "Massa assada em forno a lenha coberta com molho de tomate, queijo mozzarela e fatias de liguiça calabreza")
	private String descricao;
	
	@ApiModelProperty(example = "32.5")
	private BigDecimal preco;
	
	@ApiModelProperty(example = "false")
	private Boolean ativo;

}
