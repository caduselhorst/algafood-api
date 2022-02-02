package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.domain.model.Grupo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class GrupoModelAssembler {

	private ModelMapper mapper;
	
	public GrupoModel toModel(Grupo grupo) {
		return mapper.map(grupo, GrupoModel.class);
	}
	
	public List<GrupoModel> toCollectionModel(Collection<Grupo> grupos) {
		return grupos.stream()
				.map(g -> toModel(g))
				.collect(Collectors.toList());
	}
	
}
