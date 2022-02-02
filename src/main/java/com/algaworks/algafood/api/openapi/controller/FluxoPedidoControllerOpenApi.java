package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {
	
	@ApiOperation("Confirma um pedido pelo código")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Pedido não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Pedido confirmado")
	})
	public void confirmar(
			@ApiParam(value = "Código do pedido", required = true, 
				example = "f9981ca4-5a5e-4da3-af04-933861df3e55") String codigo);
	
	@ApiOperation("Cancela um pedido pelo código")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Pedido não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Pedido cancelado")
	})
	public void cancelar(String codigo);
	
	@ApiOperation("Entrega um pedido pelo código")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Pedido não encontrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "204", description = "Pedido entregue")
	})
	public void entregue (String codigo);

}
