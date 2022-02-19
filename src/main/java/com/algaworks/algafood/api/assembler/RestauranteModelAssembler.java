package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}

	public RestauranteModel toModel(Restaurante restaurante) {
		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, restauranteModel);

		restauranteModel.getCozinha().add(algaLinks.linkToCozinha(restauranteModel.getCozinha().getId()));
		
		if(restauranteModel.getEndereco() != null) {
			restauranteModel.getEndereco().getCidade()
			.add(algaLinks.linkToCidade(restauranteModel.getEndereco().getCidade().getId()));
		}
		
		restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		
		restauranteModel.add(algaLinks.linkToProdutos(restauranteModel.getId(), "produtos"));
		
		//links condicionais
		if(restaurante.podeAbrir()) {
			restauranteModel.add(algaLinks.linkToAbrirRestaurante(restauranteModel.getId()));
		}
		
		if(restaurante.podeFechar()) {
			restauranteModel.add(algaLinks.linkToFecharRestaurante(restauranteModel.getId()));
		}
		
		if(restaurante.podeAtivar()) {
			restauranteModel.add(algaLinks.linkToAtivarRestaurante(restauranteModel.getId()));
		}
		
		if(restaurante.podeInativar()) {
			restauranteModel.add(algaLinks.linkToInativarRestaurante(restauranteModel.getId()));
		}
		
		restauranteModel.add(algaLinks.linkToFormasPagamentoRestaurante(restauranteModel.getId()));
		restauranteModel.add(algaLinks.linkToResponsaveisRestaurante(restauranteModel.getId(), "responsaveis"));

		return restauranteModel;

	}

}
