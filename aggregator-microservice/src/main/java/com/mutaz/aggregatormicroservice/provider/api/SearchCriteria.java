package com.mutaz.aggregatormicroservice.provider.api;

import java.util.Date;

import com.mutaz.aggregatormicroservice.Util.Utils;

public class SearchCriteria {
	
	private Date fromDate;
	private Date toDate;
	private String city;
	private int numberOfAdults;
	
	public SearchCriteria(Date fromDate, Date toDate, String city, int numberOfAdults) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.city = city;
		this.numberOfAdults = numberOfAdults;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public String getCity() {
		return city;
	}
	public int getNumberOfAdults() {
		return numberOfAdults;
	}
	
	public int getNumberOfDays() {
		return Utils.findNumberOfDays(toDate, fromDate);
	}
}
