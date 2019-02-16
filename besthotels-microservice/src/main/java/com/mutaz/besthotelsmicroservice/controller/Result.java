package com.mutaz.besthotelsmicroservice.controller;

import com.mutaz.besthotelsmicroservice.domain.Hotel;
import com.mutaz.besthotelsmicroservice.util.Utils;

public class Result {

	private String hotelName;
	private int hotelRate;
	private String hotelFare;
	private String roomAmenities;

	/**
	 * Factory method for creating {@link Result} for a hotel
	 * 
	 * @param hotel
	 * @param totalPrice
	 * @return
	 */
	public static Result create(Hotel hotel, double totalPrice) {
		String totalPriceFormatted = Utils.roundToTwoDecimalPlaces(totalPrice);
		return new Result(hotel.getName(), hotel.getRate().getValue(), totalPriceFormatted, hotel.getRoomAmenities());
	}

	private Result(String hotelName, int hotelRate, String hotelFare, String roomAmenities) {
		super();
		this.hotelName = hotelName;
		this.hotelRate = hotelRate;
		this.hotelFare = hotelFare;
		this.roomAmenities = roomAmenities;
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getHotelRate() {
		return hotelRate;
	}

	public String getHotelFare() {
		return hotelFare;
	}

	public String getRoomAmenities() {
		return roomAmenities;
	}

}
