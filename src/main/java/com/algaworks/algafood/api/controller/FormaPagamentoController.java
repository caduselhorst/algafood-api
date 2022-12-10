package com.algaworks.algafood.api.controller;

import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

	private CadastroFormaPagamentoService service;
	private FormaPagamentoRepository repository;
	
	@CheckSecurity.FormasPagamento.PodeConsultar
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request) {
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = "0";
		
		OffsetDateTime dataUltAtualizacao = repository.getDataUltimaAtalizacao();
		
		if(dataUltAtualizacao != null) {
			eTag = String.valueOf(dataUltAtualizacao.toEpochSecond());
		}
		
		if(request.checkNotModified(eTag)) {
			return null;
		}
		
		CollectionModel<FormaPagamentoModel> formas = service.listar();
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(formas);
	}
	
	@CheckSecurity.FormasPagamento.PodeConsultar
	@GetMapping(path = "/{formaPagamentoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
	    
	    String eTag = "0";
	    
	    OffsetDateTime dataAtualizacao = repository
	            .getDataAtualizacaoById(formaPagamentoId);
	    
	    if (dataAtualizacao != null) {
	        eTag = String.valueOf(dataAtualizacao.toEpochSecond());
	    }
	    
	    if (request.checkNotModified(eTag)) {
	        return null;
	    }
		
		FormaPagamentoModel forma = service.buscar(formaPagamentoId);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(forma);
	}
	
	@CheckSecurity.FormasPagamento.PodeEditar
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel criar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		return service.salvar(formaPagamentoInput);
	}
	
	@CheckSecurity.FormasPagamento.PodeEditar
	@PutMapping(path = "/{formaPagamentoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FormaPagamentoModel alterar(@PathVariable Long formaPagamentoId, 
			@RequestBody @Valid FormaPagamentoInput formaPagamentoInput ) {
		return service.alterar(formaPagamentoId, formaPagamentoInput);
	}
	
	@CheckSecurity.FormasPagamento.PodeEditar
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long formaPagamentoId) {
		service.excluir(formaPagamentoId);
	}
	
}
