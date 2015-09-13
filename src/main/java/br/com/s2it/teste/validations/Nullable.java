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
package br.com.s2it.teste.validations;

/**
 * The Interface Nullable.
 */
public interface Nullable {

	/**
	 * Descricao falha.
	 *
	 * @return the string
	 */
	String descricaoFalha();
	
	/**
	 * Eh nulo.
	 *
	 * @return true, if successful
	 */
	boolean ehNulo();
}
