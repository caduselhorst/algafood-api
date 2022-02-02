package com.algaworks.algafood.api.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.core.validation.TimeOffset;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import com.algaworks.algafood.domain.service.VendaReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Api(tags = "Estatísticas")
@RestController
@RequestMapping("/estatisticas")
@Validated
public class EstatisticasController {
	
	@Autowired
	private VendaQueryService vendaQueryService;
	
	@Autowired
	private VendaReportService vendaReportService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "dataCriacaoInicio", value = "Data inicio do filtro", dataTypeClass = OffsetDateTime.class, example = "2021-12-01T00:00:00Z", required = false),
		@ApiImplicitParam(name = "dataCriacaoFim", value = "Data fim do filtro", dataTypeClass = OffsetDateTime.class, example = "2021-12-01T23:59:59Z", required = false),
		@ApiImplicitParam(name = "restauranteId", value = "ID do restaurante", dataTypeClass = String.class, example = "1", required = false),
		@ApiImplicitParam(name = "timeOffset", value = "Tempo para cálculo de offset da data de retorno", dataTypeClass = String.class, example = "+00:00", required = false, defaultValue = "+00:00")
	})
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, 
			@RequestParam(required = false, defaultValue = "+00:00") @TimeOffset String timeOffset) {
		return vendaQueryService.consultaVendasDiarias(filtro, timeOffset);
	}
	
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, 
			@RequestParam(required = false, defaultValue = "+00:00") @TimeOffset String timeOffset) {
		
		byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filtro, timeOffset);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
				
	}

}
