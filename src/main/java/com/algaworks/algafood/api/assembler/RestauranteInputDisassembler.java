package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RestauranteInputDisassembler {
	
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInput input) {
		
		return modelMapper.map(input, Restaurante.class);
		
	}
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		/*
		 * Incluido para evitar a exception
		 * org.springframework.orm.jpa.JpaSystemException: identifier of an instance of 
		 * com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2; 
		 */
		restaurante.setCozinha(new Cozinha());
		if(restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		
		
		modelMapper.map(restauranteInput, restaurante);
	}

}
