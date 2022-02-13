package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.ProdutoResumoModel;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	
	public PedidoModel toModel(Pedido pedido)  {
		
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		
		modelMapper.map(pedido, pedidoModel);
		
		// links de pedido com template
		//pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
		
		String pedidosUrl = linkTo(PedidoController.class).toUri().toString();
		
		TemplateVariables pageVariables	= new TemplateVariables(
					new TemplateVariable("page", VariableType.REQUEST_PARAM),
					new TemplateVariable("size", VariableType.REQUEST_PARAM),
					new TemplateVariable("sort", VariableType.REQUEST_PARAM)
				);
		
		pedidoModel.add(Link.of(UriTemplate.of(pedidosUrl, 
				pageVariables), "pedidos"));
		
		/*
		 * TODO verificar porque o hateoes coloca o link para o id do pedido junto com os outros links.
		 */
		//pedidoModel.add(linkTo(methodOn(PedidoController.class).buscar(pedidoModel.getCodigo())).withSelfRel());
		
		// links de clientes
		pedidoModel.getCliente().add(linkTo(
					methodOn(UsuarioController.class).buscar(pedidoModel.getCliente().getId()))
				.withSelfRel());
		pedidoModel.getCliente().add(linkTo(UsuarioController.class).withRel("clientes"));
		
		// links de formas de pagamento
		pedidoModel.getFormaPagamento().add(linkTo(
					methodOn(FormaPagamentoController.class).buscar(pedidoModel.getFormaPagamento().getId(), null))
				.withSelfRel());
		pedidoModel.getFormaPagamento().add(linkTo(FormaPagamentoController.class).withRel("formas-pagamento"));
		
		// links de restaurantes
		pedidoModel.getRestaurante().add(linkTo(
					methodOn(RestauranteController.class).buscar(pedidoModel.getRestaurante().getId()))
				.withSelfRel());
		pedidoModel.getRestaurante().add(linkTo(RestauranteController.class).withRel("restaurantes"));
		
		// links para endereço de entrega
		pedidoModel.getEnderecoEntrega().getCidade().add(linkTo(
					methodOn(CidadeController.class).buscar(pedidoModel.getEnderecoEntrega().getCidade().getId()))
				.withSelfRel());
		pedidoModel.getEnderecoEntrega().getCidade().add(linkTo(CidadeController.class).withRel("cidades"));
		
		ProdutoResumoModel produtoModel = pedidoModel.getItens().get(0).getProduto(); 
		
		produtoModel.add(linkTo(
					methodOn(RestauranteProdutoController.class).buscar(pedidoModel.getRestaurante().getId(), produtoModel.getId()))
				.withSelfRel());
		produtoModel.add(linkTo(
					methodOn(RestauranteProdutoController.class).listar(pedidoModel.getRestaurante().getId(), false))
				.withRel("produtos"));
		
		return pedidoModel;
	}
	

}
