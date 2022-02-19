package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	@ApiOperation("Lista as permissões de um grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Grupo não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
	})
	CollectionModel<PermissaoModel> lista(
			@ApiParam(value = "ID do grupo", example = "1") Long grupoId);

	
	@ApiOperation("Desassocia uma permissão ao grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Permissão desassociada ao grupo")
	})
	ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID do grupo", example = "1")
			Long grupoId, 
			@ApiParam(value = "ID da permissão", example = "1")
			Long permissaoId);

	@ApiOperation("Associa uma permissão ao grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Permissão associada ao grupo")
	})
	ResponseEntity<Void> associar(
			@ApiParam(value = "ID do grupo", example = "1")
			Long grupoId, 
			@ApiParam(value = "ID da permissão", example = "1")
			Long permissaoId);

}