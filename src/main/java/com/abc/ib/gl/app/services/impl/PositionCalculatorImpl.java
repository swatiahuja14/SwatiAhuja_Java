package com.abc.ib.gl.app.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abc.ib.gl.app.services.PositionCalculator;
import com.abc.ib.gl.enums.AccountType;
import com.abc.ib.gl.enums.TransactionType;
import com.abc.ib.gl.model.OutputPosition;
import com.abc.ib.gl.model.Position;
import com.abc.ib.gl.model.Transaction;

public class PositionCalculatorImpl implements PositionCalculator {

	List<Position> sodPositions = null;
	List<OutputPosition> eodPositions = null;
	List<Transaction> todaysTransactions = null;
	Map<String,Map<AccountType,Integer>> map = null;

	PositionCalculatorImpl(List<Position> sodPos, List<Transaction> currTxns){
		this.sodPositions = sodPos;
		this.todaysTransactions = currTxns;
		map = new HashMap<String,Map<AccountType,Integer>>();
		eodPositions = new ArrayList<>();
		for(Position pos:sodPositions) {
			Map actPosMap = map.get(pos.getInstrument());
			if(actPosMap==null) {
				actPosMap = new HashMap<AccountType, Double>();
			}
			actPosMap.put(pos.getAcType(), pos.getQuantity());
			map.put(pos.getInstrument(), actPosMap);
			eodPositions.add(new OutputPosition(pos, 0));
		}
	}
	@Override
	public List<Position> getEODPositions() {
		if(sodPositions== null && todaysTransactions==null) {
			return null;
		}
		if(sodPositions!= null && todaysTransactions==null) {
			return sodPositions;
		}
		for(Transaction txn: todaysTransactions) {
			Map actPosMap = map.get(txn.getInstrument());
			if(actPosMap==null) {
				System.out.println("account does not exist");
			}
			/*Position ex = (Position) actPosMap.get(AccountType.E);
			Position in = (Position) actPosMap.get(AccountType.I);
			if(ex == null || in ==null) {
				System.out.println("Initial Positions do not exist");
			}
			switch(txn.getTransactionType()) {
			case B:
				ex.setQuantity(ex.getQuantity()+txn.getQuantity());
				in.setQuantity(in.getQuantity()-txn.getQuantity());
				break;
			case S:
				ex.setQuantity(ex.getQuantity()-txn.getQuantity());
				in.setQuantity(in.getQuantity()+txn.getQuantity());
				break;
			default:
				break;
			}*/
			Integer ex = (Integer) actPosMap.get(AccountType.E);
			Integer in = (Integer) actPosMap.get(AccountType.I);
			if(ex == null || in ==null) {
				System.out.println("Initial Positions do not exist");
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
		System.out.println(map);
		double largest = 0;
		double lowest = 0;
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
			}
			if(delta<=lowest && delta>=0) {
				lowest = delta;
				lowestInst = pos.getInstrument();
			}
			

		}
		System.out.println("largest "+largestInst + " lowest "+lowestInst);
		return null;
	}

	public static void main(String[] args) {
		List<Position> sodPositions = InitialPositionLoader.readPositions("");
		List<Transaction> txns = TxnServiceImpl.readTransactions("");
		PositionCalculator pc = new PositionCalculatorImpl(sodPositions,txns);
		pc.getEODPositions();
	}
}
