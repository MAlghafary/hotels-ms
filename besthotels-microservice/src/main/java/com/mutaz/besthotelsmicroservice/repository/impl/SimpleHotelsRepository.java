package com.mutaz.besthotelsmicroservice.repository.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mutaz.besthotelsmicroservice.domain.Hotel;
import com.mutaz.besthotelsmicroservice.domain.Hotel.Rate;
import com.mutaz.besthotelsmicroservice.repository.api.HotelsRepository;
import com.mutaz.besthotelsmicroservice.repository.api.SearchCriteria;

@Component
public class SimpleHotelsRepository implements HotelsRepository {

	private Set<Hotel> hotels = new HashSet<>();
	{
		hotels.add(new Hotel("Hotel1", "BOM", Rate.ONE, 100, 2, "Amenities1"));
		hotels.add(new Hotel("Hotel2", "AMD", Rate.TWO, 50, 2, "Amenities1,Amenities2"));
		hotels.add(new Hotel("Hotel3", "DUB", Rate.THREE, 60, 1, "Amenities1,Amenities2"));
		hotels.add(new Hotel("Hotel4", "ALA", Rate.FOUR, 70, 3, "Amenities1,Amenities3"));
		hotels.add(new Hotel("Hotel5", "DUB", Rate.FOUR, 70, 3, "Amenities3,Amenities4"));

	}

	public SimpleHotelsRepository() {

	}

	@Override
	public Set<Hotel> find(SearchCriteria searchCriteria) {
		return hotels.stream()
				.filter(e -> e.getCity().equals(searchCriteria.getCity()))
				.collect(Collectors.toSet());
	}

}
