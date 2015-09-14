/*
 * 
 * Copyright (c) 2005, 2015, ${deoit} and/or its affiliates. All rights reserved.
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
package br.com.s2it.teste.response;

import br.com.s2it.teste.validations.Nullable;

/**
 * The Class GenericResponse.
 *
 * @author Weysler M. Silveira
 * @email <Weysler@DeOIT.com.br> @date Sep 13, 2015 @time 6:59:49 PM
 */
public class GenericResponse implements Nullable {

	/** The description. */
	private String description;

	/**
	 * Instantiates a new generic response.
	 */
	public GenericResponse() {

	}

	/**
	 * Instantiates a new generic response.
	 *
	 * @param description the description
	 */
	public GenericResponse(String description) {

		super();
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see br.com.s2it.teste.validations.Nullable#descricaoFalha()
	 */
	public String descricaoFalha() {

		return "";
	}

	/* (non-Javadoc)
	 * @see br.com.s2it.teste.validations.Nullable#ehNulo()
	 */
	public boolean ehNulo() {

		return false;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "GenericResponse [description=" + description + "]";
	}
}
