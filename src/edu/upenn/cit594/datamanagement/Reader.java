package edu.upenn.cit594.datamanagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Reader {
    protected String filename;
    protected HashMap ret;
    
    public Reader(String filename) {
    	if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty");
        }
        this.filename = filename;
    }
    
    
    public abstract HashMap read() throws IOException;

	public static Reader getReader(String filename) {
		if (filename.toLowerCase().endsWith(".csv")){
			return new CSVReader(filename);
		}
		else {
			return new JSONReader(filename);
		}
	}
}