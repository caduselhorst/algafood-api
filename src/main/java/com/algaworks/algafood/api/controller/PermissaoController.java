package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.api.openapi.controller.PermissaoControllerOpenApi;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(value = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {
	
	@Autowired
	private PermissaoRepository repository;
	@Autowired
	private PermissaoModelAssembler assembler;
	
	
	@GetMapping
	public CollectionModel<PermissaoModel> listar() {
		return assembler.toCollectionModel(repository.findAll());
	}
	

}
