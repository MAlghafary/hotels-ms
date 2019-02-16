package com.mutaz.besthotelsmicroservice.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mutaz.besthotelsmicroservice.domain.Hotel;
import com.mutaz.besthotelsmicroservice.domain.Hotel.Rate;

public class UtilsTest {
	
	
	@Test
	public void testCalculateTotalPrice() {
		Hotel mockHotel = new Hotel("", "", Rate.ONE, 100, 1, "");
		double actual = Utils.calculateTotalPrice(mockHotel, 1, 1);
		double expected = 100;
		assertEquals(expected, actual, 0.0001);
	}
	
	@Test 
	public void testCalculateTotalPriceWhenNumberOfAdultsIsLessThanRoomCapacity() {
		Hotel mockHotel = new Hotel("", "", Rate.ONE, 100, 3, "");
		double actual = Utils.calculateTotalPrice(mockHotel, 5, 2);
		double expected = 500;
		assertEquals(expected, actual, 0.0001);
	}
}
