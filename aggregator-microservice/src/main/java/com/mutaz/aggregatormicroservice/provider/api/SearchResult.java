package com.mutaz.aggregatormicroservice.provider.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a single search result for the hotels service.
 * 
 * @author Mutaz.Alghafary
 *
 */
public class SearchResult {

	/**
	 * The name of the provider who provided the result.
	 */
	private String providerName;

	private String hotelName;

	/**
	 * Rate of the hotel, used for internal ordering
	 */
	@JsonIgnore
	private int hotelRate;

	/**
	 * Fare per night
	 */
	private double fare;

	private String[] amenities = {};

	public SearchResult(String providerName, String hotelName, int hotelRate, double fare, String[] amenities) {
		super();
		this.providerName = providerName;
		this.hotelName = hotelName;
		this.hotelRate = hotelRate;
		this.fare = fare;
		this.amenities = amenities;
	}

	public String getProviderName() {
		return providerName;
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getHotelRate() {
		return hotelRate;
	}

	public double getFare() {
		return fare;
	}

	public String[] getAmenities() {
		return amenities;
	}
}
