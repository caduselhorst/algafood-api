package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {
	
	@ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String codigo;
	
	@ApiModelProperty(example = "200.50")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "11.50")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "212.00")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "2019-12-01T20:34:04Z")
	private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(example = "2019-12-01T21:34:04Z")
	private OffsetDateTime dataConfirmacao;
	
	@ApiModelProperty(example = "2019-12-01T22:34:04Z")
	private OffsetDateTime dataCancelamento;
	
	@ApiModelProperty(example = "2019-12-01T23:34:04Z")
	private OffsetDateTime dataEntrega;
	
	@ApiModelProperty(example = "CRIADO")
	private String status;
	
	
	private UsuarioModel cliente;
	private FormaPagamentoModel formaPagamento;
	private RestauranteResumoModel restaurante;
	private EnderecoModel enderecoEntrega;	
	private List<ItemPedidoModel> itens = new ArrayList<>();

}
