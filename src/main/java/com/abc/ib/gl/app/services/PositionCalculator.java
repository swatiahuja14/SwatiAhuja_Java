package com.abc.ib.gl.app.services;
import java.util.List;

import com.abc.ib.gl.model.Position;
import com.abc.ib.gl.model.Transaction;
public interface PositionCalculator {

	
	List<Position> getEODPositions();
	
}
