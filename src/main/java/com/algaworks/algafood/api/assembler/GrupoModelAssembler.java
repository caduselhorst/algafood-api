package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.GrupoController;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	public GrupoModelAssembler() {
		super(GrupoController.class, GrupoModel.class);
	}

	
	public GrupoModel toModel(Grupo grupo) {
		GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
		mapper.map(grupo, grupoModel);
		
		grupoModel.add(algaLinks.linkToGrupos("grupos"));
		grupoModel.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "permissões"));
		
		return grupoModel;
	}
	
	
	
}
