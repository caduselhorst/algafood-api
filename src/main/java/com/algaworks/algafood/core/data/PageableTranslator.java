package com.algaworks.algafood.core.data;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableTranslator {
	
	public static Pageable translate(Pageable pageable, 
			Map<String, String> fieldsMapping) {
		
		var orders = pageable.getSort()
			.stream()
			.filter(o -> fieldsMapping.containsKey(o.getProperty()))
			.map(o -> new Sort.Order(o.getDirection(), fieldsMapping.get(o.getProperty())))
			.collect(Collectors.toList());
		
		System.out.println(orders);
		
		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
		
	}

}
