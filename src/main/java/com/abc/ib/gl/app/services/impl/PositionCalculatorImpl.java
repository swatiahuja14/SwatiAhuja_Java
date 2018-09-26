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
	boolean processed = false;
	List<Position> sodPositions = null;
	List<OutputPosition> eodPositions = null;
	List<Transaction> todaysTransactions = null;
	OutputPosition largestTraded = null;
	OutputPosition lowestTraded = null;
	Map<String,Map<AccountType,Integer>> map = null;

	public PositionCalculatorImpl(List<Position> sodPos, List<Transaction> currTxns){
		if(sodPos==null) {
			System.err.println("Unable to proceed. Cant find SOD positions");
			return;
		}
		this.sodPositions = sodPos;
		this.todaysTransactions = currTxns;
		map = new HashMap<String,Map<AccountType,Integer>>();
		eodPositions = new ArrayList<>();

		for(Position pos:sodPositions) {
			Map actPosMap = map.get(pos.getInstrument());
			if(actPosMap==null) {
				actPosMap = new HashMap<AccountType, Integer>();
			}
			actPosMap.put(pos.getAcType(), pos.getQuantity());
			map.put(pos.getInstrument(), actPosMap);
			eodPositions.add(new OutputPosition(pos, 0));
		}
		System.out.println(map);
		processTransactions();
	}
	@Override
	public List<OutputPosition> getEODPositions() {
		if(processed)
			return eodPositions;
		else return null;
	}
	private void processTransactions() {
		if(sodPositions== null && todaysTransactions==null) {
			return;
		}
		if(todaysTransactions!=null) {

			for(Transaction txn: todaysTransactions) {
				Map actPosMap = map.get(txn.getInstrument());
				if(actPosMap==null) {
					System.err.println("account does not exist in SOD "+txn.getInstrument());
					continue;
				}
				Integer ex = (Integer) actPosMap.get(AccountType.E);
				Integer in = (Integer) actPosMap.get(AccountType.I);
				if(ex == null || in ==null) {
					System.err.println("Initial Positions do not exist");
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
				System.out.println(txn.getInstrument() + " "+actPosMap);
			}		
		}
		System.out.println(map);
		int largest = 0;
		int lowest = 0;
		String largestInst = null;
		String lowestInst = null;
		for(OutputPosition pos:eodPositions) {
			Integer oldqty = pos.getQuantity();
			Integer newPQty = map.get(pos.getInstrument()).get(pos.getAcType());
			Integer delta = newPQty - oldqty;
			pos.setQuantity(newPQty);
			pos.setDelta(delta);

			System.out.println(pos);
			if(delta>largest) {
				largest = delta;
				largestInst = pos.getInstrument();
				largestTraded = pos;
			}
			if(delta<=lowest && delta>=0) {
				lowest = delta;
				lowestInst = pos.getInstrument();
				lowestTraded = pos;
			}


		}
		System.out.println("largest "+largestInst + " lowest "+lowestInst);
		processed = true;
	}

	@Override
	public OutputPosition getLargestTradedInstrument() {
		return largestTraded;
	}
	@Override
	public OutputPosition getLowestTradedInstrument() {
		return lowestTraded;
	}
}
