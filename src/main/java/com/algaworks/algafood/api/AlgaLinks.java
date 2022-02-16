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
import com.algaworks.algafood.api.controller.FluxoPedidoController;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.RestauranteFormaPagamentoController;
import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.controller.RestauranteUsuarioResponsavelController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;

@Component
public class AlgaLinks {

	/**
	 * Constante de template variables para cricação de links para representação de
	 * recursos com paginação
	 */
	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	/**
	 * Cria um link para o recurso de coleção de pedidos
	 * 
	 * @return
	 */
	public Link linkToPedidos(String rel) {

		String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

		TemplateVariables paramVariables = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

		return Link.of(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(paramVariables)), rel);

	}

	public Link linkToConfirmacaoPedido(String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).confirmar(codigo)).withRel(rel);
	}

	public Link linkToEntregaPedido(String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).entregue(codigo)).withRel(rel);
	}

	public Link linkToCancelamentoPedido(String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).cancelar(codigo)).withRel(rel);
	}

	public Link linkToClientes() {
		return linkTo(methodOn(UsuarioController.class).listar()).withRel("clientes");
	}

	public Link linkToCliente(Long clienteId) {
		return linkTo(methodOn(UsuarioController.class).buscar(clienteId)).withSelfRel();
	}

	public Link linkToUsuarios(String rel) {
		if (rel == null) {
			return linkTo(methodOn(UsuarioController.class).listar()).withRel(IanaLinkRelations.SELF_VALUE);
		}
		return linkTo(methodOn(UsuarioController.class).listar()).withRel("usuarios");
	}

	public Link linkToUsuario(Long usuarioId) {
		return linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withSelfRel();
	}

	public Link linkToFormasPagamento(String rel) {
		if (rel == null) {
			throw new RuntimeException("Rel não deve ser nulo");
		}
		return linkTo(methodOn(FormaPagamentoController.class).listar(null)).withRel(rel);
	}
	
	public Link linkToFormasPagamento() {
		return linkToFormasPagamento(IanaLinkRelations.SELF_VALUE);
	}

	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null)).withRel(rel);
	}

	public Link linkToRestaurantes() {
		return linkToRestaurantes(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestaurantes(String rel) {
		
		String restaurantesUrl = linkTo(RestauranteController.class).toUri().toString();
		
		TemplateVariables paramVariables = new TemplateVariables(
				new TemplateVariable("projecao", VariableType.REQUEST_PARAM));
		
		return Link.of(UriTemplate.of(restaurantesUrl, paramVariables), rel);
	}

	public Link linkToRestaurante(Long restauranteId) {
		return linkToRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withRel(rel);
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
		return linkToCozinha(cozinhaId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCozinha(Long cozinhaId, String rel) {
		return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withRel(rel);
	}

	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaController.class).withRel(rel);
	}

	public Link linkToResponsaveisRestaurante(Long restauranteId) {
		return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
	}

	public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).listar(restauranteId)).withRel(rel);
	}

	public Link linkToGruposUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
	}
	
	public Link linkToGruposUsuario(Long usuarioId) {
		return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(IanaLinkRelations.SELF_VALUE);
	}

	public Link linkToFormasPagamentoRestaurante(Long restauranteId) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId))
				.withRel("formas-pagamento");
	}
	
	public Link linkToAtivarRestaurante(Long restauranteId) {
		return linkTo(methodOn(RestauranteController.class).ativar(restauranteId)).withRel("ativar");
	}
	
	public Link linkToInativarRestaurante(Long restauranteId) {
		return linkTo(methodOn(RestauranteController.class).ativar(restauranteId)).withRel("inativar");
	}
	
	public Link linkToAbrirRestaurante(Long restauranteId) {
		return linkTo(methodOn(RestauranteController.class).abertura(restauranteId)).withRel("abrir");
	}
	
	public Link linkToFecharRestaurante(Long restauranteId) {
		return linkTo(methodOn(RestauranteController.class).fechamento(restauranteId)).withRel("fechar");
	}

}
