package edu.sdse.csvprocessor;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileReader;

public class CityCSVProcessor {
	private List<CityRecord> allRecords = new ArrayList<>();
	private Map<String, List<CityRecord>> cityMap = new HashMap<String, List<CityRecord>>();
	
	public void readAndProcess(File file) {
		//Try with resource statement (as of Java 8)
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			//Discard header row
			br.readLine();
			
			String line;
			
			while ((line = br.readLine()) != null) {
				// Parse each line
				String[] rawValues = line.split(",");
				
				int id = convertToInt(rawValues[0]);
				int year = convertToInt(rawValues[1]);
				String city = convertToString(rawValues[2]);
				int population = convertToInt(rawValues[3]);
								
				CityRecord cityRecord = new CityRecord(id, year, city, population);
				
				allRecords.add(cityRecord);
				
				addCityRecordToMap(cityRecord);
				
				System.out.println(cityRecord);
			}
		} catch (Exception e) {
			System.err.println("An error occurred:");
			e.printStackTrace();
		}
		processDataByCity();
	}
	
	private void addCityRecordToMap(CityRecord cityRecord) {
		List<CityRecord> cityList = cityMap.get(cityRecord.getCity());
		if(cityList == null) {
		    cityList = new ArrayList<>();
		}
		cityList.add(cityRecord);
		cityMap.put(cityRecord.getCity(), cityList);
	}
	
	private void processDataByCity() {
		for (Map.Entry<String, List<CityRecord>> entry : cityMap.entrySet()) {
			int numberOfEntries = 0;
			int populationSum = 0;
			int minYear = Integer.MAX_VALUE;
			int maxYear = Integer.MIN_VALUE;
			for (CityRecord record : entry.getValue()) {
				    numberOfEntries++;
				    populationSum = populationSum + record.getPopulation();
				    minYear = Math.min(minYear, record.getYear());
				    maxYear = Math.max(maxYear, record.getYear());
			}
			double avgPopulation = (double) populationSum / numberOfEntries;
			
			CityAggregateData cityAggregateData  = new CityAggregateData(entry.getKey(), numberOfEntries, avgPopulation, minYear, maxYear);
			System.out.println(cityAggregateData);
		}
	}
	
	private String cleanRawValue(String rawValue) {
		return rawValue.trim();
	}
	
	private int convertToInt(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		return Integer.parseInt(rawValue);
	}
	
	private String convertToString(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		
		if (rawValue.startsWith("\"") && rawValue.endsWith("\"")) {
			return rawValue.substring(1, rawValue.length() - 1);
		}
		
		return rawValue;
	}
	
	public static final void main(String[] args) {
		CityCSVProcessor reader = new CityCSVProcessor();
		
		File dataDirectory = new File("data/");
		File csvFile = new File(dataDirectory, "Cities.csv");
		
		reader.readAndProcess(csvFile);
	}
}
