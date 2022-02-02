package com.algaworks.algafood.api.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {
	
	@ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
	public FotoProdutoModel buscar(Long restauranteId, Long produtoId);
	
	@ApiOperation(value = "Busca a foto do produto de um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK", 
            		content = @Content(schema = @Schema(implementation = FotoProdutoModel.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "200",description = "OK", 
            		content = @Content(mediaType = "image/png")),
            @ApiResponse(responseCode = "200",description = "OK", 
            		content = @Content(mediaType = "image/jpeg")),
            @ApiResponse(responseCode = "400",description = "ID do restaurante ou produto inválido", 
            		content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",description = "Foto de produto não encontrada", 
            		content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "406",description = "Tipo de media type não suportado", 
    		content = @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)))
    })
	
	public ResponseEntity<?> servir(
			@ApiParam(value = "ID do restaurante", required = true, example = "1")
			Long restauranteId, 
			@ApiParam(value = "ID do produto", required = true, example = "1")
			Long produtoId,
			@ApiParam(allowableValues = "application/json,image/jpeg,image/png", value = "Tipo de media a retornar", defaultValue = "application/json", type = "header")
			String acceptHeader) 
					throws HttpMediaTypeNotAcceptableException;
	
	
	public FotoProdutoModel atualizarFoto(
			Long restauranteId, 
			Long produtoId, 
			FotoProdutoInput fotoProdutoInput,
			@ApiParam(type = "file")
			MultipartFile arquivo) throws IOException;
	
	public void excluitFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId);
	
}
