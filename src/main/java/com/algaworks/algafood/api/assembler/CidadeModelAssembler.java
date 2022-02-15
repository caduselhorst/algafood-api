package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);
	}


	public CidadeModel toModel(Cidade cidade) {

		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		modelMapper.map(cidade, cidadeModel);
		
		
		cidadeModel.add(algaLinks.linkToCidades());
		
		cidadeModel.getEstado().add(algaLinks.linkToEstado(cidadeModel.getEstado().getId()));
		cidadeModel.getEstado().add(algaLinks.linkToEstados());

		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities).add(linkTo(CidadeController.class).withSelfRel());
	}


}
