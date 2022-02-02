package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EstadoInputDisassembler {
	
	private ModelMapper modelMapper;
	
	public Estado toDomainObject(EstadoInput input) {
		return modelMapper.map(input, Estado.class);
	}
	

	public void copyToDomainObject(EstadoInput input, Estado estado) {
		modelMapper.map(input, estado);
	}

}
