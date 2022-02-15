package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroFormaPagamentoService {
	
	private static final String MSG_FORMA_PAGAMENTO_EM_USO = 
			"A forma de pagamento de código %d não pode ser removida pois está em uso.";
	
	
	
	private FormaPagamentoRepository repository;
	private FormaPagamentoModelAssembler assembler;
	private FormaPagamentoInputDisassembler disassembler;
	
	

	public CollectionModel<FormaPagamentoModel> listar() {
		
		List<FormaPagamento> formasPagamento = repository.findAll();
		return assembler.toCollectionModel(formasPagamento);
		
	}
	
	public FormaPagamentoModel buscar(Long formaPagamentoId) {
		FormaPagamento formaPagamento = buscarEntidade(formaPagamentoId);
		
		return assembler.toModel(formaPagamento);
	}

	
	@Transactional
	public FormaPagamentoModel salvar(FormaPagamentoInput formaPagamentoInput) {
		
		FormaPagamento formaPagamento = disassembler.toDomainObject(formaPagamentoInput);
		formaPagamento = repository.save(formaPagamento);
		
		return assembler.toModel(formaPagamento);
		
	}
	
	@Transactional
	public FormaPagamentoModel alterar(Long formaPagamentoId, FormaPagamentoInput formaPagamentoInput) {
		
		FormaPagamento formaPagamentoAtual = buscarEntidade(formaPagamentoId);	
		disassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		
		return assembler.toModel(repository.save(formaPagamentoAtual));
		
	}
	
	@Transactional
	public void excluir(Long formaPagamentoId) {
		try {
			repository.deleteById(formaPagamentoId);
			repository.flush();

		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, formaPagamentoId));
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(formaPagamentoId);
		}
	}
	
	
	public FormaPagamento buscarEntidade(Long formaPagamentoId) {
		return repository.findById(formaPagamentoId)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
	}
	
}
