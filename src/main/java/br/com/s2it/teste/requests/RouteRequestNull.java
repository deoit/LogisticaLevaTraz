/*
 * 
 * Copyright (c) 2005, 2015, DeOIT Tecnologia LTDA-ME. and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 * Please contact DeOIT Solutions, Av.Tiradentes 1459, São Paulo, Guarulhos SP CEP 
 * 07115020 BR or visit www.deoit.com.br if you need additional information or have any
 * questions.
 * 
 * @author Weysler M. S. Silveira - weyslerms@gmail.com
 * @version 0.0.1 levaetraz
 * 
 */
package br.com.s2it.teste.requests;

import br.com.s2it.teste.validations.Nullable;

/**
 * The Class RouteRequestNull.
 */
public class RouteRequestNull extends RouteRequest implements Nullable {

	/** The descricao. */
	private String descricao;

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Sets the descricao.
	 *
	 * @param descricao the new descricao
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}
	
	/* (non-Javadoc)
	 * @see br.com.s2it.teste.requests.RouteRequest#ehNulo()
	 */
	@Override
	public boolean ehNulo() {

		return true;
	}
	
	/* (non-Javadoc)
	 * @see br.com.s2it.teste.requests.RouteRequest#descricaoFalha()
	 */
	@Override
	public String descricaoFalha() {
		return this.descricao;
	}
}
