package com.algaworks.algafood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.controller.RestauranteUsuarioResponsavelController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;

@Component
public class AlgaLinks {
	
	/**
	 * Constante de template variables para cricação de links para representação de recursos com paginação
	 */
	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM)
		); 
	/**
	 * Cria um link para o recurso de coleção de pedidos
	 * @return
	 */
	public Link linkToPedidos() {
		
		String pedidosUrl = linkTo(PedidoController.class).toUri().toString();
				
		TemplateVariables paramVariables = new TemplateVariables(
					new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
					new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
					new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
					new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM)
				);
		
		return Link.of(UriTemplate.of(pedidosUrl, 
				PAGINACAO_VARIABLES.concat(paramVariables)), "pedidos");
		
	}
	
	
	public Link linkToClientes() {
	    return linkTo(methodOn(UsuarioController.class).listar()).withRel("clientes");
	}
	
	public Link linkToCliente(Long clienteId) {
	    return linkTo(methodOn(UsuarioController.class).buscar(clienteId)).withSelfRel();
	}
	
	public Link linkToUsuarios(String rel) {
		if(rel == null) {
			return linkTo(methodOn(UsuarioController.class).listar()).withRel(IanaLinkRelations.SELF_VALUE);
		}
	    return linkTo(methodOn(UsuarioController.class).listar()).withRel("usuarios");
	}

	public Link linkToUsuario(Long usuarioId) {
	    return linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withSelfRel();
	}
	
	public Link linkToFormasPagamento(String rel) {
		if(rel == null) {
			return linkTo(methodOn(FormaPagamentoController.class).listar(null)).withRel(IanaLinkRelations.SELF_VALUE);
		}
	    return linkTo(methodOn(FormaPagamentoController.class).listar(null)).withRel(rel);
	}

	public Link linkToFormaPagamento(Long formaPagamentoId) {
	    return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null)).withSelfRel();
	}
	
	public Link linkToRestaurantes() {
	    return linkTo(methodOn(RestauranteController.class).listar()).withRel("restaurante");
	}

	public Link linkToRestaurante(Long restauranteId) {
	    return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withSelfRel();
	}
	
	public Link linkToCidades() {
	    return linkTo(methodOn(CidadeController.class).listar()).withRel("cidades");
	}

	public Link linkToCidade(Long cidadeId) {
	    return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withSelfRel();
	}
	
	public Link linkToProduto(Long restauranteId, Long produtoId) {
		return linkTo(methodOn(RestauranteProdutoController.class).buscar(restauranteId, produtoId)).withSelfRel();
	}
	
	public Link linkToProdutos(Long restauranteId) {
		return linkTo(methodOn(RestauranteProdutoController.class).listar(restauranteId, false)).withRel("produtos");
	}
	
	public Link linkToEstado(Long estadoId) {
		return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withSelfRel();
	}
	
	public Link linkToEstados() {
		return linkTo(EstadoController.class).withRel("estados");
	}
	
	public Link linkToCozinha(Long cozinhaId) {
		return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withSelfRel();
	}
	
	public Link linkToCozinhas(String rel) {
		if(rel == null) {
			return linkTo(CozinhaController.class).withSelfRel();
		}
		return linkTo(CozinhaController.class).withRel(rel);
	}
	
	public Link linkToResponsaveisRestaurante(Long restauranteId) {
		return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).listar(restauranteId)).withSelfRel();
	}
	
	public Link linkToGruposUsuario(Long usuarioId) {
		return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel("grupos-usuario");
	}

}
