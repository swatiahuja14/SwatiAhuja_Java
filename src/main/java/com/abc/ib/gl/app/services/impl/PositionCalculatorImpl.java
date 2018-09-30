package com.abc.ib.gl.app.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abc.ib.gl.app.services.PositionCalculator;
import com.abc.ib.gl.enums.AccountType;
import com.abc.ib.gl.model.OutputPosition;
import com.abc.ib.gl.model.Position;
import com.abc.ib.gl.model.Transaction;

public class PositionCalculatorImpl implements PositionCalculator {
	List<Position> sodPositions = null;
	List<OutputPosition> eodPositions = null;
	List<Transaction> todaysTransactions = null;
	String largestTraded = null;
	String lowestTraded = null;
	Map<String,Map<AccountType,Integer>> map = null;

	public PositionCalculatorImpl(List<Position> sodPos, List<Transaction> currTxns){
		System.out.println("PositionCalculatorImpl() initializing...");
		if(sodPos==null) {
			System.err.println("Unable to proceed. Cant find SOD positions");
			return;
		}
		this.sodPositions = sodPos;
		this.todaysTransactions = currTxns;
		map = new HashMap<String,Map<AccountType,Integer>>();
		eodPositions = new ArrayList<>();

		for(Position pos:sodPositions) {
			Map<AccountType, Integer> actPosMap = map.get(pos.getInstrument());
			if(actPosMap==null) {
				actPosMap = new HashMap<AccountType, Integer>();
			}
			actPosMap.put(pos.getAcType(), pos.getQuantity());
			map.put(pos.getInstrument(), actPosMap);
			eodPositions.add(new OutputPosition(pos, 0));
		}
		processTransactions();
	}
	@Override
	public List<OutputPosition> getEODPositions() {
		return eodPositions;
	}
	private void processTransactions() {
		System.out.println("PositionCalculatorImpl.processTransactions()");
		if(sodPositions== null && todaysTransactions==null) {
			System.out.println("PositionCalculatorImpl SOD Positions and todays Transactions are null");
			return;
		}
		if(todaysTransactions!=null) {
			for(Transaction txn: todaysTransactions) {
				Map<AccountType, Integer> actPosMap = map.get(txn.getInstrument());
				if(actPosMap==null) {
					System.err.println("Instrument does not exist in SOD Positions "+txn.getInstrument());
					continue;
				}
				Integer ex = actPosMap.get(AccountType.E);
				Integer in = actPosMap.get(AccountType.I);
				if(ex == null || in ==null) {
					System.err.println("Account does not exist in SOD Initial Positions");
				}
				switch(txn.getTransactionType()) {
				case B:
					ex= ex+txn.getQuantity();
					in= in-txn.getQuantity();
					break;
				case S:
					ex= ex-txn.getQuantity();
					in= in+txn.getQuantity();
					break;
				default:
					break;
				}
				actPosMap.put(AccountType.E,ex);
				actPosMap.put(AccountType.I,in);
			}		
		}
		int largest = 0;
		int lowest = 0;
		for(OutputPosition pos:eodPositions) {
			Integer oldqty = pos.getQuantity();
			Integer newPQty = map.get(pos.getInstrument()).get(pos.getAcType());
			Integer delta = newPQty - oldqty;
			pos.setQuantity(newPQty);
			pos.setDelta(delta);
			System.out.println(pos);
			if(delta>largest) {
				largest = delta;
				largestTraded = pos.getInstrument();
			}
			if(delta<0) {
				delta = delta*(-1);
			}
			if(lowest ==0 && delta!=0) {
				lowest = delta;
				lowestTraded = pos.getInstrument();
			}
			if(delta< lowest) {
				lowest = delta;
				lowestTraded = pos.getInstrument();
			}
		}
		System.out.println("PositionCalculatorImpl.processTransactions() Complete");
	}

	@Override
	public String getLargestTradedInstrument() {
		return largestTraded;
	}
	@Override
	public String getLowestTradedInstrument() {
		return lowestTraded;
	}
}
