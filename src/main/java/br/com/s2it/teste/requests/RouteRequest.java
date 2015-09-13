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

import java.math.BigDecimal;

import br.com.s2it.teste.validations.Nullable;

/**
 * The Class RouteRequest.
 */
public class RouteRequest implements Nullable {

	/** The map name. */
	private String mapName;
	
	/** The src. */
	private String src;
	
	/** The dst. */
	private String dst;
	
	/** The autonomy. */
	private BigDecimal autonomy;
	
	/** The autonomy price. */
	private BigDecimal autonomyPrice;

	/**
	 * New null.
	 *
	 * @param descricao the descricao
	 * @return the route request
	 */
	public static RouteRequest newNull(String descricao) {

		RouteRequestNull routeNull = new RouteRequestNull();
		routeNull.setDescricao(descricao);

		return routeNull;
	}

	/* (non-Javadoc)
	 * @see br.com.s2it.teste.validations.Nullable#ehNulo()
	 */
	public boolean ehNulo() {

		return false;
	}

	/* (non-Javadoc)
	 * @see br.com.s2it.teste.validations.Nullable#descricaoFalha()
	 */
	public String descricaoFalha() {

		return "";
	}

	/**
	 * Gets the map name.
	 *
	 * @return the map name
	 */
	public String getMapName() {

		return mapName;
	}

	/**
	 * Sets the map name.
	 *
	 * @param mapName the new map name
	 */
	public void setMapName(String mapName) {

		this.mapName = mapName;
	}

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
	 * Gets the autonomy.
	 *
	 * @return the autonomy
	 */
	public BigDecimal getAutonomy() {

		return autonomy;
	}

	/**
	 * Sets the autonomy.
	 *
	 * @param autonomy the new autonomy
	 */
	public void setAutonomy(BigDecimal autonomy) {

		this.autonomy = autonomy;
	}

	/**
	 * Gets the autonomy price.
	 *
	 * @return the autonomy price
	 */
	public BigDecimal getAutonomyPrice() {

		return autonomyPrice;
	}

	/**
	 * Sets the autonomy price.
	 *
	 * @param autonomyPrice the new autonomy price
	 */
	public void setAutonomyPrice(BigDecimal autonomyPrice) {

		this.autonomyPrice = autonomyPrice;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "RouteRequest [mapName=" + mapName + ", src=" + src + ", dst=" + dst + ", autonomy=" + autonomy + ", autonomyPrice=" + autonomyPrice + "]";
	}

}
