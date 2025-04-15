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
			if (args.length == 0 || args.length>4) {
				throw new IllegalArgumentException("Required: tweets_file states_file log_file");
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
			Reader reader = Reader.getReader(args[0].split("=")[1], ret);;
			HashMap<Integer,Area> covid = reader.read();
			
			
			
			for (int i = 1; i<args.length; i++) {
//				ret.clear();
				if (args[i].startsWith("--population")) {
					reader = Reader.getReader(args[i].split("=")[1], ret);
					ret = reader.read();
//					HashMap ret = new HashMap<Integer, Area>();
//					Reader reader = Reader.getReader(args[0].split("=")[1], ret);;
//					HashMap<Integer,Area> covid = reader.read();
				}
			}
			
			UserInterface ui = new UserInterface(ret);
			ui.runProgram();
			
//			if ((!args[0].toLowerCase().endsWith(".txt") && !args[0].toLowerCase().endsWith(".json")) || !args[1].toLowerCase().endsWith(".csv")) {
//				throw new IllegalArgumentException("Incorrect file extension.");
//			}
//			File tweetFile = new File(args[0]);
//			File stateFile = new File(args[1]);
//			File logFile = new File(args[2]);
//			
//			if (!tweetFile.canRead()) {
//				throw new IOException("Cannot read tweet file: " + args[0]);
//			}
//			if (!stateFile.canRead()) {
//				throw new IOException("Cannot read state file: " + args[1]);
//			}
//
//			if (logFile.exists()) {
//				if (!logFile.canWrite()) {
//					throw new IOException("Cannot write to existing log file: " + args[2]);
//				}
//			} else {
//				File logDir = logFile.getParentFile();
//				if (logDir != null && !logDir.canWrite()) {
//					throw new IOException("Cannot create log file in directory: " + logDir);
//				}
//			}
//			
//			Reader reader = Reader.getReader(args[0]);
//			ArrayList<Tweet> tweets = reader.read();
//			
//			Reader stateReader = Reader.getReader(args[1]);
//			ArrayList<State> states = stateReader.read();
//			
//			TweetFinder fluTweets = new TweetFinder(tweets, states, args[2]);
//			TreeMap finalTweets = fluTweets.findTweets();
//			PrintResult.printResult(finalTweets);
			
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
