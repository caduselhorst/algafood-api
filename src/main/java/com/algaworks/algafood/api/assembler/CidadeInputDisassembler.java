package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CidadeInputDisassembler {
	
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeInput input) {
		return modelMapper.map(input, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput input, Cidade cidade) {
		
		/*
		 * Incluido para evitar a exception
		 * org.springframework.orm.jpa.JpaSystemException: identifier of an instance of 
		 * com.algaworks.algafood.domain.model.Estado was altered from 1 to 2; 
		 */
		cidade.setEstado(new Estado());
		
		modelMapper.map(input, cidade);
		
	}

}
