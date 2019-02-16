# hotels-ms

Notes : 
- Project is divided into 3 micro-services: 1- bestHotels-microservice 2- crazyhotels-microservice 3- aggregator-microservice
- Unit testing classes : com.mutaz.besthotelsmicroservice.util.UtilsTest 
                         com.mutaz.aggregatormicroservice.controller.SimpleLoadBalancerTest 
- The crazyhotels-microservice was not implemented but its straightforward to add (Very similar to bestHotels-microservice)  
- Scalability is considered by implementing a simple load balancer to distribute the load between a list of servers in a round robin fashion
- Resilience is considered by providing a fallback method for each hotels provider, see : 
     com.mutaz.aggregatormicroservice.provider.api.HotelsProvider.fallback() 
- Adding a new provider is straightforward : Implement the com.mutaz.aggregatormicroservice.provider.api.HotelsProvider + add the configuration to the application.properties file 

Limitations: 
 - IPs for microservices are hardcoded, can be enhanced by adding a naming service  
- Unit testing is partially done 
 - Some duplicate code between different project, can be enhanced by adding a shared project (for shared code)
