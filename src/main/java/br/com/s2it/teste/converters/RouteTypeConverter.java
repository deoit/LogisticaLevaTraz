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
package br.com.s2it.teste.converters;

import java.util.List;

import br.com.s2it.teste.vos.RouteVO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;

import static com.amazonaws.util.Throwables.failure;

/**
 * The Class RouteTypeConverter.
 */
public class RouteTypeConverter implements DynamoDBMarshaller<List<RouteVO>> {

	/** The Constant mapper. */
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/** The Constant writer. */
	private static final ObjectWriter writer = mapper.writer();

	public String marshall(List<RouteVO> obj) {

		try {
			return writer.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw failure(e, "Unable to marshall instance " + obj.getClass() + " to string");
		}
	}
	
	public List<RouteVO> unmarshall(Class<List<RouteVO>> clazz, String json) {

		final CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, RouteVO.class);
		try {
			return mapper.readValue(json, type);
		} catch (Exception e) {
			throw failure(e, "Unable to unmarshall string " + json + " to " + clazz);
		}
	}
}
