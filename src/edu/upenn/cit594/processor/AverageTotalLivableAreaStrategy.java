package edu.upenn.cit594.processor;

import edu.upenn.cit594.util.Area;

public class AverageTotalLivableAreaStrategy implements SingleStrategy {

	@Override
	public String calculateOperation(Area location) {
		String ret="0";
		if (!location.getProperties().isEmpty()) {
			ret=String.valueOf(location.getTotalLivableArea()/location.getProperties().size());
		}
		return ret;
	}

}
