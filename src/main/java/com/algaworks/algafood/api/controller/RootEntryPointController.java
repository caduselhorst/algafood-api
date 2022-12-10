package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.AlgaLinks;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public static class RootEntrypointModel extends RepresentationModel<RootEntrypointModel> {
	}
	
	@GetMapping
	public RootEntrypointModel root() {
		var rootEntrypointModel = new RootEntrypointModel();
		
		rootEntrypointModel.add(algaLinks.linkToCozinhas("cozinhas"));
		rootEntrypointModel.add(algaLinks.linkToPedidos("pedidos"));
		rootEntrypointModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		rootEntrypointModel.add(algaLinks.linkToGrupos("grupos"));
		rootEntrypointModel.add(algaLinks.linkToPermissoes("permissoes"));
		rootEntrypointModel.add(algaLinks.linkToFormasPagamento("formas-pagamento"));
		rootEntrypointModel.add(algaLinks.linkToUsuarios("usuarios"));
		rootEntrypointModel.add(algaLinks.linkToEstados("estados"));
		rootEntrypointModel.add(algaLinks.linkToCidades("cidades"));
		rootEntrypointModel.add(algaLinks.linkToEstatisticas("estatisticas"));
		
		return rootEntrypointModel;
	}

}
