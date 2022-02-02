package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CozinhaInputDisassembler {

	private ModelMapper modelMapper;
	
	public Cozinha toDomainObject(CozinhaInput input) {
		return modelMapper.map(input, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaInput input, Cozinha cozinha) {
		modelMapper.map(input, cozinha);
	}
	
}
