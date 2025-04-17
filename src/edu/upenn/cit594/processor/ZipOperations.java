package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.Area;

public abstract class ZipOperations {
	protected HashMap<Integer, Area> zipCodes;
	HashMap<String,ArrayList<String>> memo = new  HashMap<String,ArrayList<String>>();
	Logger logger;

	public ZipOperations(HashMap<Integer, Area> zipCodes, Logger logger) {
		this.zipCodes = zipCodes;
		this.logger = logger;
	}
	
	
}
