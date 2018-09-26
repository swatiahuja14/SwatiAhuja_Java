package com.abc.ib.gl.model;

import com.abc.ib.gl.enums.AccountType;

public class OutputPosition {

	
	private Position position;
	private int delta;
	
	public OutputPosition(Position pos, int del) {
		this.position = pos;
		this.delta =del;
	}
	public int getQuantity() {
		return position.getQuantity();
	}
	public String getInstrument() {
		return position.getInstrument();
	}
	public AccountType getAcType() {
		return position.getAcType();
	}
	/**
	 * @return the delta
	 */
	public int getDelta() {
		return delta;
	}
	public void setQuantity(int quantity) {
		this.position.setQuantity(quantity);
	}
	/**
	 * @param delta the delta to set
	 */
	public void setDelta(int delta) {
		this.delta = delta;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OutputPosition [position=" + position + ", delta=" + delta + "]";
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
	
}
