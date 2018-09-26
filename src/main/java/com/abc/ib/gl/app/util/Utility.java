package com.abc.ib.gl.app.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.abc.ib.gl.enums.AccountType;
import com.abc.ib.gl.model.OutputPosition;
import com.abc.ib.gl.model.Position;
import com.abc.ib.gl.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Utility {
	public static List<Position> readSODPositions(String fileName) {

		try {
			if(fileName==null)
				fileName = "Input_StartOfDay_Positions.txt";
			ClassLoader classLoader = (new Utility()).getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			List<Position> sodList = new ArrayList<>();
			FileReader reader = new FileReader(file);
			CSVReader csvReader =  		new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll(); 
			for (String[] row : allData) { 
				int index=0;
				Position sodPos =new Position();
				for (String data : row) { 
					System.out.print(data + "\t"); 
					if (index == 0)
						sodPos.setInstrument(data);
					else if (index == 1)
						sodPos.setAccountNum(Integer.parseInt(data));
					else if (index == 2)
						sodPos.setAcType(AccountType.valueOf(data));
					else if (index == 3)
						sodPos.setQuantity(Integer.parseInt(data));
					else
						System.err.println("invalid data::" + data);
					index++;
				} 
				System.out.println(sodPos	); 
				sodList.add(sodPos);
			} 
			return sodList;

		} catch (Exception e) {
			System.err.println("Unable to read SOD input file "+e.getMessage());
			e.printStackTrace();
		}
		return null;

	}



	public static List<Transaction> readTransactions(String fileName) {
		JSONParser parser = new JSONParser();

		try {
			if(fileName==null)
				fileName = "1537277231233_Input_Transactions.txt";
			ClassLoader classLoader = (new Utility()).getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			Object obj = parser.parse(new FileReader(file));
			List<Transaction> txns = new ArrayList<Transaction>();
			JSONArray txnList = (JSONArray) obj;

			System.out.println(txnList);

			Iterator<JSONObject> iterator = txnList.iterator();
			ObjectMapper objectMapper = new ObjectMapper();

			while (iterator.hasNext()) {
				JSONObject jobj = iterator.next();
				Transaction txn = objectMapper.convertValue(jobj, Transaction.class);
				txns.add(txn);
				System.out.println(txn);
			}
			return txns;

		} catch (Exception e) {
			System.err.println("Unable to read TXN input file "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}


	public static void writeEODFile(List<OutputPosition> eodPositions) {

		if(eodPositions==null) {
			System.err.println("Nothing to write for EOD");
			return;
		}
		FileWriter fileWriter = null;

		try {

			fileWriter = new FileWriter("EndOfDay_Positions.txt");
			fileWriter.append("Instrument,Account,AccountType,Quantity,Delta\n");
			for (OutputPosition pos : eodPositions) {
				fileWriter.append(pos.getInstrument()+",");
				fileWriter.append(pos.getPosition().getAccountNum()+",");
				fileWriter.append(pos.getAcType()+",");
				fileWriter.append(pos.getQuantity()+",");
				fileWriter.append(pos.getDelta()+" \n ");
			}
			System.out.println("EOD file was created successfully !!!");
		} catch (Exception e) {
			System.err.println("Error in writing file !!! "+e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.err.println("Error while flushing/closing fileWriter !!!"+e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
