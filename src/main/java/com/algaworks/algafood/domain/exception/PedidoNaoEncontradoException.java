package com.algaworks.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5571056792620380950L;
		
	public PedidoNaoEncontradoException(String codigo) {
		super(String.format("Não foi encontrado um pedido de código %s.", codigo));
	}

}
