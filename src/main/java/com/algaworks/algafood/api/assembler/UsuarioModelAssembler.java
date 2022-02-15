package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	public UsuarioModelAssembler() {
		super(UsuarioController.class, UsuarioModel.class);
		// TODO Auto-generated constructor stub
	}


	public UsuarioModel toModel(Usuario usuario) {

		UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);

		mapper.map(usuario, usuarioModel);

		usuarioModel.add(algaLinks.linkToUsuarios("usuarios"));
		usuarioModel
				.add(algaLinks.linkToGruposUsuario(usuarioModel.getId()));

		return usuarioModel;
	}

	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities).add(algaLinks.linkToUsuarios(null));
	}
}
