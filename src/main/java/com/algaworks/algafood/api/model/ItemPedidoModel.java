package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "1")
	private Integer quantidade;
	
	@ApiModelProperty(example = "50,51")
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(example = "50.51")
	private BigDecimal precoTotal;
	
	@ApiModelProperty(example = "Sem cebola")
	private String observacao;
	
	private ProdutoResumoModel produto;

}
