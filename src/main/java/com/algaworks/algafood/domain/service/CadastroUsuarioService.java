package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.api.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	private static final String MENSAGEM_USUARIO_EMAIL_EXISTENTE = "Já existe um usuário com o email %s cadastrado";

	private static final String MSG_USUARIO_EM_USO = "O usuario cadastrado com o id %d está sendo utilizado.";
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioModelAssembler assembler;
	
	@Autowired
	private UsuarioInputDisassembler disassembler;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	public List<UsuarioModel> listar() {
		return assembler.toCollectionModel(repository.findAll());
	}
	
	public UsuarioModel buscar(Long usuarioId) {
		return assembler.toModel(buscarOuFalhar(usuarioId));
	}
	
	@Transactional
	public UsuarioModel adicionar(UsuarioInput usuarioInput) {
		Usuario usuario = disassembler.toDomainObject(usuarioInput);
		
		Optional<Usuario> usuarioExistente = buscaEntidadePeloEmail(usuarioInput.getEmail());
		
		if(usuarioExistente.isPresent()) {
			throw new NegocioException(String.format(MENSAGEM_USUARIO_EMAIL_EXISTENTE, 
					usuarioInput.getEmail()));
		}
		
		return assembler.toModel(repository.save(usuario));
	}
	
	@Transactional
	public UsuarioModel alterar(Long usuarioId, UsuarioInput usuarioInput) {
		Usuario usuarioAtual = buscarOuFalhar(usuarioId);
		
		Optional<Usuario> usuarioEmail = buscaEntidadePeloEmail(usuarioInput.getEmail());
		
		// teste para verificar se já existe um usuario com o email
		if(usuarioEmail.isPresent() && !usuarioEmail.get().equals(usuarioAtual)) {
			throw new NegocioException(String.format(MENSAGEM_USUARIO_EMAIL_EXISTENTE, 
					usuarioInput.getEmail())); 
		}
		
		disassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		return assembler.toModel(usuarioAtual);
	}
	
	@Transactional
	public void excluir(Long usuarioId) {
		try {
			repository.deleteById(usuarioId);
			repository.flush();

		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, usuarioId));
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(usuarioId);
		}
	}
	
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		
		if(usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário");
		}
		
		usuario.setSenha(novaSenha);
	}
	
	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = cadastroGrupo.buscarEntidade(grupoId);
		
		usuario.associarGrupo(grupo);
	}
	
	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = cadastroGrupo.buscarEntidade(grupoId);
		
		usuario.desassociarGrupo(grupo);
	}
	
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		return repository.findById(usuarioId)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
	
	public Optional<Usuario> buscaEntidadePeloEmail(String email) {
		return repository.findByEmail(email);
	}
	
}
