package com.algaworks.algafood.api.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PagedSortModel")
public class PagedSortOpenApi {
	
	@ApiModelProperty(value = "Indica se a lista está ordenada")
	private boolean sorted;
	
	@ApiModelProperty(value = "Indica se a lista não está ordenada")
	private boolean unsorted;
	
	@ApiModelProperty(value = "Indica se o resultado veio de uma query com ordenação")
	private boolean empty;

}
