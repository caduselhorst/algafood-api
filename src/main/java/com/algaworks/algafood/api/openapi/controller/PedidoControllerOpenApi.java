package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {
	
	@ApiOperation("Pesquisa pedidos")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", dataTypeClass = String.class)
	})
	public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,  Pageable pageable);
	
	@ApiOperation("Busca por um código")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", dataTypeClass = String.class)
	})
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Pedido não econtrado", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
	})
	public PedidoModel buscar(@ApiParam(example = "f9981ca4-5a5e-4da3-af04-933861df3e55", value="Código do pedido", required = true) String codigo);
	
	@ApiOperation("Adiciona um novo pedido.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Pedido registrado")
	})
	public PedidoModel adicionar(@ApiParam(value = "Representação de um pedido", name = "corpo", required = true) PedidoInput pedidoInput);

}
