package com.abc.ib.gl.app.services;
import java.util.List;

import com.abc.ib.gl.model.OutputPosition;
public interface PositionCalculator {
	OutputPosition getLargestTradedInstrument();
	OutputPosition getLowestTradedInstrument();
	List<OutputPosition> getEODPositions();
	
}
