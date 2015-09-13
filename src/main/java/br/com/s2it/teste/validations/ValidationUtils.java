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

import java.math.BigDecimal;

import br.com.s2it.teste.requests.RouteRequest;
import br.com.s2it.teste.vos.MapVO;

/**
 * The Class ValidationUtils.
 */
public class ValidationUtils {

	/**
	 * Gets the map instance.
	 *
	 * @param map the map
	 * @return the map instance
	 */
	public static MapVO getMapInstance(MapVO map) {

		if (map == null) {
			return MapVO.newNull("Map not found.");
		}else if (map.getName() == null) {
			return MapVO.newNull("Map name is null.");
		}else if (map.getName().trim().length() == 0) {
			return MapVO.newNull("Map name is empty.");
		}else if (map.getRoutes() == null || map.getRoutes().size() == 0) {
			return MapVO.newNull("Map without routes.");
		}
		
		return map;
	}
	
	/**
	 * Gets the route request instance.
	 *
	 * @param route the route
	 * @return the route request instance
	 */
	public static RouteRequest getRouteRequestInstance(RouteRequest route) {

		if (route == null) {
			return RouteRequest.newNull("Requsicao nula");
		}else if (route.getMapName() == null || route.getMapName().trim().length() == 0) {
			return RouteRequest.newNull("Nome do mapa nulo ou vazio");
		}else if (route.getSrc() == null || route.getSrc().trim().length() == 0) {
			return RouteRequest.newNull("Origem nula ou vazia");
		}else if (route.getDst() == null || route.getDst().trim().length() == 0) {
			return RouteRequest.newNull("Destino nulo ou vazio");
		}else if (route.getAutonomy() == null || route.getAutonomy().compareTo(new BigDecimal(0)) <= 0) {
			return RouteRequest.newNull("Autonomia nula, negativa ou inválida");
		}else if (route.getAutonomyPrice() == null || route.getAutonomyPrice().compareTo(new BigDecimal(0)) <= 0) {
			return RouteRequest.newNull("Preco nulo, negativo ou inválido");
		}
		
		return route;
	}
}
