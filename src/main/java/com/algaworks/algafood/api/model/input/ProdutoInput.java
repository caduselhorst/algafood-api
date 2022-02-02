package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {
	
	@ApiModelProperty(value = "Nome do produto", required = true, example = "Pizza Calabreza - 12 fatias")	
	@NotBlank
	private String nome;
	
	@ApiModelProperty(value = "Descrição do produto", required = true, 
			example = "Massa assada no forno a lenha recheada com molho de tomate, queijo mozzarela e linguiça calabreza")
	@NotBlank
	private String descricao;
	
	@ApiModelProperty(value = "Valor do produto. Deve ser maior que $0,00", required = true, 
			example = "32.5")
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	@ApiModelProperty(value = "Indica se o produto está ativo ou não", required = true, 
			example = "true")
	@NotNull
	private Boolean ativo;

}
