package tech.migsfactory.injectionjava.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.io.InputStreamReader;

public class CSVRead{
	public Map< String,String[]> readCSV(String filePath) {
		List<String[]> data = new ArrayList<>();
		String line;
		System.out.println("Resource path: " + getClass().getClassLoader().getResource(filePath));
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				getClass().getClassLoader().getResourceAsStream(filePath)))){
			
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				data.add(values);
				for (String value : values) {
					System.out.print(value + "\t");
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String,String[]> index = new HashMap<>();
		int num;
		if (filePath.matches(".*radioactive_medicines.*")) {
			num = 5;
		} else {
			num = 0;
		}
		for (String[] row: data) {
			index.put(row[num], row);
		}
		
		return index;
	}
	public String searchRi(Map<String, String[]> index, String ri){
		String results = index.get(ri)[0] +" "+ index.get(ri)[1];
		
		return results;
	}
}