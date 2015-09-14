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
import br.com.s2it.teste.response.RouteResponse;
import br.com.s2it.teste.vos.MapVO;

/**
 * The Class ValidationUtils.
 */
public class ValidationUtils {

	/**
	 * Gets the map instance.
	 * 
	 * @param map
	 *            the map
	 * @return the map instance
	 */
	public static MapVO getMapInstance(MapVO map) {

		if (map == null) {
			return MapVO.newNull("Bad Request: Map not found.");
		} else if (map.getName() == null) {
			return MapVO.newNull("Bad Request: Map name is null.");
		} else if (map.getName().trim().length() == 0) {
			return MapVO.newNull("Validation Error: Map name is empty.");
		} else if (map.getRoutes() == null) {
			return MapVO.newNull("Bad Request: Map routes is null.");
		} else if (map.getRoutes().size() == 0) {
			return MapVO.newNull("Validation Error: Map without routes.");
		}

		return map;
	}

	/**
	 * Gets the route request instance.
	 * 
	 * @param route
	 *            the route
	 * @return the route request instance
	 */
	public static RouteRequest getRouteRequestInstance(RouteRequest route) {

		if (route == null) {
			return RouteRequest.newNull("Bad Request: Route is null.");
		} else if (route.getMapName() == null || route.getMapName().trim().length() == 0) {
			return RouteRequest.newNull("Bad Request: Map name is null or empty");
		} else if (route.getSrc() == null || route.getSrc().trim().length() == 0) {
			return RouteRequest.newNull("Bad Request: Source is null or empty.");
		} else if (route.getDst() == null || route.getDst().trim().length() == 0) {
			return RouteRequest.newNull("Bad Request: Destination is null or empty.");
		} else if (route.getAutonomy() == null) {
			return RouteRequest.newNull("Bad Request: Autonomy is null.");
		} else if (route.getAutonomy().compareTo(new BigDecimal(0)) <= 0) {
			return RouteRequest.newNull("Validation Error: Autonomy invalid or negative.");
		} else if (route.getAutonomyPrice() == null) {
			return RouteRequest.newNull("Bad Request: Price is null.");
		} else if (route.getAutonomyPrice().compareTo(new BigDecimal(0)) <= 0) {
			return RouteRequest.newNull("Validation Error: Price invalid or negative.");
		}

		return route;
	}

	/**
	 * @param response
	 * @return RouteResponse
	 */
	public static RouteResponse getRouteResponseInstance(RouteResponse response) {

		if (response == null) {
			return RouteResponse.newNull("Validation Error: Source or Destination not found on map.");
		} else if (response.getMinDistance() == Double.POSITIVE_INFINITY) {
			return RouteResponse.newNull("Validation Error: Unreachable route.");
		}

		return response;
	}

}
