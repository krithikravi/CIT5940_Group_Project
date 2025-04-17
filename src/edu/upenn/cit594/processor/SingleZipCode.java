package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.Input;
import edu.upenn.cit594.util.*;

public class SingleZipCode extends ZipOperations{
	protected HashMap<Integer,SingleStrategy> operationStrategies;
	

	public SingleZipCode(HashMap<Integer, Area> zipCodes) {
		super(zipCodes);
		operationStrategies=new HashMap<Integer,SingleStrategy>();
		operationStrategies.put(4,new AverageMarketValueStrategy());
		operationStrategies.put(5,new AverageTotalLivableAreaStrategy());
		operationStrategies.put(6,new TotalMarketValueStrategy());
		operationStrategies.put(7,new MarketValueVaccinationStrategy());
		
	}
	
	public void runOperation(Integer choice) {
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
		if (memo.containsKey(String.valueOf(zip)+String.valueOf(choice))) {
			for (String out: memo.get(String.valueOf(zip)+String.valueOf(choice))) {
				System.out.println(out);
			}
			System.out.println("END OUTPUT");
			return;
		}
//		String ret ="0";
		Area location = zipCodes.get(zip);
		String ret  = operationStrategies.get(choice).calculateOperation(location);
		
//		if (choice==4) {
//			if (!location.getProperties().isEmpty()) {
//				ret =String.valueOf(location.getTotalMarketValue()/location.getProperties().size()) ;
//			}
//						
//		}
//		else if (choice==6) {
//			if (location.getPopulation()!=0) {
//				ret=String.valueOf(location.getTotalMarketValue()/location.getPopulation());
//			}
//			
//		}
//		else if (choice==7) {
//			if (location.getPopulation()!=0 && location.getMaxFullVaccinations()!=0) {
//				ret=String.valueOf((float)location.getTotalMarketValue()/(float)location.getPopulation()/(float)location.getMaxFullVaccinations());
//			}
//			
//		}
//		else {
//			if (!location.getProperties().isEmpty()) {
//				ret=String.valueOf(location.getTotalLivableArea()/location.getProperties().size());
//			}
//			
//		}
		System.out.println(ret);
		memo.put(String.valueOf(zip)+String.valueOf(choice), new ArrayList<>(Arrays.asList(ret)));
//		memo.put(String.valueOf(zip)+String.valueOf(choice), String.valueOf(ret));
		System.out.println("END OUTPUT");
	}
	
}
