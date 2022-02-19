package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoModel extends RepresentationModel<FotoProdutoModel> {
	
	@ApiModelProperty(value = "Nome do arquivo", example = "fed16369-3c86-474d-9fc0-6e0f172db15c_camarao-tailandes.jpeg")
	private String nomeArquivo;
	
	@ApiModelProperty(value = "Descrição da foto", example = "Camarão Tailandes")
	private String descricao;
	
	@ApiModelProperty(value = "Tipo do conteúdo da foto", example = "image/jpg")
	private String contentType;
	
	@ApiModelProperty(value = "Tamanho do arquivo em bytes", example = "9858")
	private Long tamanho;

}
