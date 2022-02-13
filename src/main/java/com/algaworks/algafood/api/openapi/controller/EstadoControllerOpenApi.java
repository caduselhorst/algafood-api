package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	@ApiOperation("Listar os estados")
	public CollectionModel<EstadoModel> listar();
	
	@ApiOperation("Buscar um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
				content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "ID do estado é inválido", 
		content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class)))
	})
	public EstadoModel buscar(@ApiParam(value = "ID do estado", example = "1", required = true) Long estadoId);
	
	@ApiOperation("Cadastrar um estado")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Estado cadastrado")
	})
	public EstadoModel adicionar(@ApiParam(value = "Representação de um estado", required = true) EstadoInput estado);
	
	@ApiOperation("Alterar um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
				content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "ID do estado é inválido", 
		content = @Content(mediaType = "application/json",  schema = @Schema(implementation = Problem.class)))
	})
	public EstadoModel alterar(
			@ApiParam(value = "ID do estado", required = true, example = "1")
			Long estadoId, 
			@ApiParam(value = "Representação de um estado", required = true)
			EstadoInput estado);
	
	@ApiOperation("Exclui um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Estado excluído excluída"),
		@ApiResponse(responseCode = "409", description = "Registro do estado sendo utilizado em alguma entidade", 
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
	})
	public void excluir(Long estadoId);
	
}
