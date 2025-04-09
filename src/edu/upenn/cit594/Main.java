package edu.upenn.cit594;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import edu.upenn.cit594.datamanagement.*;
//import edu.upenn.cit594.processor.*;
//import edu.upenn.cit594.ui.*;
import edu.upenn.cit594.util.*;

public class Main {

	public static void main(String[] args) {
		try {
			HashMap ret = new HashMap<Integer, Area>();
			Reader reader = Reader.getReader(args[0], ret);;
			HashMap<Integer,Area> covid = reader.read();
			System.out.println(covid.keySet());
			System.out.println(covid.get(19102).getFullVaccinations().values());
//			if (args.length != 3) {
//				throw new IllegalArgumentException("Required: tweets_file states_file log_file");
//			}
//			
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
	}

}
