package com.mutaz.aggregatormicroservice.provider.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mutaz.aggregatormicroservice.Util.Utils;
import com.mutaz.aggregatormicroservice.provider.api.HotelsProvider;
import com.mutaz.aggregatormicroservice.provider.api.SearchResult;
import com.mutaz.aggregatormicroservice.provider.api.SearchCriteria;

/**
 * A Provider for the BestHotels service.
 * 
 * @author Mutaz.Alghafary
 *
 */
@Component
public class BestHotelsProvider implements HotelsProvider {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public String getName() {
		return "BestHotels";
	}

	@Override
	public Set<SearchResult> search(String url, SearchCriteria searchCriteria) throws IOException {
		ArrayNode hotels = executeRequest(url, searchCriteria);
		Set<SearchResult> result = new HashSet<>();
		for (JsonNode hotel : hotels) {
			result.add(convertToApiResult(hotel, searchCriteria));
		}
		return result;
	}

	private ArrayNode executeRequest(String server, SearchCriteria searchCriteria) throws IOException {
		String request = "http://" + server + buildRequestForSearch(searchCriteria);
		String response = restTemplate.getForObject(request, String.class);
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode hotels = (ArrayNode) mapper.readTree(response);
		return hotels;
	}

	private String buildRequestForSearch(SearchCriteria searchCriteria) {
		StringBuilder requestParameters = new StringBuilder("/hotels/search?");
		requestParameters.append("city=" + searchCriteria.getCity() + "&");
		requestParameters.append("fromDate=" + Utils.formatDateAsIsoLocalDate(searchCriteria.getFromDate()) + "&");
		requestParameters.append("toDate=" + Utils.formatDateAsIsoLocalDate(searchCriteria.getToDate()) + "&");
		requestParameters.append("numberOfAdults=" + searchCriteria.getNumberOfAdults());
		return requestParameters.toString();
	}

	private SearchResult convertToApiResult(JsonNode hotel, SearchCriteria searchCriteria) {
		String hotelName = hotel.get("hotelName").asText();
		int hotelRate = hotel.get("hotelRate").asInt();
		double hotelFare = hotel.get("hotelFare").asDouble();
		String roomAmenities = hotel.get("roomAmenities").asText();

		int numberOfDays = searchCriteria.getNumberOfDays();
		double farePerNight = hotelFare / numberOfDays;

		SearchResult result = new SearchResult(getName(), hotelName, hotelRate, farePerNight,
				Utils.convertToArray(roomAmenities, ","));
		return result;
	}
}
