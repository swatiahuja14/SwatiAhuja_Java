package com.abc.ib.gl.app.services;
import java.util.List;

import com.abc.ib.gl.model.OutputPosition;
public interface PositionCalculator {
	String getLargestTradedInstrument();
	String getLowestTradedInstrument();
	List<OutputPosition> getEODPositions();
	
}
