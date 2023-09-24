package edu.sdse.csvprocessor;

public class CityAggregateData {
	private String city;
	private int numberOfEntries;
	private double avgPopulation;
	private int minYear;
	private int maxYear;
	
	public CityAggregateData(String city, int numberOfEntries, double avgPopulation, int minYear, int maxYear) {
		this.city = city;
		this.numberOfEntries = numberOfEntries;
		this.avgPopulation = avgPopulation;
		this.minYear = minYear;
		this.maxYear = maxYear;
	}
	
	public String toString(){
		return "Average population of " + city + "(" + minYear + "-" + maxYear + "; " + numberOfEntries + " entries): " + avgPopulation;
	}
}
