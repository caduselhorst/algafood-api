package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "FormasPagamento")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation("Lista as formas de pagamento")
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);
	
	@ApiOperation("Busca uma forma de pagamento pelo ID")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "400", description = "ID da forma de pagamento é inválido", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<FormaPagamentoModel> buscar(
			@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
			Long formaPagamentoId, ServletWebRequest request);
	
	@ApiOperation("Cadastra uma nova forma de pagamento")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Forma de pagamento criada")
	})
	public FormaPagamentoModel criar(
			@ApiParam(name = "corpo", value = "Representação de uma forma de pagamento", required = true)
			FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Altera uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada")
	})
	public FormaPagamentoModel alterar(
			@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId, 
			@ApiParam(name = "corpo", value = "Representação de uma forma de pagamento", required = true)
			FormaPagamentoInput formaPagamentoInput );
	
	@ApiOperation("Exclui uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Forma de pagamento excluída")
	})
	public void excluir(@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);
	
}
