package edu.upenn.cit594.datamanagement;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.util.*;
import edu.upenn.cit5940.CSVFormatException;

public class CSVReader extends Reader {
	protected FileReader reader;

	public CSVReader(String filename) {
		super(filename);
		FileReader reader = new FileReader(this.filename);
	}

	@Override
	public HashMap read() throws IOException {
		ArrayList<String> columns = new ArrayList<String>();
		int zipIndex;
		int dateIndex;
		int partialIndex;
		int fullIndex;
		int popIndex;
		int areaIndex;
		int marketIndex;
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}", Pattern.CASE_INSENSITIVE);
		while (true) {
			String[] row = readRow();
			if (row==null) {
				break;
			}
			if (ret.isEmpty()) {
				columns.addAll(row);
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
				Integer zip = Integer.valueOf(row[zipIndex]);
				String dateString = row[dateIndex];
				Matcher matcher = pattern.matcher(dateString);
				if (zip==null || dateString==null || !matcher.find()) {
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
				Integer zip = Integer.valueOf(row[zipIndex]);
				Integer totalArea = Integer.valueOf(row[areaIndex]);
				Integer marketValue = Integer.valueOf(row[marketIndex]);
				ret.putIfAbsent(zip, new Area(zip));
				ret.get(zip).addProperty(new Property(marketValue,totalArea));
			}
			if (columns.size()==2) {
				Integer zip = Integer.valueOf(row[zipIndex]);
				Integer pop = Integer.valueOf(row[popIndex]);
				ret.putIfAbsent(zip, new Area(zip));
				ret.get(zip).setPopulation(pop);
			}
		}
		return ret;
	}
	
	public String[] readRow() throws IOException, CSVFormatException {	
    	enum isEscaped {ESCAPED,NOT_ESCAPED};
    	enum prevQuote {QUOTE,NOT_QUOTE};
    	enum prevCR {CR,NOT_CR};
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
    		int curr = reader.read();
    		if (curr==-1) {
    			if ("CR".equals(cr.toString()) || escape.equals(isEscaped.ESCAPED) || (quoteIndex!=-1 && quoteIndex!=index-1)) {
    				throw new CSVFormatException();
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
    					throw new CSVFormatException();
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
    					throw new CSVFormatException();
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
    					throw new CSVFormatException();
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
    					throw new CSVFormatException();
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
					throw new CSVFormatException();
				}
    			row.append((char) curr);
    			break;
    		}
    		
    	}
    	

    }

}
