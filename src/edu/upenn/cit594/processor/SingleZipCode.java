package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.util.*;

public class SingleZipCode extends ZipOperations{

	public SingleZipCode(HashMap<Integer, Area> zipCodes) {
		super(zipCodes);
	}
	
	public void averageMarketValue(Integer choice) {
		Pattern pattern = Pattern.compile("^\\d{5}", Pattern.CASE_INSENSITIVE);
		String zipString = Input.getInput("Enter the zip code to look up: must be in the format XXXXX.");
		Matcher matcher = pattern.matcher(zipString);
		while (!matcher.find()) {
			zipString = Input.getInput("Enter the zip code to look up: must be in the format XXXXX.");
			matcher = pattern.matcher(zipString);
		}
		Integer zip = Integer.valueOf((String) matcher.group());
		Area location = zipCodes.get(zip);
		if (choice==4) {
			System.out.println(location.getTotalMarketValue()/location.getProperties().size());
		}
		else if (choice==6) {
			System.out.println(location.getTotalMarketValue()/location.getPopulation());
		}
		else if (choice==7) {
			System.out.println(location.getTotalLivableArea()/location.getPopulation());
		}
		else {
			System.out.println(location.getTotalLivableArea()/location.getProperties().size());
		}
	}
	
}
