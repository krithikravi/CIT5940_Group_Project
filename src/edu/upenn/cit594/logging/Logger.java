package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

	private PrintWriter out;
	private String outFile;
	
	private static Logger instance;
	
	private Logger() {
		this.out = new PrintWriter(System.err, true);
	}
	
	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	public void loggerFileLocation(String fileName) throws IOException {
		if (this.out != null && this.outFile!=null) {
			this.out.close();
		}
		
		if (fileName != null) {
			this.out = new PrintWriter(new FileWriter(fileName, true));
			outFile=fileName;
		} else {
			this.out = new PrintWriter(System.err, true);
			outFile=null;
		}
	}
	
	public void log(String msg) {
		out.println(System.currentTimeMillis() + " " + msg);
		out.flush();
	}
	
	public void close() {
		if (out != null) {
			out.flush();
			out.close();
		}
	}
}
