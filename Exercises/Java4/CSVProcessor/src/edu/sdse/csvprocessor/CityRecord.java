package edu.sdse.csvprocessor;

public class CityRecord {
	private int id;
	private int year;
	private String city;
	private int population;
	
	public CityRecord(int id, int year, String city, int population) {
		this.id = id;
		this.year = year;
		this.city = city;
		this.population = population;
	}
	
	public String toString(){
		return "id: " + this.id + ", year: " + this.year + ", city: " + this.city + ", population: " + this.population;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public int getPopulation() {
		return this.population;
	}
	
	public int getYear() {
		return this.year;
	}
}
