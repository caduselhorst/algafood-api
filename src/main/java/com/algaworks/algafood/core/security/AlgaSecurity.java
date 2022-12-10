package com.algaworks.algafood.core.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AlgaSecurity {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private PedidoRepository pedidoRepository;

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Long getUsuarioId() {
		
		log.info(getAuthentication().getPrincipal().toString());
		
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("usuario_id");
	}
	
	public boolean gerenciaRestaurante(Long restauranteId) {
		
		boolean retorno = restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
		return retorno;
	}
	
	// a partir de um código do pedido, verifica se o usuário pode gerenciar (se é responsável pelo restaurante
	// para qual o pedido foi realizado
	public boolean gerenciaRestauranteDoPedido(String codigo) {
	    return pedidoRepository.isPedidoGerenciadoPor(codigo, getUsuarioId());
	} 
	
	
}
