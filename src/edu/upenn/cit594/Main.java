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

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Set<String> argsTraversed = new HashSet<String>();
		Logger logger = Logger.getInstance();
		try {
			
			// Maybe try this to have the program take more variable number of arguments
			if (args.length == 0 || args.length > 4) {
				throw new IllegalArgumentException("Requires up to 4 arguments: --population, --covid, --properties, and --log");
			}
			
//			Check that all arguments are properly formatted
			for (int i = 0; i<args.length; i++) {
				String regex = "^--(?<name>.+?)=(?<value>.+)$";
			    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			    if (!pattern.matcher(args[i]).find()) {
			    	throw new IllegalArgumentException("Required argument format: --population=pop.csv"
			    			+ "--covid=cov.csv --properties=props.csv --log=log.txt");
			    }
			    
			    if (args[i].startsWith("--population=")) {
			    	if (!argsTraversed.add("--population")) {
			    		throw new IllegalArgumentException("More than one argument for population file");
			    		
			    	}
			    	
			    }
			    if (args[i].startsWith("--covid=")) {
			    	if (!argsTraversed.add("--covid")) {
			    		throw new IllegalArgumentException("More than one argument for covid file");
			    		
			    	}
			    	
			    }
			    if (args[i].startsWith("--properties=")) {
			    	if (!argsTraversed.add("--properties")) {
			    		throw new IllegalArgumentException("More than one argument for properties file");
			    		
			    	}
			    	
			    }
			    if (args[i].startsWith("--log=")) {
			    	if (!argsTraversed.add("--log")) {
			    		throw new IllegalArgumentException("More than one argument for log file");
			    		
			    	}
			    	
			    }
			}
			
//			This should be setting the location for the log file
			for (int i = 0; i<args.length; i++) {
				if (args[i].startsWith("--log")) {
					logger.loggerFileLocation(args[i].split("=")[1]);
					break;
				}
			}
			
//			Log the arguments
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i<args.length; i++) {
				sb.append(" " + args[i]);
			}
			logger.log(sb.toString().trim());

			HashMap<Integer, Area> data = new HashMap<Integer, Area>();

			for (int i = 0; i<args.length; i++) {
				if (!args[i].startsWith("--log")) {
					String filename=args[i].split("=")[1];
					Reader reader;
					if (filename.toLowerCase().endsWith("json")) {
						reader = new JSONReader(filename, data);
						
					}
					else if (filename.toLowerCase().endsWith("csv")){
						reader = new CSVReader(filename, data);
					}
					else {
						throw new IllegalArgumentException("Invalid file extension");
			    		
					}
					data = (HashMap<Integer, Area>) reader.read().clone();
					logger.log(filename);

				}
			}
			Input inputHelper = new Input();
			UserInterface ui = new UserInterface(data, logger, argsTraversed,inputHelper);
			ui.runProgram();
			
		} catch (IllegalArgumentException e) {
			System.err.println("Error: " + e.getMessage());
			System.out.flush();
		} catch (IOException e) {
			System.err.println("File error: " + e.getMessage());
			System.out.flush();
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
			System.out.flush();
		}

	}

}
