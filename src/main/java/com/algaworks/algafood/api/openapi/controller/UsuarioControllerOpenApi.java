package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Listar os usuários")
	public List<UsuarioModel> listar();
	
	@ApiOperation("Buscar um usuário pelo seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "ID da usuário é inválido", 
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
	})
	public UsuarioModel buscar(
			@ApiParam(value = "ID do usuário") Long usuarioId);
	
	@ApiOperation("Cadastrar um novo usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Usuário cadastrado")
	})
	public UsuarioModel criar(
			@ApiParam("Representação de um usuário") UsuarioComSenhaInput usuarioInput);
	
	@ApiOperation("Alterar um usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "200", description = "Usuário atualizado")
	})
	public UsuarioModel alterar(
			@ApiParam(value = "ID do usuário", example = "1") Long usuarioId, 
			@ApiParam("Representação do usuário")
			UsuarioInput usuarioInput);
	
	@ApiOperation("Excluir um usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Usuário excluído"),
		@ApiResponse(responseCode = "409", description = "Registro de usuário já utilizado por outra entidade", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void excluir(
			@ApiParam(value = "ID do usuário", example = "1") Long usuarioId);
	
	@ApiOperation("Altera a senha do usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Usuário excluído")
	})
    public void alterarSenha(
    		@ApiParam(value = "ID do usuário", example = "1") Long usuarioId, 
    		@ApiParam(value = "Representação da alteração da senha") SenhaInput senha);
	
}