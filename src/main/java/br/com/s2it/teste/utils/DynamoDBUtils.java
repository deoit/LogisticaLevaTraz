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
package br.com.s2it.teste.utils;

import br.com.s2it.teste.validations.ValidationUtils;
import br.com.s2it.teste.vos.MapVO;
import br.com.s2it.teste.vos.RouteVO;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

/**
 * The Class DynamoDBUtils.
 */
public class DynamoDBUtils {

	/** The Constant TABLE. */
	private static final String TABLE = "Map-S2IT-Test";
	
	/** The Constant REGION. */
	private static final Region REGION = Region.getRegion(Regions.US_EAST_1);

	/** The Constant keyName. */
	private static final String keyName = "Name";
	
	/** The Constant keyRoutes. */
	private static final String keyRoutes = "Routes";

	/** The Constant keySrc. */
	private static final String keySrc = "src";
	
	/** The Constant keyDst. */
	private static final String keyDst = "dst";
	
	/** The Constant keyDistance. */
	private static final String keyDistance = "distance";

	/**
	 * Save map.
	 *
	 * @param map the map
	 */
	public static void saveMap(MapVO map) {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(REGION);
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		mapper.save(map);
	}

	/**
	 * Load map.
	 *
	 * @param mapName the map name
	 * @return the map vo
	 */
	public static MapVO loadMap(String mapName) {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(REGION);
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		MapVO mapRetrieved = mapper.load(MapVO.class, mapName);
		
		return ValidationUtils.getMapInstance(mapRetrieved);
	}

	/**
	 * Put route.
	 *
	 * @param route the route
	 * @return the put item outcome
	 */
	public static PutItemOutcome putRoute(RouteVO route) {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(REGION);
		DynamoDB dynamoDB = new DynamoDB(client);
		Table table = dynamoDB.getTable(TABLE);

		Item item = new Item().withPrimaryKey(keyDistance, route.getDistance()).withString(keySrc, route.getSrc()).withString(keyDst, route.getDst());
		PutItemOutcome outcome = table.putItem(item);

		return outcome;
	}

	/**
	 * Gets the all maps.
	 *
	 * @return the all maps
	 */
	public static ScanResult getAllMaps() {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(REGION);

		ScanRequest scanRequest = new ScanRequest().withTableName(TABLE).withProjectionExpression(keyName);

		return client.scan(scanRequest);
	}

	/**
	 * Gets the map.
	 *
	 * @param mapName the map name
	 * @return the map
	 */
	public static Item getMap(String mapName) {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(REGION);
		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable(TABLE);
		Item item = table.getItem(new GetItemSpec().withPrimaryKey(keyName, mapName).withAttributesToGet(keyRoutes));

		return item;
	}

	/**
	 * Delete map.
	 *
	 * @param mapName the map name
	 * @return the delete item outcome
	 */
	public static DeleteItemOutcome deleteMap(String mapName) {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(REGION);
		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable(TABLE);
		DeleteItemOutcome outcome = table.deleteItem(keyName, mapName);
		return outcome;
	}
}