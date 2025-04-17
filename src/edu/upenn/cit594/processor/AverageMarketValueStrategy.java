package edu.upenn.cit594.processor;

import edu.upenn.cit594.util.Area;

public class AverageMarketValueStrategy implements SingleStrategy {

	@Override
	public String calculateOperation(Area location) {
		String ret="0";
		if (!location.getProperties().isEmpty()) {
			ret =String.valueOf(location.getTotalMarketValue()/location.getProperties().size()) ;
		}
		return ret;
	}

}
