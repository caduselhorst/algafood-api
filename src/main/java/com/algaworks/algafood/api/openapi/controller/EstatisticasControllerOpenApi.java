package com.algaworks.algafood.api.openapi.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {

	
	@ApiOperation(value = "Listar as vendas diárias")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "dataCriacaoInicio", value = "Data inicio do filtro", dataTypeClass = OffsetDateTime.class, example = "2021-12-01T00:00:00Z", required = false),
		@ApiImplicitParam(name = "dataCriacaoFim", value = "Data fim do filtro", dataTypeClass = OffsetDateTime.class, example = "2021-12-01T23:59:59Z", required = false),
		@ApiImplicitParam(name = "restauranteId", value = "ID do restaurante", dataTypeClass = String.class, example = "1", required = false),
		@ApiImplicitParam(name = "timeOffset", value = "Tempo para cálculo de offset da data de retorno", dataTypeClass = String.class, example = "+00:00", required = false, defaultValue = "+00:00")
	})
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK", 
				content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE)),
		@ApiResponse(responseCode = "200", description = "OK", 
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendaDiaria.class)))
	})
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

	@ApiOperation(value = "Listar vendas diárias", hidden = false)
	ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);

}