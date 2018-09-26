package com.abc.ib.gl.app.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.abc.ib.gl.app.services.TxnService;
import com.abc.ib.gl.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TxnServiceImpl implements TxnService {

	@SuppressWarnings("unchecked")
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
 
        try {
 
        	ClassLoader classLoader = (new TxnServiceImpl()).getClass().getClassLoader();
    		String fileName="1537277231233_Input_Transactions.txt";
    		System.out.println(classLoader.getResource(fileName));
    		System.out.println(classLoader.getResource(fileName).getFile());
    		File file = new File(classLoader.getResource(fileName).getFile());
            Object obj = parser.parse(new FileReader(file));
 
            JSONArray txnList = (JSONArray) obj;
         
            System.out.println(txnList);
          
            Iterator<JSONObject> iterator = txnList.iterator();
            ObjectMapper objectMapper = new ObjectMapper();

            while (iterator.hasNext()) {
            	JSONObject jobj = iterator.next();
            	Transaction txn = objectMapper.convertValue(jobj, Transaction.class);
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static List<Transaction> readTransactions(String filename) {
        JSONParser parser = new JSONParser();
 
        try {
 
        	ClassLoader classLoader = (new TxnServiceImpl()).getClass().getClassLoader();
    		String fileName="1537277231233_Input_Transactions.txt";
    		System.out.println(classLoader.getResource(fileName));
    		System.out.println(classLoader.getResource(fileName).getFile());
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
            e.printStackTrace();
        }
        return null;
    }
	
}
