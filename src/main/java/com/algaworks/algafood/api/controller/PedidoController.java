package com.algaworks.algafood.api.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.api.openapi.controller.PedidoControllerOpenApi;
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private EmissaoPedidoService cadastroPedido;
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	@Autowired
	private PagedResourcesAssembler<Pedido> pedidoPagedResouceAssembler;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@CheckSecurity.Pedidos.PodePesquisar
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
	
		Pageable pageableTraduzido = traduzirPageable(pageable); 
		
		Page<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);
		
		todosPedidos = new PageWrapper<>(todosPedidos, pageable);
		
		PagedModel<PedidoResumoModel> pagedPedidos = pedidoPagedResouceAssembler.toModel(todosPedidos, pedidoResumoModelAssembler);
		
		
		
		return pagedPedidos;
	}
	
	@CheckSecurity.Pedidos.PodeBuscar
	@Override
	@GetMapping(value  = "/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RepresentationModel<PedidoModel> buscar(@PathVariable String codigoPedido) {
		return cadastroPedido.buscar(codigoPedido);
	}
	
	@CheckSecurity.Pedidos.PodeCriar
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
	    try {
	        Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

	        novoPedido.setCliente(new Usuario());
	        novoPedido.getCliente().setId(algaSecurity.getUsuarioId());

	        novoPedido = cadastroPedido.emitir(novoPedido);

	        return pedidoModelAssembler.toModel(novoPedido);
	    } catch (EntidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}
	
	
	private Pageable traduzirPageable(Pageable apiPageble) {

		HashMap<String, String> mapeamento = new HashMap<>();

		mapeamento.put("codigo", "codigo");
		mapeamento.put("subtotal", "subtotal");
		mapeamento.put("taxaFrete", "taxaFrete");
		mapeamento.put("valorTotal", "valorTotal");
		mapeamento.put("dataCriacao", "dataCriacao");
		mapeamento.put("cliente.nome", "cliente.nome");
		mapeamento.put("restaurante.nome", "restaurante.nome");
		
		return PageableTranslator.translate(apiPageble, mapeamento);
				
	}
	
	
}
