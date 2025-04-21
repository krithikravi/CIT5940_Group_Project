package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.Input;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.*;


public class AllZipCodes extends ZipOperations {
	
	
	
	public AllZipCodes(HashMap<Integer, Area> zipCodes, Logger logger, Input inputHelper) {
		super(zipCodes, logger,inputHelper);
	}

	public void totalPopulation() {
		System.out.println();
		System.out.println("BEGIN OUTPUT");
		if (memo.containsKey("total")) {
			System.out.println(memo.get("total").get(0));
			System.out.println("END OUTPUT");
			System.out.flush();
			return;
			
		}
		
		Integer total=0;
		for (Area zip:zipCodes.values()) {
			total+=zip.getPopulation();
		}
		System.out.println(total);
		memo.put("total", new ArrayList<>(Arrays.asList(String.valueOf(total))));
		System.out.println("END OUTPUT");
		System.out.flush();
	}
	
	public void totalVaccinations() {
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}", Pattern.CASE_INSENSITIVE);
		String type = inputHelper.getValidStringInput("Enter the type of vaccination to look up: full or partial.");
		logger.log(type);
		while (!(type.compareToIgnoreCase("partial")==0 || type.compareToIgnoreCase("full")==0)) {
			type = inputHelper.getValidStringInput("Enter the type of vaccination to look up: full or partial.");
			logger.log(type);
		}
		String dateString = inputHelper.getValidStringInput("Enter the date to look up: must be in the format YYYY-MM-DD.");
		logger.log(dateString);
		Matcher matcher = pattern.matcher(dateString);
		while (!matcher.find()) {
			dateString = inputHelper.getValidStringInput("Enter the date to look up: must be in the format YYYY-MM-DD.");
			logger.log(dateString);
			matcher = pattern.matcher(dateString);
		}
		System.out.println();
		System.out.println("BEGIN OUTPUT");
		String date = (String) matcher.group();
		type=type.toLowerCase();
		if (memo.containsKey(date+type)) {
			for (String s: memo.get(date+type)) {
				System.out.println(s);
			}
			System.out.println("END OUTPUT");
			System.out.flush();
			return;
		}
		for (Area zip:zipCodes.values()) {
			if (zip.getPopulation()==0) {
				continue;
			}
			if (type.compareToIgnoreCase("partial")==0) {
				if (zip.getPartialVaccinations().getOrDefault(date, 0)==0) {
					continue;
				}
				String format = String.format("%d %.4f", zip.getZipcode(),(double)zip.getPartialVaccinations().getOrDefault(date, 0)/(double)zip.getPopulation());
				System.out.println(format);
				ArrayList<String> currMemo = memo.getOrDefault(date+type,new ArrayList<String>());
				currMemo.add(format);
				memo.put(date+type, currMemo);
			}
			else {
				if (zip.getFullVaccinations().getOrDefault(date, 0)==0) {
					continue;
				}
				String format = String.format("%d %.4f", zip.getZipcode(),(double)zip.getFullVaccinations().getOrDefault(date, 0)/(double)zip.getPopulation());
				System.out.println(format);
				ArrayList<String> currMemo = memo.getOrDefault(date+type,new ArrayList<String>());
				currMemo.add(format);
				memo.put(date+type, currMemo);
			}
		}
		if (!memo.containsKey(date+type)) {
			System.out.println("0");
		}
		System.out.println("END OUTPUT");
		System.out.flush();
	}
	
	
}
