package com.abc.ib.gl.app.services;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.abc.ib.gl.app.services.impl.PositionCalculatorImpl;
import com.abc.ib.gl.enums.AccountType;
import com.abc.ib.gl.enums.TransactionType;
import com.abc.ib.gl.model.OutputPosition;
import com.abc.ib.gl.model.Position;
import com.abc.ib.gl.model.Transaction;

//@Category(com.abc.ib.gl.app.services.PositionCalculator.class)
public class PositionCalculatorTest {

	List<Position> sodPos;
	List<Transaction> currTxns;
	List<OutputPosition> outputPos;

	@Before
	public void setUpData() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("PositionCalculatorTest.setUpData() Before");
		Position p1 = new Position();
		p1.setAccountNum(101);
		p1.setAcType(AccountType.E);
		p1.setInstrument("IBM");
		p1.setQuantity(100000);

		Position p2 = new Position();
		p2.setAccountNum(201);
		p2.setAcType(AccountType.I);
		p2.setInstrument("IBM");
		p2.setQuantity(-100000);

		Position p3 = new Position();
		p3.setAccountNum(101);
		p3.setAcType(AccountType.E);
		p3.setInstrument("MSFT");
		p3.setQuantity(-5000000);

		Position p4 = new Position();
		p4.setAccountNum(201);
		p4.setAcType(AccountType.I);
		p4.setInstrument("MSFT");
		p4.setQuantity(5000000);

		Transaction t1 = new Transaction();
		t1.setInstrument("IBM");
		t1.setQuantity(50000);
		t1.setTransactionId("1");
		t1.setTransactionType(TransactionType.B);

		Transaction t2 = new Transaction();
		t2.setInstrument("MSFT");
		t2.setQuantity(200000);
		t2.setTransactionId("1");
		t2.setTransactionType(TransactionType.S);

		sodPos = new ArrayList<Position>();
		sodPos.add(p1);
		sodPos.add(p2);
		sodPos.add(p3);
		sodPos.add(p4);
		
		currTxns = new ArrayList<Transaction>();
		currTxns.add(t1);
		currTxns.add(t2);
	}

	@Test
	public void testGetEODPositions() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("PositionCalculatorTest.testGetEODPositions()");
		PositionCalculator pc = new PositionCalculatorImpl(sodPos, currTxns);
		outputPos = pc.getEODPositions();
		for (OutputPosition out:outputPos) {
			if(out.getInstrument()=="IBM") {
				if(out.getAcType()==AccountType.E) {
					assertEquals(150000, out.getQuantity());
					assertEquals(50000, out.getDelta());
				} else {
					assertEquals(-150000, out.getQuantity());
					assertEquals(-50000, out.getDelta());					
				}
			} else {
				if(out.getAcType()==AccountType.E) {
					assertEquals(-5200000, out.getQuantity());
					assertEquals(-200000, out.getDelta());
				} else {
					assertEquals(5200000, out.getQuantity());
					assertEquals(200000, out.getDelta());					
				}
			}
		}
	}

	@Test
	public void testNullGetEODPositions() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("PositionCalculatorTest.testNullGetEODPositions()");
		PositionCalculator pc = new PositionCalculatorImpl(null, null);
		assertNull(pc.getEODPositions());
		assertNull(pc.getLargestTradedInstrument());
		assertNull(pc.getLowestTradedInstrument());
	}
	
	@Test
	public void testBlankListGetEODPositions() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("PositionCalculatorTest.testBlankListGetEODPositions()");
		List<Position> sod = new ArrayList<Position>();
		List<Transaction> txn = new ArrayList<Transaction>();
		PositionCalculator pc = new PositionCalculatorImpl(sod, txn);
		assertNotNull(pc.getEODPositions());
		assertEquals(0, pc.getEODPositions().size());
		assertNull(pc.getLargestTradedInstrument());
		assertNull(pc.getLowestTradedInstrument());
	}
	
	@Test
	public void testSodNullTxnGetEODPositions() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("PositionCalculatorTest.testSodNullTxnGetEODPositions()");
		PositionCalculator pc = new PositionCalculatorImpl(sodPos, null);
		assertNotNull(pc.getEODPositions());
		assertNull(pc.getLargestTradedInstrument());
		assertNull(pc.getLowestTradedInstrument());
	}
	
	@Test
	public void testSodNoTxnGetEODPositions() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("PositionCalculatorTest.testSodNoTxnGetEODPositions()");
		List<Transaction> txn = new ArrayList<Transaction>();
		PositionCalculator pc = new PositionCalculatorImpl(sodPos, txn);
		assertNotNull(pc.getEODPositions());
		assertNull(pc.getLargestTradedInstrument());
		assertNull(pc.getLowestTradedInstrument());
	}
	
	@Test
	public void testNullTxnGetEODPositions() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("PositionCalculatorTest.testNullTxnGetEODPositions()");
		PositionCalculator pc = new PositionCalculatorImpl(null, currTxns);
		assertNull(pc.getEODPositions());
	}
	
	
	@Test
	public void testGetLargestTradedInstrument() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("PositionCalculatorTest.testGetLargestTradedInstrument()");
		PositionCalculator pc = new PositionCalculatorImpl(sodPos, currTxns);
		assertEquals("MSFT",pc.getLargestTradedInstrument());
	}

	@Test
	public void testGetLowestTradedInstrument() {
		System.out.println("[INFO] -------------------------------------------------------");
		System.out.println("PositionCalculatorTest.testGetLowestTradedInstrument()");
		PositionCalculator pc = new PositionCalculatorImpl(sodPos, currTxns);
		assertEquals("IBM",pc.getLowestTradedInstrument());
	}

}
