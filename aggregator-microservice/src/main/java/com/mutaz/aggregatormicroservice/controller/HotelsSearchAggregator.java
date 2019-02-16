package com.mutaz.aggregatormicroservice.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.mutaz.aggregatormicroservice.provider.api.HotelsProvider;
import com.mutaz.aggregatormicroservice.provider.api.SearchCriteria;
import com.mutaz.aggregatormicroservice.provider.api.SearchResult;

@RestController
@RequestMapping("hotels")
public class HotelsSearchAggregator {

	@Autowired
	List<HotelsProvider> providers;


	@Autowired 
	SimpleLoadBalancer loadBalancer;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Set<SearchResult> search(
			@RequestParam(value = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@RequestParam(value = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
			@RequestParam(value = "city") String city, @RequestParam(value = "numberOfAdults") int numberOfAdults) {

		SearchCriteria searchCriteria = new SearchCriteria(fromDate, toDate, city, numberOfAdults);
		Set<SearchResult> aggregateResults = new HashSet<>();
		for (HotelsProvider provider : providers) {
			try {
				String server = loadBalancer.selectServer(provider.getName());
				Set<SearchResult> providerResults = provider.search(server, searchCriteria);
				aggregateResults.addAll(providerResults);
			} catch (RestClientException | IOException e) {
				aggregateResults.addAll(provider.fallback());
			}
		}

		return aggregateResults.stream().sorted((a, b) -> a.getHotelRate() - b.getHotelRate())
				.collect(Collectors.toSet());
	}

}
