package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RestauranteModelAssembler {
	
	private ModelMapper modelMapper;
	
	public RestauranteModel toModel(Restaurante restaurante) {
		
		return modelMapper.map(restaurante, RestauranteModel.class);
		
	}
	
	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream()
				.map(r -> toModel(r))
				.collect(Collectors.toList());
	}

}
