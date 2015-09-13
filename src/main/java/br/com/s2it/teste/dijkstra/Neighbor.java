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
package br.com.s2it.teste.dijkstra;

/**
 * The Class Neighbor.
 */
public class Neighbor {

	/** The target. */
	public final Point target;
	
	/** The weight. */
	public final double weight;

	/**
	 * Instantiates a new neighbor.
	 *
	 * @param argTarget the arg target
	 * @param argWeight the arg weight
	 */
	public Neighbor(Point argTarget, double argWeight) {

		target = argTarget;
		weight = argWeight;
	}
}