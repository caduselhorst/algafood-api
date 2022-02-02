package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.domain.model.FormaPagamento;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class FormaPagamentoModelAssembler {

	private ModelMapper mapper;
	
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		return mapper.map(formaPagamento, FormaPagamentoModel.class);
	}
	
	public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formasPagamento) {
		return formasPagamento.stream()
				.map(f -> toModel(f))
				.collect(Collectors.toList());
	}
	
}
