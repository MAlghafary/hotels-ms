package com.mutaz.besthotelsmicroservice.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mutaz.besthotelsmicroservice.domain.Hotel;
import com.mutaz.besthotelsmicroservice.repository.api.HotelsRepository;
import com.mutaz.besthotelsmicroservice.repository.api.SearchCriteria;
import com.mutaz.besthotelsmicroservice.util.Utils;

@RestController
@RequestMapping("hotels")
public class HotelsController {

	@Autowired
	private HotelsRepository hotelsRepository;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Set<Result> search(@RequestParam(value = "city") String city,
			@RequestParam(value = "fromDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
			@RequestParam(value = "toDate")   @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,
			@RequestParam(value = "numberOfAdults") int numberOfAdults) {

		SearchCriteria searchCriteria = new SearchCriteria(city);
		Set<Hotel> matchedHotels = hotelsRepository.find(searchCriteria);
		Set<Result> result = new HashSet<>();
		int numberOfDays = Utils.findNumberOfDays(toDate, fromDate);
		matchedHotels.forEach(e ->{
			double totalPrice = Utils.calculateTotalPrice(e, numberOfDays, numberOfAdults);
			result.add(Result.create(e, totalPrice));
		});
		return result;
	}
}
