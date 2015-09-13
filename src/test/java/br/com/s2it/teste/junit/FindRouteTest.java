package br.com.s2it.teste.junit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.s2it.teste.functions.Main;
import br.com.s2it.teste.responde.RouteResponse;
import br.com.s2it.teste.vos.MapVO;
import br.com.s2it.teste.vos.RouteVO;

public class FindRouteTest {

	@Test
	public void testRoute() {

		MapVO map1 = mockMap1();
		RouteResponse r1 = Main.findRoute("A", "D", map1);
		System.out.println("Shortest route: "+r1.getPath()+" with distance "+r1.getMinDistance()+"\n");
		Assert.assertNotNull(r1);
		
		MapVO map2 = mockMap2();
		RouteResponse r2 = Main.findRoute("HA", "NY", map2);
		System.out.println("Shortest route: "+r2.getPath()+" with distance "+r2.getMinDistance()+"\n");
		Assert.assertNotNull(r2);
		
		MapVO map3 = mockMap3();
		RouteResponse r3 = Main.findRoute("HA", "NY", map3);
		System.out.println("Route not found. \n");
		Assert.assertNull(r3);
	}

	private MapVO mockMap1() {

		MapVO map = new MapVO();
		map.setName("MapaSP");

		List<RouteVO> routes = new ArrayList<RouteVO>();

		RouteVO route1 = new RouteVO();
		route1.setSrc("A");
		route1.setDst("B");
		route1.setDistance(10);
		routes.add(route1);

		RouteVO route2 = new RouteVO();
		route2.setSrc("B");
		route2.setDst("D");
		route2.setDistance(15);
		routes.add(route2);

		RouteVO route3 = new RouteVO();
		route3.setSrc("A");
		route3.setDst("C");
		route3.setDistance(20);
		routes.add(route3);

		RouteVO route4 = new RouteVO();
		route4.setSrc("C");
		route4.setDst("D");
		route4.setDistance(30);
		routes.add(route4);

		RouteVO route5 = new RouteVO();
		route5.setSrc("B");
		route5.setDst("E");
		route5.setDistance(50);
		routes.add(route5);

		RouteVO route6 = new RouteVO();
		route6.setSrc("D");
		route6.setDst("E");
		route6.setDistance(30);
		routes.add(route6);

		RouteVO route7 = new RouteVO();
		route7.setSrc("E");
		route7.setDst("D");
		route7.setDistance(31);
		routes.add(route7);

		map.setRoutes(routes);

		return map;
	}

	private MapVO mockMap2() {

		MapVO map = new MapVO();
		map.setName("MapaMG");

		List<RouteVO> routes = new ArrayList<RouteVO>();

		RouteVO route1 = new RouteVO();
		route1.setSrc("HA");
		route1.setDst("BA");
		route1.setDistance(79);
		routes.add(route1);

		RouteVO route2 = new RouteVO();
		route2.setSrc("HA");
		route2.setDst("AL");
		route2.setDistance(81);
		routes.add(route2);

		RouteVO route3 = new RouteVO();
		route3.setSrc("AL");
		route3.setDst("AH");
		route3.setDistance(82);
		routes.add(route3);

		RouteVO route4 = new RouteVO();
		route4.setSrc("AL");
		route4.setDst("NY");
		route4.setDistance(91);
		routes.add(route4);
		
		RouteVO route5 = new RouteVO();
		route5.setSrc("AL");
		route5.setDst("PH");
		route5.setDistance(62);
		routes.add(route5);

		RouteVO route6 = new RouteVO();
		route6.setSrc("NY");
		route6.setDst("PH");
		route6.setDistance(97);
		routes.add(route6);

		RouteVO route7 = new RouteVO();
		route7.setSrc("NY");
		route7.setDst("AL");
		route7.setDistance(87);
		routes.add(route7);
		
		RouteVO route8 = new RouteVO();
		route8.setSrc("PH");
		route8.setDst("NY");
		route8.setDistance(96);
		routes.add(route8);

		RouteVO route9 = new RouteVO();
		route9.setSrc("PH");
		route9.setDst("BA");
		route9.setDistance(102);
		routes.add(route9);

		RouteVO route10 = new RouteVO();
		route10.setSrc("PH");
		route10.setDst("AL");
		route10.setDistance(61);
		routes.add(route10);


		map.setRoutes(routes);

		return map;
	}
	
	private MapVO mockMap3() {

		MapVO map = new MapVO();
		map.setName("MapaRS");

		List<RouteVO> routes = new ArrayList<RouteVO>();

		RouteVO route1 = new RouteVO();
		route1.setSrc("H");
		route1.setDst("I");
		route1.setDistance(10);
		routes.add(route1);

		RouteVO route2 = new RouteVO();
		route2.setSrc("I");
		route2.setDst("K");
		route2.setDistance(15);
		routes.add(route2);

		RouteVO route3 = new RouteVO();
		route3.setSrc("H");
		route3.setDst("J");
		route3.setDistance(20);
		routes.add(route3);

		RouteVO route4 = new RouteVO();
		route4.setSrc("J");
		route4.setDst("K");
		route4.setDistance(30);
		routes.add(route4);

		RouteVO route5 = new RouteVO();
		route5.setSrc("I");
		route5.setDst("L");
		route5.setDistance(50);
		routes.add(route5);

		RouteVO route6 = new RouteVO();
		route6.setSrc("K");
		route6.setDst("L");
		route6.setDistance(30);
		routes.add(route6);

		RouteVO route7 = new RouteVO();
		route7.setSrc("L");
		route7.setDst("K");
		route7.setDistance(31);
		routes.add(route7);

		map.setRoutes(routes);

		return map;
	}

}
