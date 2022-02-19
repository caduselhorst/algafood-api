package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.input.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

	@ApiOperation("Lista os produtos, informando um id de restaurante e se deseja mostrar todos ou somente os ativos")
	CollectionModel<ProdutoModel> listar(
			@ApiParam(value = "ID do restaurante", required = true, example = "1")
			Long restauranteId, 
			@ApiParam(value = "Incluir inativos", required = true, example = "true")
			Boolean incluirInativos);

	@ApiOperation("Buscar um produto por ID de restaurante e por ID de produto")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
				content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto é inválido", 
		content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class)))
	})
	ProdutoModel buscar(
			@ApiParam(value = "ID do restaurante", required = true, example = "1")
			Long restauranteId, 
			@ApiParam(value = "ID do produto", required = true, example = "1")
			Long produtoId);

	@ApiOperation("Adicionar um novo produto a um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
				content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "201", description = "Produto criado")
	})
	ProdutoModel adicionar(
			@ApiParam(value =  "ID do restaurante", required = true, example = "1")
			Long restauranteId, 
			@ApiParam(value = "Representação do produto", required = true)
			ProdutoInput produtoInput);

	@ApiOperation("Atualizar um produto")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
				content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "200", description = "Produto atualizado")
	})
	ProdutoModel atualizar(
			@ApiParam(value =  "ID do restaurante", required = true, example = "1")
			Long restauranteId, 
			@ApiParam(value =  "ID do produto", required = true, example = "1")
			Long produtoId, 
			@ApiParam(value = "Representação do produto", required = true)
			ProdutoInput produtoInput);

}