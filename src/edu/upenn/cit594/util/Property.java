package edu.upenn.cit594.util;

public class Property {
	Integer marketValue;
	Integer livableArea;
	public Property(Integer marketValue, Integer livableArea) {
		this.marketValue = marketValue;
		this.livableArea = livableArea;
	}
	public Integer getMarketValue() {
		return marketValue;
	}
	public Integer getLivableArea() {
		return livableArea;
	}
}
