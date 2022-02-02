package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {
		
	@ApiOperation("Lista os usuários reponsáveis pelo restaurante")
	public List<UsuarioModel> listar(
			@ApiParam(value = "ID do restaurante", required = true, example = "") Long restauranteId);
	
	@ApiOperation("Associa um usuário como responsável do restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Usuário associado como responsável do restaurante")
	})
	public void associarUsuarioResponsavel(
			@ApiParam(example = "1", required = true, value = "ID do restaurante")
			Long restauranteId, 
			@ApiParam(example = "1", required = true, value = "ID do usuário")
			Long usuarioId);
	
	@ApiOperation("Desassocia um usuário como responsável do restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Usuário desassociado como responsável do restaurante")
	})
	public void desassociarUsuarioResponsavel(
			@ApiParam(example = "1", required = true, value = "ID do restaurante")
			Long restauranteId, 
			@ApiParam(example = "1", required = true, value = "ID do usuário")
			Long usuarioId);

}
