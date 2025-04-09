package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.util.*;

public class CSVReader extends Reader {
	protected FileReader charReader;
	enum isEscaped {ESCAPED,NOT_ESCAPED};
	enum prevQuote {QUOTE,NOT_QUOTE};
	enum prevCR {CR,NOT_CR};

	public CSVReader(String filename) throws FileNotFoundException {
		super(filename);
		this.charReader = new FileReader(this.filename);
	}
	
	public CSVReader(String filename, HashMap curr) throws FileNotFoundException {
		super(filename,curr);
		charReader = new FileReader(this.filename);
	}

	@Override
	public HashMap read() throws IOException {
		List<String> columns = new ArrayList<String>();
		int zipIndex = -1;
		int dateIndex = 0;
		int partialIndex = 0;
		int fullIndex = 0;
		int popIndex = 0;
		int areaIndex = 0;
		int marketIndex = 0;
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}", Pattern.CASE_INSENSITIVE);
		Pattern pattern2 = Pattern.compile("^\\d{5}");
		while (true) {
			String[] row = readRow();
//			System.out.println(row[0]);
			if (row==null) {
				break;
			}
			try {
			if (zipIndex==-1) {
				columns =Arrays.asList(row);
				zipIndex = columns.indexOf("zip_code");
				if (columns.size()==9) {
					dateIndex=columns.indexOf("etl_timestamp");
					partialIndex=columns.indexOf("partially_vaccinated");
					fullIndex=columns.indexOf("fully_vaccinated");
				}
				if (columns.size()==3) {
					areaIndex=columns.indexOf("total_livable_area");
					marketIndex = columns.indexOf("market_value");
				}
				if (columns.size()==2) {
					popIndex = columns.indexOf("population");
				}
				continue;
			}
			if (columns.size()==9) {
				if (row[zipIndex]=="" || row[dateIndex]=="") {
					continue;
				}
				Integer zip = Integer.valueOf(row[zipIndex]);
				String dateString = row[dateIndex];
				Matcher matcher = pattern.matcher(dateString);
				if (!matcher.find()) {
					continue;
				}
				String date = (String) matcher.group();
				Integer partialVaccinations = Integer.valueOf(row[partialIndex]);
				Integer fullVaccinations = Integer.valueOf(row[fullIndex]);
				ret.putIfAbsent(zip, new Area(zip));
				ret.get(zip).addPartialVaccination(date, partialVaccinations);
				ret.get(zip).addFullVaccination(date, fullVaccinations);
			}
			if (columns.size()==3) {
				Matcher matcher2 = pattern2.matcher(row[zipIndex].toString());
				if (!matcher2.find()) {
					continue;
				}
				Integer zip = Integer.valueOf(matcher2.group());
//				System.out.println(zip+row[areaIndex]+row[marketIndex]);
				Integer totalArea = (int) Float.parseFloat(row[areaIndex]);
				Integer marketValue = (int) Float.parseFloat(row[marketIndex]);
				ret.putIfAbsent(zip, new Area(zip));
//				System.out.println(ret.get(zip).getProperties());
				ret.get(zip).addProperty(new Property(marketValue,totalArea));
//				System.out.println(ret.get(zip).getProperties());
			}
			if (columns.size()==2) {
				Matcher matcher2 = pattern2.matcher(row[zipIndex].toString());
				if (!matcher2.find()) {
					continue;
				}
				Integer zip = Integer.valueOf((String) matcher2.group());
				Integer pop = Integer.valueOf(row[popIndex]);
				ret.putIfAbsent(zip, new Area(zip));
				ret.get(zip).setPopulation(pop);
			}
			} catch (Exception e) {
				continue;
			}
		}
		return ret;
	}
	
	public String[] readRow() throws IOException {	
    	
    	ArrayList<String> rows=new ArrayList<String>();
    	StringBuilder row=new StringBuilder("");
    	isEscaped escape = isEscaped.NOT_ESCAPED;
    	prevQuote quote = prevQuote.NOT_QUOTE;
    	prevCR cr = prevCR.NOT_CR; 
    	int index=0;
    	int quoteIndex=-1;
    	int crIndex=-1;
    	while (true) {
    		index++;
    		int curr = this.charReader.read();
    		if (curr==-1) {
    			if ("CR".equals(cr.toString()) || escape.equals(isEscaped.ESCAPED) || (quoteIndex!=-1 && quoteIndex!=index-1)) {
    				throw new IOException();
    			}
    			if (row.length() > 0 || rows.size() > 0) {
    				
    		        rows.add(row.toString());
    		        return rows.toArray(new String[rows.size()]);
    		    }
    			return null;
    		}
    		
    		switch(curr) {
    		case 34:
    			if ("NOT_ESCAPED".equals(escape.toString())) {
    				if ((quoteIndex==-1 && index!=1) || (quoteIndex!=-1 && quoteIndex!=index-1)|| "CR".equals(cr.toString()) || escape.equals(isEscaped.ESCAPED)) {
    					throw new IOException();
    				}
    				if (quoteIndex!=-1) {
    					row.append('"');
    				}
    				escape=isEscaped.ESCAPED;
    				quoteIndex=index;
    				break;
    			}
    			quoteIndex=index;
    			escape=isEscaped.NOT_ESCAPED;
    			break;
    		case 44:
    			if ("NOT_ESCAPED".equals(escape.toString())) {
    				if ("CR".equals(cr.toString()) || (quoteIndex!=-1 && quoteIndex!=index-1)){
    					throw new IOException();
    				}
    				rows.add(row.toString());
    				row = new StringBuilder("");
    				escape = isEscaped.NOT_ESCAPED;
    		    	quote = prevQuote.NOT_QUOTE;
    		    	cr = prevCR.NOT_CR; 
    		    	quoteIndex=-1;
    		    	crIndex=-1;
    		    	index=0;
    				break;
    			}
    			else {
    				row.append(',');
    				break;
    			}
    		case 13:
    			if ("NOT_ESCAPED".equals(escape.toString())) {
    				if ("CR".equals(cr.toString())){
    					throw new IOException();
    				}
    				cr=prevCR.CR;
    				crIndex=index;
    				break;
    			}
    			else {
    				row.append('\r');
    				break;
    			}
    		case 10:
    			if ("NOT_ESCAPED".equals(escape.toString())) {
    				if (crIndex!=-1 && crIndex!=index-1 || (quoteIndex!=-1 && (quoteIndex<index-2))) {
    					throw new IOException();
    				}
    				rows.add(row.toString());
    				return rows.toArray(new String[rows.size()]);
    			}
    			else {
    				row.append('\n');
    				break;
    			}
    		default:
//    			if ("CR".equals(cr.toString()) || curr<0x20 || curr>0x7E || curr==0x22){
    			if ("CR".equals(cr.toString())) {
    				throw new IOException();
				}
    			row.append((char) curr);
    			break;
    		}
    		
    	}
    	

    }

}
