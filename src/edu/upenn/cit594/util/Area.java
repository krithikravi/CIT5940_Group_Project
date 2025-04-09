package edu.upenn.cit594.util;
import java.util.ArrayList;
import java.util.HashMap;

public class Area {
<<<<<<< HEAD
	protected Integer zipcode;
	protected Integer population;
	protected HashMap<String, Integer> partialVaccinations;
	protected HashMap<String, Integer> fullVaccinations;
	protected ArrayList<Property> properties;
	protected Integer totalMarketValue;
	protected Integer totalLivableArea;
	public Area(Integer zipcode) {
		this.zipcode = zipcode;
		this.population=0;
		this.partialVaccinations=new HashMap<String,Integer>();
		this.fullVaccinations= new HashMap<String, Integer>();
		
		
		
	}

	public void addPartialVaccination(String date, Integer num) {
		this.partialVaccinations.put(date, this.partialVaccinations.getOrDefault(date, 0)+num);
	}
	public void addFullVaccination(String date, Integer num) {
		this.fullVaccinations.put(date, this.fullVaccinations.getOrDefault(date, 0)+num);
	}
	public void addProperty(Property property) {
		this.properties.add(property);
		this.totalMarketValue+=property.getMarketValue();
		this.totalLivableArea+=property.getLivableArea();
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public Integer getPopulation() {
		return population;
	}

	public HashMap<String, Integer> getPartialVaccinations() {
		return partialVaccinations;
	}

	public HashMap<String, Integer> getFullVaccinations() {
		return fullVaccinations;
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}

	public Integer getTotalMarketValue() {
		return totalMarketValue;
	}

	public Integer getTotalLivableArea() {
		return totalLivableArea;
	}
	
	
=======
	String zipcode;
	Integer population;
	protected HashMap<String, Integer> vaccinations;
	protected ArrayList<Property> properties;
>>>>>>> 9b3159aea1cdbb1c19a12e85603ec35d88c62f8d
}
