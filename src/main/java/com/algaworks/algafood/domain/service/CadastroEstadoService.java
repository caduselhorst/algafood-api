package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroEstadoService {
	
	private static final String MSG_ESTADO_EM_USO = "O estado cadastrado com o id %d está sendo utilizado.";
	
	private EstadoRepository estadoRepository;
	private EstadoInputDisassembler disassembler;
	
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	public Estado buscar(Long estadoId) {
		
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
		
	}
	
	@Transactional
	public Estado adiciona(Estado estado) {
		
		return estadoRepository.save(estado);
		
	}
	
	public Estado altera(Long estadoId, EstadoInput estado) {
		
		Estado estadoAtual = buscar(estadoId);
		
		disassembler.copyToDomainObject(estado, estadoAtual);
		
		return estadoRepository.save(estadoAtual);
		
	}
	
	@Transactional
	public void excluir(Long estadoId) {
				
		try {
			estadoRepository.deleteById(estadoId);
			estadoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, estadoId));
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException (estadoId);
		}
		
		
	}

}
