package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel extends RepresentationModel<CidadeResumoModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Macapá")
	private String nome;
	
	@ApiModelProperty(example = "Amapá")
	private String estado;
	
}
