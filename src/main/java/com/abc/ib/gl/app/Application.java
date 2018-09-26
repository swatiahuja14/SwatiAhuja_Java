package com.abc.ib.gl.app;

import java.util.List;

import com.abc.ib.gl.app.services.PositionCalculator;
import com.abc.ib.gl.app.services.impl.PositionCalculatorImpl;
import com.abc.ib.gl.app.util.Utility;
import com.abc.ib.gl.model.OutputPosition;
import com.abc.ib.gl.model.Position;
import com.abc.ib.gl.model.Transaction;

public class Application {

    public static void main(String[] args) {
    	String inputSODFile = null;
    	String inputTXNFile = null;
    	if(args.length==1) {
    		inputSODFile = args[0];
    	}
    	if(args.length>=2) {
    		inputSODFile = args[0];
    		inputTXNFile = args[1];
    	}
		List<Position> sodPositions = Utility.readSODPositions(inputSODFile);
		List<Transaction> txns = Utility.readTransactions(inputTXNFile);
		PositionCalculator pc = new PositionCalculatorImpl(sodPositions,txns);
		List<OutputPosition> eod = pc.getEODPositions();
		Utility.writeEODFile(eod);
		//System.out.println(pc.getLargestTradedInstrument());
		//System.out.println(pc.getLowestTradedInstrument());
	}

}