package edu.upenn.cit594.processor;

import edu.upenn.cit594.util.Area;

public class MarketValueVaccinationStrategy implements SingleStrategy {

	@Override
	public String calculateOperation(Area location) {
		String ret ="0";
		if (location.getPopulation()!=0 && location.getMaxFullVaccinations()!=0) {
			ret=String.valueOf((float)location.getTotalMarketValue()/(float)location.getPopulation()/(float)location.getMaxFullVaccinations());
		}
		return ret;
	}

}
