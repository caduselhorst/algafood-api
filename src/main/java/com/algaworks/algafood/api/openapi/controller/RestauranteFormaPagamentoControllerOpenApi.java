package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

	@ApiOperation("Lista formas de pagamento associadas ao restaurante")
	public CollectionModel<FormaPagamentoModel> listar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@ApiOperation("Desassocia forma de pagamento do restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrada", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Forma de pagamento desassociada do restaurante")
	})
	public void desassociarFormaPagameto(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId,
			@ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId);
	
	@ApiOperation("Associa forma de pagamento do restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrada", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Forma de pagamento associada do restaurante")
	})
	public void associarFormaPagamento(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId,
			@ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId);
	
}
