package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class GrupoInputDisassembler {
	
	private ModelMapper mapper;
	
	public Grupo toDomainObject(GrupoInput grupoInput) {
		return mapper.map(grupoInput, Grupo.class);
	}
	
	public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
		mapper.map(grupoInput, grupo);
	}

}
