package com.mutaz.besthotelsmicroservice.repository.api;


/**
 * A Criteria to be used when searching for hotels.
 */

public class SearchCriteria {

	private String city;

	public SearchCriteria(String city) {
		super();
		this.city = city;
	}

	public String getCity() {
		return city;
	}

}
