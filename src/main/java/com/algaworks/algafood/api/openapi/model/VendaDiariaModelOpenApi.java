package com.algaworks.algafood.api.openapi.model;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("VendaDiaria")
public class VendaDiariaModelOpenApi {

	@ApiModelProperty(value = "Data da venda", example = "2021-12-01")
	private Date data;
	
	@ApiModelProperty(value = "Quantidade de vendas", example = "30")
	private Long totalVendas;
	
	@ApiModelProperty(value = "Total faturado", example = "1451.50")
	private BigDecimal totalFaturado;
	
}
