package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.domain.model.Cidade;


@Component
public class CidadeModelAssembler 
extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);
	}

	private ModelMapper modelMapper;
	
	@Override
	public CidadeModel toModel(Cidade cidade) {
		
		CidadeModel cidadeModel = modelMapper.map(cidade, CidadeModel.class);
		
		cidadeModel.add(linkTo(methodOn(CidadeController.class).buscar(cidadeModel.getId())).withSelfRel());

		cidadeModel.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));

		cidadeModel.getEstado()
				.add(linkTo(methodOn(EstadoController.class).buscar(cidadeModel.getEstado().getId())).withSelfRel());

		
		return cidadeModel;
	}
	
	/*
	public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
		return cidades.stream()
				.map(c -> toModel(c))
				.collect(Collectors.toList());
	}
	*/
	
}
