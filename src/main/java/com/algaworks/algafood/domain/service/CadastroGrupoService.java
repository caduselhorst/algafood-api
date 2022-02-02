package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroGrupoService {
	
	private static final String MSG_GRUPO_EM_USO = "A cidade cadastrada com o id %d está sendo utilizada.";
	
	private GrupoRepository repository;
	private GrupoModelAssembler assembler;
	private GrupoInputDisassembler disassembler;
	private CadastroPermissaoService cadastroPermissao;
	
	
	public List<GrupoModel> listar() {
		return assembler.toCollectionModel(repository.findAll());
	}
	
	public GrupoModel buscar(Long grupoId) {
		return assembler.toModel(buscarEntidade(grupoId));
	}
	
	@Transactional
	public GrupoModel adicionar(GrupoInput grupoInput) {
		
		Grupo novoGrupo = disassembler.toDomainObject(grupoInput);
		
		return assembler.toModel(repository.save(novoGrupo));
		
	}
	
	@Transactional
	public GrupoModel alterar(Long grupoId, GrupoInput grupoInput) {
		
		Grupo grupoAtual = buscarEntidade(grupoId);
		
		disassembler.copyToDomainObject(grupoInput, grupoAtual);
		
		return assembler.toModel(repository.save(grupoAtual));
		
	}
	
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarEntidade(grupoId);
		Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);
		
		grupo.associarPermissao(permissao);
	}
	
	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarEntidade(grupoId);
		Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);
		
		grupo.desassociarPermissao(permissao);
	}
	
	@Transactional
	public void excluir(Long grupoId) {
		try {
			repository.deleteById(grupoId);
			repository.flush();

		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, grupoId));
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		}
	}
	
	public Grupo buscarEntidade(Long grupoId) {
		return repository.findById(grupoId)
				.orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}

}
