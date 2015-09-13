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
package br.com.s2it.teste.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.s2it.teste.dijkstra.Point;
import br.com.s2it.teste.dijkstra.RouteFinder;
import br.com.s2it.teste.requests.RouteRequest;
import br.com.s2it.teste.responde.RouteResponse;
import br.com.s2it.teste.utils.DynamoDBUtils;
import br.com.s2it.teste.validations.ValidationUtils;
import br.com.s2it.teste.vos.MapVO;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

/**
 * The Class Main.
 */
public class Main {

	/**
	 * Save map handler.
	 *
	 * @param mapRequest the map request
	 * @param context the context
	 * @return the map
	 */
	public static Map<String, String> saveMapHandler(MapVO mapRequest, Context context) {

		LambdaLogger logger = context.getLogger();
		MapVO map = ValidationUtils.getMapInstance(mapRequest);
		Map<String, String> message = new HashMap<String, String>();

		if (!map.ehNulo()) {

			String mapName = map.getName();
			logger.log("Saving Map: " + mapName + " \n");
			DynamoDBUtils.saveMap(map);
			message.put("Info", "done!");

			return message;
		}

		message.put("Error", "MapVO data model validation fail.");
		return message;
	}

	/**
	 * Find best route handler.
	 *
	 * @param routeRequest the route request
	 * @param context the context
	 * @return the route response
	 */
	public static RouteResponse findBestRouteHandler(RouteRequest routeRequest, Context context) {

		LambdaLogger logger = context.getLogger();
		RouteRequest req = ValidationUtils.getRouteRequestInstance(routeRequest);

		if (!req.ehNulo()) {

			String mapName = req.getMapName();
			logger.log("Loading Map: " + mapName + " \n");
			MapVO mapRetrieved = DynamoDBUtils.loadMap(mapName);
			MapVO map = ValidationUtils.getMapInstance(mapRetrieved);
			
			if(!map.ehNulo()){
				return makeResponse(req, map);
			}
			
			return RouteResponse.newNull(map.descricaoFalha());
		}
		
		return RouteResponse.newNull("RouteRequest data model validation fail.");
	}

	/**
	 * Make response.
	 *
	 * @param req the req
	 * @param map the map
	 * @return the route response
	 */
	private static RouteResponse makeResponse(RouteRequest req, MapVO map) {

		RouteResponse response = findRoute(req.getSrc(), req.getDst(), map);

		if (response != null) {

			BigDecimal autonomy = req.getAutonomy();
			BigDecimal autonomyPrice = req.getAutonomyPrice();
			Double minDistance = response.getMinDistance();

			if (minDistance != Double.POSITIVE_INFINITY) {
				BigDecimal distance = new BigDecimal(minDistance);

				if (autonomy.compareTo(distance) >= 0) {
					response.setCost(autonomyPrice);
				} else {
					BigDecimal result = distance.divide(autonomy, RoundingMode.HALF_UP).multiply(autonomyPrice);
					response.setCost(result);
				}
			} else {
				response = RouteResponse.newNull("Unreachable route");
			}

			return response;
		}

		return RouteResponse.newNull("Source or Destination not found on map " + map.getName() + ".");
	}

	/**
	 * Find route.
	 *
	 * @param src the src
	 * @param dst the dst
	 * @param map the map
	 * @return the route response
	 */
	public static RouteResponse findRoute(String src, String dst, MapVO map) {

		// Inicializa pontos e calcula adjacencias
		map.setupPoints();

		List<Point> points = map.points;
		Point pSrc = RouteFinder.obterPonto(src, points);
		Point pDst = RouteFinder.obterPonto(dst, points);

		if (pSrc != null && pDst != null) {

			RouteFinder.calculaCaminhosOrigem(pSrc);
			List<Point> path = RouteFinder.buscaCaminhoMaisCurtoPara(pDst);

			RouteResponse response = new RouteResponse();
			response.setMinDistance(pDst.minDistance);
			
			List<String> pathList = new ArrayList<String>();
			
			for (Point p : path) {
				pathList.add(p.toString());	
			}
			
			response.setPath(pathList);
			return response;
		}

		return null;
	}

	/**
	 * Prints the map info handler.
	 *
	 * @param map the map
	 * @param context the context
	 * @return the string
	 */
	public static String printMapInfoHandler(MapVO map, Context context) {

		LambdaLogger logger = context.getLogger();

		logger.log("Map: " + map.toString() + "\n");
		logger.log("Function name: " + context.getFunctionName() + "\n");
		logger.log("Max mem allocated: " + context.getMemoryLimitInMB() + "\n");
		logger.log("Time remaining in milliseconds: " + context.getRemainingTimeInMillis() + "\n");
		logger.log("CloudWatch log stream name: " + context.getLogStreamName() + "\n");
		logger.log("CloudWatch log group name: " + context.getLogGroupName() + "\n");

		return map.toString();
	}
}
