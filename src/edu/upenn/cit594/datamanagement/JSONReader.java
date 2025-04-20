package edu.upenn.cit594.datamanagement;

import java.util.List;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*; 
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.upenn.cit594.util.*;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.util.regex.Matcher;

import edu.upenn.cit594.util.*;

public class JSONReader extends Reader {

	public JSONReader(String filename) {
		super(filename);
	}
	public JSONReader(String filename, HashMap curr) {
		super(filename,curr);		
	}

	@Override
	public HashMap read() throws IOException {
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}", Pattern.CASE_INSENSITIVE);
		
		try (FileReader file = new FileReader(this.filename)) {
			Object obj = JSONValue.parse(file);
			JSONArray array = (JSONArray) obj;
			for (int i = 0; i < array.size(); i++) {
				JSONObject line = (JSONObject) array.get(i);
				Integer zip = ((Long) line.get("zip_code")).intValue();
				String dateString = (String) line.get("etl_timestamp");
				Matcher matcher = pattern.matcher(dateString);
				if (zip==null || dateString==null || !matcher.find()) {
					continue;
				}
				String date = matcher.group();
				Object partObject = line.getOrDefault("partially_vaccinated", 0);
				Integer partialVaccinations=0;
				if (partObject instanceof Integer) {
					partialVaccinations = (Integer) partObject;
				} else if (partObject instanceof Long) {
					partialVaccinations =((Long) partObject).intValue();
				}
				else if (partObject instanceof String) {
					partialVaccinations = Integer.parseInt(((String) partObject));
				}
				Object fullObject = line.getOrDefault("fully_vaccinated", 0);
//				System.out.println(fullObject.getClass());
				Integer fullVaccinations=0;
				if (fullObject instanceof Integer) {
					fullVaccinations = (Integer) fullObject ;
				} else if (fullObject  instanceof Long) {
					fullVaccinations =((Long) fullObject ).intValue();
//					System.out.println("1");
				}
				else if (fullObject  instanceof String) {
					fullVaccinations = Integer.parseInt(((String) fullObject ));
				}
//				Integer partialVaccinations=((Integer) line.getOrDefault("partially_vaccinated", 0));
//				if (line.get("partially_vaccinated")==null) {
//					partialVaccinations =0;
//				}
//				else {
//					partialVaccinations = Integer.valueOf((String) line.getOrDefault("partially_vaccinated", 0));
//				}
//				Integer fullVaccinations=((Integer) line.getOrDefault("fully_vaccinated", 0));
//				if (line.get("fully_vaccinated")==null) {
//					fullVaccinations =0;
//				}
//				else {
//					fullVaccinations = Integer.valueOf((String) line.getOrDefault("fully_vaccinated", 0));
//				}
//				Integer fullVaccinations = Integer.valueOf((String) line.getOrDefault("fully_vaccinated", 0));
				ret.putIfAbsent(zip, new Area(zip));
				ret.get(zip).addPartialVaccination(date, partialVaccinations);
//				System.out.println(fullVaccinations);
				ret.get(zip).addFullVaccination(date, fullVaccinations);
			}
		}
		
		return ret;
	}

}
