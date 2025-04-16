package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.util.*;

public abstract class Reader {
    protected String filename;
    protected HashMap<Integer, Area> ret;
    
    public Reader(String filename) {
    	if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty");
        }
        this.filename = filename;
        this.ret = new HashMap<Integer, Area>();
    }
    
    public Reader(String filename, HashMap current) {
    	if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty");
        }
        this.filename = filename;
        this.ret=(HashMap<Integer, Area>) current.clone();
    }
    
    
    public abstract HashMap read() throws IOException;

	public static Reader getReader(String filename,HashMap<Integer, Area> ret) throws FileNotFoundException {
		if (filename.toLowerCase().endsWith(".csv")){
			return new CSVReader(filename,ret);
		}
		else {
			return new JSONReader(filename,ret);
		}
	}
}