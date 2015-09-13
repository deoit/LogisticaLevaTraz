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
package br.com.s2it.teste.vos;

/**
 * The Class RouteVO.
 */
public class RouteVO {

	/** The src. */
	private String src;
	
	/** The dst. */
	private String dst;
	
	/** The distance. */
	private Integer distance;

	/**
	 * Gets the src.
	 *
	 * @return the src
	 */
	public String getSrc() {

		return src;
	}

	/**
	 * Sets the src.
	 *
	 * @param src the new src
	 */
	public void setSrc(String src) {

		this.src = src;
	}

	/**
	 * Gets the dst.
	 *
	 * @return the dst
	 */
	public String getDst() {

		return dst;
	}

	/**
	 * Sets the dst.
	 *
	 * @param dst the new dst
	 */
	public void setDst(String dst) {

		this.dst = dst;
	}

	/**
	 * Gets the distance.
	 *
	 * @return the distance
	 */
	public Integer getDistance() {

		return distance;
	}

	/**
	 * Sets the distance.
	 *
	 * @param distance the new distance
	 */
	public void setDistance(Integer distance) {

		this.distance = distance;
	}
	
	/**
	 * Obter src dst.
	 *
	 * @return the string[]
	 */
	public String[] obterSrcDst(){
		return new String[] {this.src, this.dst};
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "RouteVO [src=" + src + ", dst=" + dst + ", distance=" + distance + "]";
	}
}