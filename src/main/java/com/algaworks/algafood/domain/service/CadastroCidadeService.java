package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroCidadeService {
	
	private static final String MSG_CIDADE_EM_USO = "A cidade cadastrada com o id %d está sendo utilizada.";

	private CidadeRepository repository;
	private CadastroEstadoService estadoService;
	private CidadeInputDisassembler inputDisassembler;
	
	public List<Cidade> listar() {
		return repository.findAll();
	}
	
	public Cidade buscar(Long cidadeId) {
		
		return repository.findById(cidadeId)
				.orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
		
	}
	
	@Transactional
	public Cidade adiciona(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		
		Estado estado = buscaEstado(estadoId);
		cidade.setEstado(estado);
		
		return repository.save(cidade);
		
	}

	private Estado buscaEstado(Long estadoId) {
		
		return estadoService.buscar(estadoId);

	}
	
	@Transactional
	public Cidade altera(Long cidadeId, CidadeInput cidade) {
		
		Cidade cidadeAtual = buscar(cidadeId);
		
		Estado estado = buscaEstado(cidade.getEstado().getId());
		
		inputDisassembler.copyToDomainObject(cidade, cidadeAtual);
		
		cidadeAtual.setEstado(estado);
		
		return repository.save(cidadeAtual);
		
	}
	
	@Transactional
	public void excluir(Long cidadeId) {
		
		try {
			repository.deleteById(cidadeId);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, cidadeId));
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);
		}
		
		
	}

}
