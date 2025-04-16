package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.Input;
import edu.upenn.cit594.util.*;

public class SingleZipCode extends ZipOperations{

	public SingleZipCode(HashMap<Integer, Area> zipCodes) {
		super(zipCodes);
	}
	
	public void averageMarketValue(Integer choice) {
		Pattern pattern = Pattern.compile("^\\d{5}", Pattern.CASE_INSENSITIVE);
		String zipString = Input.getValidStringInput("Enter the zip code to look up: must be in the format XXXXX.");
		Matcher matcher = pattern.matcher(zipString);
		while (!matcher.find()) {
			zipString = Input.getValidStringInput("Enter the zip code to look up: must be in the format XXXXX.");
			matcher = pattern.matcher(zipString);
		}
		System.out.println("BEGIN OUTPUT");
		Integer zip = Integer.valueOf((String) matcher.group());
		if (!zipCodes.containsKey(zip)) {
			System.out.println(0);
			System.out.println("END OUTPUT");
			return;
		}
		Area location = zipCodes.get(zip);
		if (choice==4) {
			if (location.getProperties().isEmpty()) {
				System.out.println(0);
				System.out.println("END OUTPUT");
				return;
			}
			System.out.println(location.getTotalMarketValue()/location.getProperties().size());
		}
		else if (choice==6) {
			if (location.getPopulation()==0) {
				System.out.println(0);
				System.out.println("END OUTPUT");
				return;
			}
			System.out.println(location.getTotalMarketValue()/location.getPopulation());
		}
		else if (choice==7) {
			if (location.getPopulation()==0 || location.getMaxFullVaccinations()==0) {
				System.out.println(0);
				System.out.println("END OUTPUT");
				return;
			}
			System.out.println(location.getTotalMarketValue()/location.getPopulation()/location.getMaxFullVaccinations());
		}
		else {
			if (location.getProperties().isEmpty()) {
				System.out.println(0);
				System.out.println("END OUTPUT");
				return;
			}
			System.out.println(location.getTotalLivableArea()/location.getProperties().size());
		}
		System.out.println("END OUTPUT");
	}
	
}
