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

	@Override
	public HashMap read() throws IOException {
		HashMap<Integer, Area> ret = new HashMap<Integer, Area>();
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}", Pattern.CASE_INSENSITIVE);
		
		try (FileReader file = new FileReader(this.filename)) {
			Object obj = JSONValue.parse(file);
			JSONArray array = (JSONArray) obj;
			for (int i = 0; i < array.size(); i++) {
				JSONObject line = (JSONObject) array.get(i);
				Integer zip = (Integer) line.get("zip_code");
				String dateString = (String) line.get("etl_timestamp");
				Matcher matcher = pattern.matcher(dateString);
				if (zip==null || dateString==null || !matcher.find()) {
					continue;
				}
				String date = matcher.group();
				Integer partialVaccinations = (Integer) line.getOrDefault("partially_vaccinated", 0);
				Integer fullVaccinations = (Integer) line.getOrDefault("fully_vaccinated", 0);
				ret.putIfAbsent(zip, new Area(zip));
				ret.get(zip).addPartialVaccination(date, partialVaccinations);
				ret.get(zip).addFullVaccination(date, fullVaccinations);
			}
		}
		
		return ret;
	}

}
