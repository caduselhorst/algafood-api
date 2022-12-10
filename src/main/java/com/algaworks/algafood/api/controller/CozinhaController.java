package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.api.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cozinhas")
@AllArgsConstructor
public class CozinhaController implements CozinhaControllerOpenApi {

	private CozinhaRepository repository;
	private CadastroCozinhaService service;
	private CozinhaModelAssembler cozinhaModelAssembler;
	private CozinhaInputDisassembler cozinhaInpuDisassembler;
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

	@CheckSecurity.Cozinhas.PodeConsultar
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PagedModel<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		
		Page<Cozinha> cozinhasPage = repository.findAll(pageable);
		
		PagedModel<CozinhaModel> cozinhasPagedModel = pagedResourcesAssembler
				.toModel(cozinhasPage,  cozinhaModelAssembler);
		
		
		return cozinhasPagedModel;
	}

	@CheckSecurity.Cozinhas.PodeConsultar
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CozinhaModel buscar(@PathVariable Long id) {
		return cozinhaModelAssembler.toModel(
				service.buscarOuFalhar(id));
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CozinhaModel> adicionar(@RequestBody @Valid CozinhaInput cozinha) {

		return new ResponseEntity<CozinhaModel>(
				cozinhaModelAssembler.toModel(
						service.salvar(
								cozinhaInpuDisassembler.toDomainObject(cozinha))), HttpStatus.CREATED);
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CozinhaModel alterar(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinha) {

		Cozinha cozinhaAtual = service.buscarOuFalhar(id);
		//BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		cozinhaInpuDisassembler.copyToDomainObject(cozinha, cozinhaAtual);
		return cozinhaModelAssembler.toModel(service.salvar(cozinhaAtual));

	}

	@CheckSecurity.Cozinhas.PodeEditar
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}

}
