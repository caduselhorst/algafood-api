package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

	@ApiOperation("Listar os grupos associados a um usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Usuario não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
	})
	CollectionModel<GrupoModel> listar(@ApiParam(value = "ID do usuario", example = "1") Long usuarioId);

	@ApiOperation("Associa um grupo a um usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Grupo associado ao usuário")
	})
	void associarGrupo(
			@ApiParam(value = "ID usuário", example = "1") Long usuarioId, 
			@ApiParam(value = "ID do grupo", example = "1") Long grupoId);

	@ApiOperation("Desassocia um grupo de um usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Grupo desassociado do usuário")
	})
	void desassociarGrupo(
			@ApiParam(value = "ID usuário", example = "1") Long usuarioId, 
			@ApiParam(value = "ID do grupo", example = "1") Long grupoId);

}