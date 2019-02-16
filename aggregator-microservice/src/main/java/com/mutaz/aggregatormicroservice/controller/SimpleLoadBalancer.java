package com.mutaz.aggregatormicroservice.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * A Simple load balancer that uses a Round-Robin technique to distribute the load 
 * between a number of servers. 
 *  
 * Depends on the following properties to be available 
 * <ul> 
 * <li> loadbalancer.servicesIds  : List of services (must match providers names) separated by (,) </li>
 * <li> serviceId.serversList     : a list of servers for each service Id,separated by (,) </li>
 * </ul>
 * <p>
 * Example :
 *  <code>
 *  loadbalancer.servicesIds=BestHotels,CrazyHotels
 *  BestHotels.serversList=localhost:8081,localhost:8082
 *  CrazyHotels.serversList=localhost:8083,localhost:8084
 *  </code>
 * </p>
 * @author Mutaz.Alghafary
 *
 */
@Component
public class SimpleLoadBalancer {

	
	private Environment environment;

	private Map<String, List<String>> serversByServiceId = new HashMap<>();
	private Map<String, String> lastAccssedServersByServiceId = new HashMap<>();

	
	public SimpleLoadBalancer(Environment environment) {
		this.environment = environment;
	}
	
	
	@PostConstruct
	public void init() {
		String servicesIdsProperty = environment.getProperty("loadbalancer.servicesIds");
		String[] servicesIds = servicesIdsProperty.split(",");
		for (String serviceId : servicesIds) {
			String listOfServers = environment.getProperty(serviceId + ".serversList");
			if(listOfServers == null) {
				throw new IllegalStateException("No Servers are defined for service :" + serviceId );
			}
			List<String> servers = Arrays.asList(listOfServers.split(","));
			this.serversByServiceId.put(serviceId, servers);
		}
	}

	public String selectServer(String serviceId) {
		List<String> availableServers = serversByServiceId.get(serviceId);
		String lastAccssedServer = lastAccssedServersByServiceId.get(serviceId);
		String selectedServer = "";
		if (lastAccssedServer == null) {
			// First Selection
			selectedServer = availableServers.get(0);
		} else {
			// Last server was selected as last one, go to the beginning
			if (availableServers.indexOf(lastAccssedServer) == availableServers.size() - 1) {
				selectedServer = availableServers.get(0);
			} else {
				selectedServer = availableServers.get(availableServers.indexOf(lastAccssedServer) + 1);
			}
		}

		lastAccssedServersByServiceId.put(serviceId, selectedServer);
		return selectedServer;
	}
}
