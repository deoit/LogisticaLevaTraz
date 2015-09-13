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
 * The Class Point.
 */
public class Point implements Comparable<Point> {

	/** The name. */
	public final String name;
	
	/** The adjacencias. */
	public Neighbor[] adjacencias;
	
	/** The min distance. */
	public double minDistance = Double.POSITIVE_INFINITY;
	
	/** The anterior. */
	public Point anterior;
	
	/**
	 * Instantiates a new point.
	 *
	 * @param argName the arg name
	 */
	public Point(String argName) {

		name = argName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Point other) {

		return Double.compare(minDistance, other.minDistance);
	}
	
}
