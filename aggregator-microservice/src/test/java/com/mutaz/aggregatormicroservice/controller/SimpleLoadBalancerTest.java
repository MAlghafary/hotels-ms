package com.mutaz.aggregatormicroservice.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SimpleLoadBalancerTest {
	
	@Test
	public void testSelectServerWithOneServiceAndOneServer() {
	
		MockEnvironment mockEnvironment = new MockEnvironment();
		mockEnvironment.setProperty("loadbalancer.servicesIds", "a");
		mockEnvironment.setProperty("a.serversList", "s1:p1");
		SimpleLoadBalancer simpleLoadBalancer = createLoadBalancer(mockEnvironment);
		String expected = "s1:p1";
		String actual = simpleLoadBalancer.selectServer("a");
		assertEquals(expected, actual);
	}

	@Test(expected=IllegalStateException.class)
	public void testSelectServerWithOneServiceAndNoServers() {
	
		MockEnvironment mockEnvironment = new MockEnvironment();
		mockEnvironment.setProperty("loadbalancer.servicesIds", "a");
		SimpleLoadBalancer simpleLoadBalancer = createLoadBalancer(mockEnvironment);
		String expected = "";
		String actual = simpleLoadBalancer.selectServer("a");
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testSelectServerWithOneServiceAndThreeServers() {
		MockEnvironment mockEnvironment = new MockEnvironment();
		mockEnvironment.setProperty("loadbalancer.servicesIds", "a");
		mockEnvironment.setProperty("a.serversList", "s1:p1,s2:p2,s3:p3");
		SimpleLoadBalancer simpleLoadBalancer = createLoadBalancer(mockEnvironment);
		
		assertEquals("s1:p1", simpleLoadBalancer.selectServer("a"));
		assertEquals("s2:p2", simpleLoadBalancer.selectServer("a"));
		assertEquals("s3:p3", simpleLoadBalancer.selectServer("a"));
		assertEquals("s1:p1", simpleLoadBalancer.selectServer("a"));
		assertEquals("s2:p2", simpleLoadBalancer.selectServer("a"));
	}
	
	private SimpleLoadBalancer createLoadBalancer(MockEnvironment mockEnvironment) {
		SimpleLoadBalancer simpleLoadBalancer = new SimpleLoadBalancer(mockEnvironment);
		simpleLoadBalancer.init();
		return simpleLoadBalancer;
	}

}
