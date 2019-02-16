package com.mutaz.besthotelsmicroservice.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.mutaz.besthotelsmicroservice.domain.Hotel;

public class Utils {

	public static int findNumberOfDays(Date firstDate, Date secondDate) {
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return Math.toIntExact(diff);
	}

	public static double calculateTotalPrice(Hotel hotel, int numberOfDays, int numberOfAdults) {
		int numberOfNeededRooms = (int) Math.ceil((double )numberOfAdults / hotel.getRoomCapacity());
		double totalPrice = numberOfDays * numberOfNeededRooms * hotel.getPricePerNight();
		return totalPrice;
	}

	public static String roundToTwoDecimalPlaces(double number) {
		return String.format("%.2f", number);
	}

}
