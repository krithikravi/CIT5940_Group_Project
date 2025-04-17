package edu.upenn.cit594;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.*;
import edu.upenn.cit594.logging.Logger;
//import edu.upenn.cit594.processor.*;
import edu.upenn.cit594.ui.*;
import edu.upenn.cit594.util.*;

public class Main {

	public static void main(String[] args) {

		Set<String> argsTraversed = new HashSet<String>();
		Logger logger = Logger.getInstance();
		try {
//			HashMap ret = new HashMap<Integer, Area>();
//			Reader reader = Reader.getReader(args[0].split("=")[1], ret);;
//			HashMap<Integer,Area> covid = reader.read();
//			System.out.println(covid.keySet());
//			System.out.println();
//			System.out.println(covid.get(19102).getFullVaccinations().values());
//			System.out.println(covid.get(19153).getPopulation());
//			System.out.println(covid.get(19104).getProperties().getFirst().getLivableArea());
//			if (args.length == 0 || args.length>4) {
//				throw new IllegalArgumentException("Required: tweets_file states_file log_file");
//			}
			if (args.length != 4) {
				throw new IllegalArgumentException("Requires arguments for --population, --covid, --properties, and --log");
			}
			
//			Check that all arguments are properly formatted
			for (int i = 0; i<args.length; i++) {
				argsTraversed.clear(); //= new HashSet<String>(); 
				String regex = "^--(?<name>.+?)=(?<value>.+)$";
			    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			    if (!pattern.matcher(args[i]).find()) {
			    	throw new IllegalArgumentException("Required argument format: --population=pop.csv"
			    			+ "--covid=cov.csv --properties=props.csv --log=log.txt");
			    }
			    
			    if (args[i].startsWith("--population=")) {
			    	if (!argsTraversed.add("--population=")) {
			    		throw new IllegalArgumentException("More than one argument for population file");
			    		
			    	}
			    	
			    }
			    if (args[i].startsWith("--covid=")) {
			    	if (!argsTraversed.add("--covid=")) {
			    		throw new IllegalArgumentException("More than one argument for covid file");
			    		
			    	}
			    	
			    }
			    if (args[i].startsWith("--properties=")) {
			    	if (!argsTraversed.add("--properties=")) {
			    		throw new IllegalArgumentException("More than one argument for properties file");
			    		
			    	}
			    	
			    }
			    if (args[i].startsWith("--log=")) {
			    	if (!argsTraversed.add("--log=")) {
			    		throw new IllegalArgumentException("More than one argument for log file");
			    		
			    	}
//			    	 loggerAccessor = new LoggerAccessor(args[i]);
			    	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			    	//wrong file name, need to parse out the --log
			    	 logger.loggerFileLocation(args[i]);
			    	
			    }
			}
			
//			if (!args[0].startsWith("--population=")) {
//				throw new IllegalArgumentException("Required argument format: --population=pop.csv"
//			    			+ "--covid=cov.csv --properties=props.csv --log=log.txt");
//			}
//			if (!args[1].startsWith("--covid=")) {
//				throw new IllegalArgumentException("Required argument format: --population=pop.csv"
//			    			+ "--covid=cov.csv --properties=props.csv --log=log.txt");
//			}
//			if (!args[2].startsWith("--properties=")) {
//				throw new IllegalArgumentException("Required argument format: --population=pop.csv"
//			    			+ "--covid=cov.csv --properties=props.csv --log=log.txt");
//			}
//			if (!args[3].startsWith("--log=")) {
//				throw new IllegalArgumentException("Required argument format: --population=pop.csv"
//			    			+ "--covid=cov.csv --properties=props.csv --log=log.txt");
//			}
			
			
			
			/* Need to check this
			The format of the COVID data file can not be determined from the filename extension (“csv”
			or “json”, case-insensitive). */
//			if (!(args[2].toLowerCase().endsWith(".csv") || args[2].toLowerCase().endsWith(".json"))) {
//				throw new IllegalArgumentException("covid file has incorrect extension");
//			}

//			HashMap ret = new HashMap<Integer, Area>();
			HashMap ret = new HashMap<Integer, Area>();
//			Reader reader = Reader.getReader(args[0].split("=")[1], ret);;
//			HashMap<Integer,Area> covid = reader.read();
			
			
			
			for (int i = 0; i<args.length; i++) {
//				ret.clear();
				if (!args[i].startsWith("--log")) {
					String filename=args[i].split("=")[1];
					Reader reader;
					if (!filename.toLowerCase().endsWith("json")) {
						reader = new CSVReader(filename, ret);
					}
					else {
						reader = new JSONReader(filename, ret);
					}
					ret = (HashMap) reader.read().clone();
//					HashMap ret = new HashMap<Integer, Area>();
//					Reader reader = Reader.getReader(args[0].split("=")[1], ret);;
//					HashMap<Integer,Area> covid = reader.read();
				}
			}
			
			UserInterface ui = new UserInterface(ret);
			ui.runProgram();
			
		} catch (IllegalArgumentException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("File error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
		}
//		UserInterface.printMenu();

	}

}
