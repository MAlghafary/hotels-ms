package com.mutaz.besthotelsmicroservice.domain;

/**
 * Represents a hotel with one type of rooms, the room has both capacity (how
 * many adults can stay in the room) and amenities.
 * 
 * @author Mutaz.Alghafary
 *
 */
public class Hotel {

	public enum Rate {
		ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

		private final int value;

		private Rate(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private String name;
	private String city;
	private Rate rate;
	private double pricePerNight;
	private int roomCapacity;
	private String roomAmenities;

	public Hotel(String name, String city, Rate rate, double pricePerNight, int roomCapacity, String roomAmenities) {
		super();
		this.name = name;
		this.city = city;
		this.rate = rate;
		this.pricePerNight = pricePerNight;
		this.roomCapacity = roomCapacity;
		this.roomAmenities = roomAmenities;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public Rate getRate() {
		return rate;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public int getRoomCapacity() {
		return roomCapacity;
	}

	public String getRoomAmenities() {
		return roomAmenities;
	}

}
