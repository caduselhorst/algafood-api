package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.api.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController implements EstadoControllerOpenApi {
	
	private CadastroEstadoService cadastroEstado;
	private EstadoModelAssembler assembler;
	private EstadoInputDisassembler disassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstadoModel>> listar() {
		List<Estado> estados = cadastroEstado.listar();
		return ResponseEntity.ok(
				assembler.toCollectionModel(estados));
	}
	
	@GetMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstadoModel buscar(@PathVariable Long estadoId) {
		return assembler.toModel( cadastroEstado.buscar(estadoId) );
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estado) {
		
		return assembler.toModel(
				cadastroEstado.adiciona(
						disassembler.toDomainObject(estado)));
		
	}
	
	@PutMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstadoModel alterar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estado) {
		return assembler.toModel(
				cadastroEstado.altera(estadoId, estado));
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}

}
