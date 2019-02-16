package com.mutaz.besthotelsmicroservice.repository.api;

import java.util.Set;

import com.mutaz.besthotelsmicroservice.domain.Hotel;

public interface HotelsRepository {

	/**
	 * Finds a collection of hotels matching the {@link SearchCriteria}
	 * 
	 * @param searchCriteria
	 * @return A collection of hotels
	 */
	public Set<Hotel> find(SearchCriteria searchCriteria);

}
