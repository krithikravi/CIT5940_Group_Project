package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

//	private static final OutputStream NULL_OUTPUT = new OutputStream() {
//	    public void write(int b) {}
//	};

	private PrintWriter out; //= new PrintWriter(System.out); // Added new PrintWriter(System.out)
	private String outFile;
	
	// 1. private constructor with initialization
    private Logger() {
        //            File file = new File("src/edu/upenn/cit594/datamanagement/output.txt");
		out = new PrintWriter(System.err);
    }
	
	// 2. singleton instance
	private static Logger instance = new Logger();
	
	// 3. singleton accessor method
	public static Logger getInstance() { return instance; }
	
	// non-static method
	public void log(String msg) {
		out.println(System.currentTimeMillis() + " " + msg);
		out.flush();
	}
	
	public void loggerFileLocation(String fileName) {
		this.outFile = fileName;
		try {
			out = new PrintWriter(new FileWriter(this.outFile, true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
