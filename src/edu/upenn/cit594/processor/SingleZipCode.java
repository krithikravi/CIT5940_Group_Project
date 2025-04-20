package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.Input;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.*;

public class SingleZipCode extends ZipOperations{
	protected HashMap<Integer,SingleStrategy> operationStrategies;
	

	public SingleZipCode(HashMap<Integer, Area> zipCodes, Logger logger,Input inputHelper) {
		super(zipCodes, logger,inputHelper);
		operationStrategies=new HashMap<Integer,SingleStrategy>();
		operationStrategies.put(4,new AverageMarketValueStrategy());
		operationStrategies.put(5,new AverageTotalLivableAreaStrategy());
		operationStrategies.put(6,new TotalMarketValueStrategy());
		operationStrategies.put(7,new MarketValueVaccinationStrategy());
		
	}
	
	public void runOperation(Integer choice) {
		Pattern pattern = Pattern.compile("^\\d{5}", Pattern.CASE_INSENSITIVE);
		String zipString = inputHelper.getValidStringInput("Enter the zip code to look up: must be in the format XXXXX.");
		logger.log(zipString);
		Matcher matcher = pattern.matcher(zipString);
		while (!matcher.find()) {
			zipString = inputHelper.getValidStringInput("Enter the zip code to look up: must be in the format XXXXX.");
			logger.log(zipString);
			matcher = pattern.matcher(zipString);
		}
		System.out.println();
		System.out.println("BEGIN OUTPUT");
		Integer zip = Integer.valueOf((String) matcher.group());
		if (!zipCodes.containsKey(zip)) {
			System.out.println(0);
			System.out.println("END OUTPUT");
			System.out.flush();
			return;
		}
		if (memo.containsKey(String.valueOf(zip)+String.valueOf(choice))) {
			for (String out: memo.get(String.valueOf(zip)+String.valueOf(choice))) {
				System.out.println(out);
			}
			System.out.println("END OUTPUT");
			System.out.flush();
			return;
		}
		Area location = zipCodes.get(zip);
		String ret  = operationStrategies.get(choice).calculateOperation(location);
		System.out.println(ret);
		memo.put(String.valueOf(zip)+String.valueOf(choice), new ArrayList<>(Arrays.asList(ret)));
		System.out.println("END OUTPUT");
		System.out.flush();
	}
	
}
