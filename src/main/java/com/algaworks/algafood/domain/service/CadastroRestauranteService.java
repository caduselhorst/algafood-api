package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroRestauranteService {
	
	private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido pois está em uso.";
	
	private RestauranteRepository repository;
	
	private CadastroCozinhaService cozinhaService;
	private CadastroCidadeService cidadeService;
	private CadastroFormaPagamentoService formaPagamentoService;
	private CadastroUsuarioService cadastroUsuario;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		
		Cozinha cozinha = cozinhaService.buscarOuFalhar(
				restaurante.getCozinha().getId());
		Cidade cidade = cidadeService.buscar(restaurante.getEndereco().getCidade().getId());
				
		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);
		
		return repository.save(restaurante);
	}
	
	public List<Restaurante> listar() {
		List<Restaurante> restaurantes = repository.findAll();
		
		return restaurantes;
	}
	
	public Restaurante buscar(Long id) {
		
		Restaurante r = repository.findById(id)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(id));

		return r;
		
	}
	
	@Transactional
	public void excluir(Long restauranteId) {
		try {
			
			repository.deleteById(restauranteId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(
					MSG_RESTAURANTE_EM_USO, restauranteId));
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(restauranteId);
		}
	}
	
	@Transactional
	public void ativar(Long restauranteId) {
		
		Restaurante restauranteAtual = buscar(restauranteId);
		restauranteAtual.ativar();
		
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		
		Restaurante restauranteAtual = buscar(restauranteId);
		restauranteAtual.inativar();
		
	}
	
	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restaurante = buscar(restauranteId);
		restaurante.abrir();
	}
	
	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restaurante = buscar(restauranteId);
		restaurante.fechar();
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		
		Restaurante restaurante = buscar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscarEntidade(formaPagamentoId);
		
		restaurante.removerFormaPagamento(formaPagamento);
		
	}
	
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		
		Restaurante restaurante = buscar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscarEntidade(formaPagamentoId);
		
		restaurante.adicionarFormaPagamento(formaPagamento);
		
	}
	
	@Transactional
	public void associarUsuarioResponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscar(restauranteId);
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
		
		restaurante.associaUsuarioResponsavel(usuario);
		
	}
	
	@Transactional
	public void desassociarUsuarioResponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscar(restauranteId);
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
		
		restaurante.desassociaUsuarioResponsavel(usuario);
	}
	
	@Transactional
	public void ativar(List<Long> restauranteId) {
		restauranteId.forEach(this::ativar);
	}
	
	@Transactional
	public void inativar(List<Long> restauranteId) {
		restauranteId.forEach(this::inativar);
	}

}
