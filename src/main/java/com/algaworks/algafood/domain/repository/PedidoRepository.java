package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.algafood.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
	
	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	public List<Pedido> findAll();
	
	public Optional<Pedido> findByCodigo(String codigo);
	
	// chama a query do orm.xml
	public boolean isPedidoGerenciadoPor(String codigoPedido, Long usuarioId);
}
