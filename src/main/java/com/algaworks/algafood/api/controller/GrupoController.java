package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.api.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grupos")
public class GrupoController implements GrupoControllerOpenApi {
	
	private CadastroGrupoService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<GrupoModel> listar() {
		return service.listar();
	}
	
	@GetMapping(path = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoModel buscar(@PathVariable Long grupoId) {
		return service.buscar(grupoId);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		return service.adicionar(grupoInput);
	}
	
	@PutMapping(path = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoModel alterar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
		return service.alterar(grupoId, grupoInput);
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long grupoId) {
		service.excluir(grupoId);
	}

}
