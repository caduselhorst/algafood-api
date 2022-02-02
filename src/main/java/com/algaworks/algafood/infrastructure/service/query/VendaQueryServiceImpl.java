package com.algaworks.algafood.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.core.validation.TimeOffset;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro, @TimeOffset String timeOffset) {
		
//		String pattern = "^[+-](0[0-9]|1[0-2]):00$";
//		
//		if(!timeOffset.matches(pattern)) {
//			throw new NegocioException("Time offset incorreto. Utilize o formato +00:00");
//		}
		
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);
		
		/*
		 * Filtros
		 */
		var predicates = new ArrayList<>();
		
		// pedidos CONFIRMADOS e ENTREGUES
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		if(filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}
		
		if(filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
		}
		
		if(filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
		}
		
		/*
		 * Constrói uma chamada de função no banco de dados
		 */
		var functionConvertTzDataCriacao = builder.function("convert_tz", 
				Date.class, root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeOffset));
		var functionDateDataCriacao = builder.function("date", Date.class, functionConvertTzDataCriacao);
		
		var selection = builder.construct(VendaDiaria.class, 
				functionDateDataCriacao, 
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		query.select(selection);
		query.where(predicates.toArray(new Predicate[] {}));
		query.groupBy(functionDateDataCriacao);
		query.orderBy(builder.asc(functionDateDataCriacao));
		
		return manager.createQuery(query).getResultList();
	}

}
