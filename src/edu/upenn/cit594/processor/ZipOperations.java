package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.datamanagement.Input;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.Area;

public abstract class ZipOperations {
	protected HashMap<Integer, Area> zipCodes;
	HashMap<String,ArrayList<String>> memo = new  HashMap<String,ArrayList<String>>();
	Logger logger;
	Input inputHelper;

	public ZipOperations(HashMap<Integer, Area> zipCodes, Logger logger, Input inputHelper) {
		this.zipCodes = zipCodes;
		this.logger = logger;
		this.inputHelper=inputHelper;
	}
	
	
}
