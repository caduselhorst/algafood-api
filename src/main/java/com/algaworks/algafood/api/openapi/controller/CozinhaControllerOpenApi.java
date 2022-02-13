package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {
	
	@ApiOperation("Listar as cozinhas com paginação")
	public PagedModel<CozinhaModel> listar(Pageable pageable);

	@ApiOperation("Buscar uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "ID da cozinha é inválido", 
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
	})
	public CozinhaModel buscar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long id);

	@ApiOperation("Cadastrar uma nova cozinha")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cidade cadastrada")
	})
	public ResponseEntity<CozinhaModel> adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma coziha", required = true)
			CozinhaInput cozinha);

	
	@ApiOperation("Alterar uma cozinha")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "200", description = "Cozinha atualizada")
	})
	public CozinhaModel alterar(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long id, 
			@ApiParam(name = "corpo", value = "Representação de uma cozinha", required = true)
			CozinhaInput cozinha);

	@ApiOperation("Excluir uma cozinha")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Cozinha excluída")
	})
	public void excluir(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long id);

}
