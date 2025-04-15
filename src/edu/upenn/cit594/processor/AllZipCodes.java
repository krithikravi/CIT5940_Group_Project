package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.Input;
import edu.upenn.cit594.util.*;

public class AllZipCodes extends ZipOperations {
	
	
	public AllZipCodes(HashMap<Integer, Area> zipCodes) {
		super(zipCodes);
	}

	public void totalPopulation() {
		Integer total=0;
		for (Area zip:zipCodes.values()) {
			total+=zip.getPopulation();
		}
		System.out.println(total);
	}
	
	public void totalVaccinations() {
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}", Pattern.CASE_INSENSITIVE);
		String type = Input.getValidStringInput("Enter the type of vaccination to look up: full or partial.");
		while (type!="partial" && type!="full") {
			type = Input.getValidStringInput("Enter the type of vaccination to look up: full or partial.");
		}
		String dateString = Input.getValidStringInput("Enter the date to look up: must be in the format YYYY-MM-DD.");
		Matcher matcher = pattern.matcher(dateString);
		while (!matcher.find()) {
			dateString = Input.getValidStringInput("Enter the date to look up: must be in the format YYYY-MM-DD.");
			matcher = pattern.matcher(dateString);
		}
		String date = (String) matcher.group();
		Integer total=0;
		for (Area zip:zipCodes.values()) {
			if (type=="partial") {
				System.out.println(zip.getZipcode()+" "+zip.getPartialVaccinations().getOrDefault(date, 0)/zip.getPopulation());
			}
			else {
				System.out.println(zip.getZipcode()+" "+zip.getFullVaccinations().getOrDefault(date, 0)/zip.getPopulation());
			}
		}
	}
	
	
}
