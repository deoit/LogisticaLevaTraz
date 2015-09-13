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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.s2it.teste.converters.RouteTypeConverter;
import br.com.s2it.teste.dijkstra.Point;
import br.com.s2it.teste.dijkstra.RouteFinder;
import br.com.s2it.teste.validations.MapNull;
import br.com.s2it.teste.validations.Nullable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * The Class MapVO.
 */
@DynamoDBTable(tableName = "Map-S2IT-Test")
public class MapVO implements Nullable {

	/** The name. */
	private String name;

	/** The routes. */
	private List<RouteVO> routes;

	/** The points. */
	public List<Point> points;

	/**
	 * New null.
	 * 
	 * @param descricao
	 *            the descricao
	 * @return the map vo
	 */
	public static MapVO newNull(String descricao) {

		MapNull mapNull = new MapNull();
		mapNull.setDescricao(descricao);

		return mapNull;
	}

	/**
	 * Setup configuration and instantiation of all points for these routes.
	 */
	public void setupPoints() {

		Set<String> pointsHash = obterHashPontos();
		this.points = RouteFinder.instanciarPontos(pointsHash);
		RouteFinder.atribuiAdjacencias(this.points, this.routes);
	}

	/**
	 * Obter hash pontos.
	 * 
	 * @return the sets the
	 */
	private Set<String> obterHashPontos() {

		Set<String> pontosHash = new HashSet<String>();

		for (RouteVO route : routes) {
			Collections.addAll(pontosHash, route.obterSrcDst());
		}

		return pontosHash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.s2it.teste.validations.Nullable#ehNulo()
	 */
	public boolean ehNulo() {

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.s2it.teste.validations.Nullable#descricaoFalha()
	 */
	public String descricaoFalha() {

		return "";
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	@DynamoDBHashKey(attributeName = "Name")
	public String getName() {

		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * Gets the routes.
	 * 
	 * @return the routes
	 */
	@DynamoDBAttribute(attributeName = "Routes")
	@DynamoDBMarshalling(marshallerClass = RouteTypeConverter.class)
	public List<RouteVO> getRoutes() {

		return routes;
	}

	/**
	 * Sets the routes.
	 * 
	 * @param routes
	 *            the new routes
	 */
	public void setRoutes(List<RouteVO> routes) {

		this.routes = routes;
	}

	@Override
	public String toString() {

		return "MapVO [name=" + name + ", routes=" + routes + "]";
	}

}