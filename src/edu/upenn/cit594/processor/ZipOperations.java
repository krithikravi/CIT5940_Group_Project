package edu.upenn.cit594.processor;

import java.util.HashMap;

import edu.upenn.cit594.util.Area;

public abstract class ZipOperations {
	protected HashMap<Integer, Area> zipCodes;

	public ZipOperations(HashMap<Integer, Area> zipCodes) {
		this.zipCodes = zipCodes;
	}
	
	
}
