package com.algaworks.algafood.api.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PagedModel")
@Getter
@Setter
public class PagedModelOpenApi<T> {

	@ApiModelProperty(value = "Lista de objetos")
	private List<T> content;
	
	@ApiModelProperty(value = "Quantidade de elementos na lista", example = "10")
	private int size;
	
	@ApiModelProperty(value = "Total de objetos cadastrados")
	private int totalElement;
	
	@ApiModelProperty(value = "Total de páginas", example = "5")
	private int totalPages;
	
	@ApiModelProperty(value = "Página atual (iniciando em 0)", example = "0")
	private int number;
	private PagedSortOpenApi sort;
	
	@ApiModelProperty(value = "Indica se a página atual é a primeira")
	private boolean first;
	
	@ApiModelProperty(value = "Indica se a página atual é a última")
	private boolean last;
	
}
