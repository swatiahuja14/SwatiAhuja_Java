package com.abc.ib.gl.app.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.abc.ib.gl.model.OutputPosition;
import com.abc.ib.gl.model.Position;
import com.abc.ib.gl.model.Transaction;

//@Category(com.abc.ib.gl.app.util.Utility.class)
public class UtilityTest {

	@Test
	public void testReadSODPositionsFileNotFound() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadSODPositionsFileNotFound()");
		List<Position> list = Utility.readSODPositions("xyz.txt");
		assertNull(list);
	}
	
	@Test
	public void testReadSODPositionsBlankFile() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadSODPositionsBlankFile()");
		List<Position> list = Utility.readSODPositions("BlankInput_StartOfDay_Positions.txt");
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testReadSODPositionsWrongInput() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadSODPositionsWrongInput()");
		List<Position> list = Utility.readSODPositions("WrongInput_StartOfDay_Positions.txt");
		assertNotNull(list);
		assertEquals(10, list.size());
	}
	
	
	@Test
	public void testReadSODPositionsDefaultFile() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadSODPositionsDefaultFile()");
		List<Position> list = Utility.readSODPositions(null);
		assertEquals(10, list.size());
	}
	
	@Test
	public void testReadTransactionsFileNotFound() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadTransactionsFileNotFound()");
		List<Transaction> list = Utility.readTransactions("abc.txt");
		assertNull(list);
	}
	
	@Test
	public void testReadTransactionsBlankFile() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadTransactionsBlankFile()");
		List<Transaction> list = Utility.readTransactions("Blank_Input_Transactions.txt");
		assertNull(list);
	}
	

	@Test
	public void testReadTransactionsInstrNotInSOD() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadTransactionsInstrNotInSOD()");
		List<Transaction> list = Utility.readTransactions("InstrNotInSOD_Input_Transactions.txt");
		assertNotNull(list);
		assertEquals(12, list.size());
	}


	@Test
	public void testReadTransactionsInvalidJSON() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadTransactionsInvalidJSON()");
		List<Transaction> list = Utility.readTransactions("InvalidJSON_Input_Transactions.txt");
		assertNull(list);
	}
	
	@Test
	public void testReadTransactionsInvalidJSON1() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadTransactionsInvalidJSON1()");
		List<Transaction> list = Utility.readTransactions("WrongJSON_Input_Transactions.txt");
		assertNull(list);
	}
	
	@Test
	public void testReadTransactionsInvalidJSON2() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadTransactionsInvalidJSON2()");
		List<Transaction> list = Utility.readTransactions("WrongJSON_Input_Transactions2.txt");
		assertNull(list);
	}
	@Test
	public void testReadTXNDefaultFile() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testReadTXNDefaultFile()");
		List<Transaction> list = Utility.readTransactions(null);
		assertEquals(12, list.size());
	}
	
	@Test
	public void testWriteEODFile() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("UtilityTest.testWriteEODFile()");
		Utility.writeEODFile(null);
		List<OutputPosition> out = new ArrayList<OutputPosition>();
		Utility.writeEODFile(out);
		out.add(new OutputPosition(new Position(), 5000));
		Utility.writeEODFile(out);
	}

}
