package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista os grupos")
	public CollectionModel<GrupoModel> listar();
	
	@ApiOperation("Busca um grupo por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Grupo não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "ID do grupo inválido", 
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
	})
	public GrupoModel buscar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);
	
	@ApiOperation("Cadastra um grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Grupo cadastrado")
	})
	public GrupoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
			GrupoInput grupoInput);
	
	@ApiOperation("Altera um grupo por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Grupo não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "200", description = "Grupo atualizado")
	})
	public GrupoModel alterar(
			@ApiParam(value =  "ID de um grupo", example = "1", required = true)
			Long grupoId, 
			@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
			GrupoInput grupoInput);
	
	@ApiOperation("Exclui um grupo por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Grupo não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Grupo excluído")
	})
	public void excluir(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);
	
}
