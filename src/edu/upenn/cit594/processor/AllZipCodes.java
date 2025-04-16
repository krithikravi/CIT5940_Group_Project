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
		while (!(type.compareToIgnoreCase("partial")==0 || type.compareToIgnoreCase("full")==0)) {
			type = Input.getValidStringInput("Enter the type of vaccination to look up: full or partial.");
		}
		String dateString = Input.getValidStringInput("Enter the date to look up: must be in the format YYYY-MM-DD.");
		Matcher matcher = pattern.matcher(dateString);
		while (!matcher.find()) {
			dateString = Input.getValidStringInput("Enter the date to look up: must be in the format YYYY-MM-DD.");
			matcher = pattern.matcher(dateString);
		}
		System.out.println("BEGIN OUTPUT");
		String date = (String) matcher.group();
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
			}
			else {
				if (zip.getFullVaccinations().getOrDefault(date, 0)==0) {
					continue;
				}
				String format = String.format("%d %.4f", zip.getZipcode(),(double)zip.getFullVaccinations().getOrDefault(date, 0)/(double)zip.getPopulation());
				System.out.println(format);
			}
		}
		System.out.println("END OUTPUT");
	}
	
	
}
