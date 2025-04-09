package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.util.*;

public class CSVReader extends Reader {

	public CSVReader(String filename) {
		super(filename);
	}

	@Override
	public HashMap read() throws IOException {
		ArrayList<State> ret = new ArrayList<State>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] strArray = line.split(",");
				if (strArray.length!=3) {
					continue;
				}
				ret.add(new State(strArray[0], Double.parseDouble(strArray[1]), Double.parseDouble(strArray[2])));
			}
		}
		return ret;
	}

}
