package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	
	public PedidoModel toModel(Pedido pedido)  {
		
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		
		modelMapper.map(pedido, pedidoModel);
		
		
		pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		
		if(pedido.podeSerConfirmado()) {
			pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
		}
		
		if(pedido.podeSerEntregue()) {
			pedidoModel.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
		}
		
		if(pedido.podeSerCancelado()) {
			pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
		}
		
		
		// links de clientes
		pedidoModel.getCliente().add(
				algaLinks.linkToCliente(pedidoModel.getCliente().getId()));

		
		// links de formas de pagamento
		pedidoModel.getFormaPagamento().add(
				algaLinks.linkToFormaPagamento(pedidoModel.getFormaPagamento().getId()));
		
		// links de restaurantes
		pedidoModel.getRestaurante().add(algaLinks.linkToRestaurante(pedidoModel.getRestaurante().getId()));

		
		// links para endereço de entrega
		pedidoModel.getEnderecoEntrega().getCidade().add(algaLinks.linkToCidade(pedidoModel.getEnderecoEntrega().getCidade().getId()));

		
		pedidoModel.getItens().forEach(i -> 
			i.getProduto().add(
					algaLinks.linkToProduto(pedidoModel.getRestaurante().getId(), i.getProduto().getId())));
		
		return pedidoModel;
	}
	

}
