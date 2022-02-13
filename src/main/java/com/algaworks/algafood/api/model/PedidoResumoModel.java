package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
//@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {
	
	@ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String codigo;
	
	@ApiModelProperty(example = "200.50")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "11.50")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "210.00")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "2019-12-01T20:34:04Z")
	private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(example = "CRIADO")
	private String status;
	private UsuarioModel cliente;
	private RestauranteResumoModel restaurante;

}
