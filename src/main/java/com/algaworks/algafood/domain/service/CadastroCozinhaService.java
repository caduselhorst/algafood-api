package com.algaworks.algafood.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = 
			"Cozinha de código %d não pode ser removida pois está em uso.";
	
	private CozinhaRepository repository;

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return repository.save(cozinha);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();

		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
		} catch (EmptyResultDataAccessException e) {

			throw new CozinhaNaoEncontradaException(id);
		}
	}

	public Cozinha buscarOuFalhar(Long cozinhaId) {

		return repository.findById(cozinhaId).orElseThrow(
				() -> new CozinhaNaoEncontradaException(cozinhaId));
	}

}
