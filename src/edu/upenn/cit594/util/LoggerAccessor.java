package edu.upenn.cit594.util;

import edu.upenn.cit594.logging.Logger;

public class LoggerAccessor {
	Logger logger = Logger.getInstance();
	public LoggerAccessor(String fileName) {
		logger.loggerFileLocation(fileName);
	}
	
	public void sendToLogger(String inputString) {
		logger.log(inputString);
	}
	
	public void loggerPrinter () {
		logger.toString();
	}

}
