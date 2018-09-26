package com.abc.ib.gl.app.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.abc.ib.gl.enums.AccountType;
import com.abc.ib.gl.model.Position;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class InitialPositionLoader {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		try {

			ClassLoader classLoader = (new InitialPositionLoader()).getClass().getClassLoader();
			String fileName="Input_StartOfDay_Positions.txt";
			File file = new File(classLoader.getResource(fileName).getFile());
			List<Position> empList = new ArrayList<>();
			FileReader reader = new FileReader(file);
			CSVReader csvReader =  		new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll(); 
			for (String[] row : allData) { 
				int index=0;
				Position emp =new Position();
				for (String data : row) { 
					System.out.print(data + "\t"); 
					if (index == 0)
						emp.setInstrument(data);
					else if (index == 1)
						emp.setAccountNum(Integer.parseInt(data));
					else if (index == 2)
						emp.setAcType(AccountType.valueOf(data));
					else if (index == 3)
						emp.setQuantity(Integer.parseInt(data));
					else
						System.out.println("invalid data::" + data);
					index++;
				} 
				System.out.println(emp	); 
				empList.add(emp);
			} 


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<Position> readPositions(String filename) {

		try {

			ClassLoader classLoader = (new InitialPositionLoader()).getClass().getClassLoader();
			String fileName="Input_StartOfDay_Positions.txt";
			File file = new File(classLoader.getResource(fileName).getFile());
			List<Position> empList = new ArrayList<>();
			FileReader reader = new FileReader(file);
			CSVReader csvReader =  		new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll(); 
			for (String[] row : allData) { 
				int index=0;
				Position emp =new Position();
				for (String data : row) { 
					System.out.print(data + "\t"); 
					if (index == 0)
						emp.setInstrument(data);
					else if (index == 1)
						emp.setAccountNum(Integer.parseInt(data));
					else if (index == 2)
						emp.setAcType(AccountType.valueOf(data));
					else if (index == 3)
						emp.setQuantity(Integer.parseInt(data));
					else
						System.out.println("invalid data::" + data);
					index++;
				} 
				System.out.println(emp	); 
				empList.add(emp);
			} 
			return empList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}


}
