package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {
	
	@ApiOperation(value = "Lista restaurantes")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de pedidos", name = "projecao", 
				paramType = "query", allowableValues =  "apenas-nome", dataTypeClass = String.class)
	})
	public ResponseEntity<CollectionModel<RestauranteBasicoModel>> listar();
	
	@ApiOperation(value = "Lista restaurantes", hidden = true)
	public ResponseEntity<CollectionModel<RestauranteApenasNomeModel>> listarApenasNome();
	
	@ApiOperation(value = "Busca um restaurante pelo seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", 
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
	})
	public RestauranteModel buscar(Long restauranteId);
	
	@ApiOperation("Cadastra um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Restaurante cadastrado")
	})
	public RestauranteModel adicionar (@ApiParam(required = true, value = "Represestação de um restaurante") RestauranteInput restaurante);
	
	@ApiOperation("Exclui um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Restaurante excluído")
	})
	public void excluir(Long restauranteId);
	
	@ApiOperation("Altera restaurante pelo seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "200", description = "Restaurante atualizado")
	})
	public RestauranteModel alterar(
			@ApiParam(required = true, example = "1", value = "ID do restaurante")
			Long restauranteId, 
			@ApiParam(required = true, value = "Represetação de um restaurante")
			RestauranteInput restaurante);
	
	@ApiOperation("Ativa um restaurante, informando seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Restaurante ativado")
	})
	public ResponseEntity<Void> ativar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@ApiOperation("Inativa um restaurante, informando seu ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Restaurante inativado")
	})
	public ResponseEntity<Void> inativar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@ApiOperation("Abre um restaurante, para indicar que está recebendo pedidos. Informa um ID de restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Restaurante aberto")
	})
	public ResponseEntity<Void> abertura(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@ApiOperation("Fecha um restaurante, para indicar que não está recebendo pedidos. Informa um ID de restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Restaurante fechado")
	})
	public ResponseEntity<Void> fechamento(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@ApiOperation("Ativa múltiplos restaurantes")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Restaurantes ativados")
	})
	public void ativarMultiplos(@ApiParam(example = "[1,2,3]", value = "Array de IDs de restaurantes") List<Long> restauranteIds);
	
	@ApiOperation("Inativa múltiplos restaurantes")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Restaurantes inativados")
	})
	public void desativarMultiplos(@ApiParam(example = "[1,2,3]", value = "Array de IDs de restaurantes") List<Long> restauranteIds);

}
