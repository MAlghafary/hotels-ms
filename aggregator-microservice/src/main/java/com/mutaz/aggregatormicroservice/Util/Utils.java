package com.mutaz.aggregatormicroservice.Util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {
	
	
	public static String formatDateAsIsoLocalDate(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return DateTimeFormatter.ISO_LOCAL_DATE.format(localDate);
	}
	
	public static int findNumberOfDays(Date firstDate, Date secondDate) {
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return Math.toIntExact(diff);
	}
	
	public static String[] convertToArray(String text,String delimiter) {
		return text.split(delimiter);
	}

}
