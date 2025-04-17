package edu.upenn.cit594.processor;

import edu.upenn.cit594.util.Area;

public class TotalMarketValueStrategy implements SingleStrategy {

	@Override
	public String calculateOperation(Area location) {
		String ret ="0";
		if (location.getPopulation()!=0) {
			ret=String.valueOf(location.getTotalMarketValue()/location.getPopulation());
		}
		return ret;
	}

}
