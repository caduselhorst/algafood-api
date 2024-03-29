package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	@Autowired
	private CadastroCidadeService cadastroCidade;
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	@Autowired
	private CadastroProdutoService cadastroProduto;
	
	public Pedido buscarOuFalhar(String codigo) {
		return pedidoRepository.findByCodigo(codigo)
				.orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
	}
	
	public RepresentationModel<PedidoModel> buscar(String codigo) {
		return pedidoModelAssembler.toModel(buscarOuFalhar(codigo));
	}
	
	public CollectionModel<PedidoResumoModel> listar() {
		return pedidoResumoModelAssembler.toCollectionModel(pedidoRepository.findAll());
	}
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
	    validarPedido(pedido);
	    validarItens(pedido);

	    pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
	    pedido.calculaValorPedido();

	    return pedidoRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
	    Cidade cidade = cadastroCidade.buscar(pedido.getEnderecoEntrega().getCidade().getId());
	    Usuario cliente = cadastroUsuario.buscarOuFalhar(pedido.getCliente().getId());
	    Restaurante restaurante = cadastroRestaurante.buscar(pedido.getRestaurante().getId());
	    FormaPagamento formaPagamento = cadastroFormaPagamento.buscarEntidade(pedido.getFormaPagamento().getId());

	    pedido.getEnderecoEntrega().setCidade(cidade);
	    pedido.setCliente(cliente);
	    pedido.setRestaurante(restaurante);
	    pedido.setFormaPagamento(formaPagamento);
	    
	    if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
	        throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
	                formaPagamento.getDescricao()));
	    }
	}

	private void validarItens(Pedido pedido) {
	    pedido.getItens().forEach(item -> {
	        Produto produto = cadastroProduto.buscar(
	                pedido.getRestaurante().getId(), item.getProduto().getId());
	        
	        item.setPedido(pedido);
	        item.setProduto(produto);
	        item.setPrecoUnitario(produto.getPreco());
	    });
	}

}
