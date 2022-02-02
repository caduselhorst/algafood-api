package com.algaworks.algafood.api.model;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Representa uma cozinha")
@Data
public class CozinhaModel {
	
	@ApiModelProperty(value = "ID da cozinha", example = "1")
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@ApiModelProperty(value = "Nome da cozinha", example = "Japonesa")
	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
