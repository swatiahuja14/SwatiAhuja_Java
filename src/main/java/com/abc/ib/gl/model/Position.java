package com.abc.ib.gl.model;

import com.abc.ib.gl.enums.AccountType;

public class Position {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Position [instrument=" + instrument + ", accountNum=" + accountNum + ", acType=" + acType
				+ ", quantity=" + quantity + "]";
	}
	private String instrument;
	private int accountNum;
	private AccountType acType;
	private int quantity;
	/**
	 * @return the instrument
	 */
	public String getInstrument() {
		return instrument;
	}
	/**
	 * @param instrument the instrument to set
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	/**
	 * @return the accountNum
	 */
	public int getAccountNum() {
		return accountNum;
	}
	/**
	 * @param accountNum the accountNum to set
	 */
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	/**
	 * @return the acType
	 */
	public AccountType getAcType() {
		return acType;
	}
	/**
	 * @param acType the acType to set
	 */
	public void setAcType(AccountType acType) {
		this.acType = acType;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
