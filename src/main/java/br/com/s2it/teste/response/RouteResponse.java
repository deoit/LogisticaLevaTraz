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
package br.com.s2it.teste.response;

import java.math.BigDecimal;
import java.util.List;

import br.com.s2it.teste.validations.Nullable;

/**
 * The Class RouteResponse.
 */
public class RouteResponse implements Nullable {

	/** The path. */
	private List<String> path;
	
	/** The cost. */
	private BigDecimal cost;
	
	/** The min distance. */
	private Double minDistance;
	
	/** The description. */
	private String description;

	/**
	 * New null.
	 *
	 * @param descricao the descricao
	 * @return the route response null
	 */
	public static RouteResponseNull newNull(String descricao) {

		RouteResponseNull reponse = new RouteResponseNull();
		reponse.setDescricao(descricao);

		return reponse;
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
	 * Gets the path.
	 *
	 * @return the path
	 */
	public List<String> getPath() {

		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(List<String> path) {

		this.path = path;
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public BigDecimal getCost() {

		return cost;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(BigDecimal cost) {

		this.cost = cost;
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

	/**
	 * Gets the min distance.
	 *
	 * @return the min distance
	 */
	public Double getMinDistance() {

		return minDistance;
	}

	/**
	 * Sets the min distance.
	 *
	 * @param minDistance the new min distance
	 */
	public void setMinDistance(Double minDistance) {

		this.minDistance = minDistance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "RouteResponse [path=" + path + ", cost=" + cost + ", description=" + description + "]";
	}

}
