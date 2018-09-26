package com.abc.ib.gl.model;

import com.abc.ib.gl.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Transaction {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [txnId=" + txnId + ", instrument=" + instrument + ", txnType=" + txnType + ", txnQuantity="
				+ txnQuantity + "]";
	}
	private String txnId;
	private String instrument;
	private TransactionType txnType;
	private int txnQuantity;
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return txnId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	@JsonSetter("TransactionId")
	public void setTransactionId(String transactionId) {
		this.txnId = transactionId;
	}
	/**
	 * @return the instrument
	 */
	public String getInstrument() {
		return instrument;
	}
	/**
	 * @param instrument the instrument to set
	 */
	@JsonSetter("Instrument")
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	/**
	 * @return the transactionType
	 */
	public TransactionType getTransactionType() {
		return txnType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	@JsonSetter("TransactionType")
	public void setTransactionType(TransactionType transactionType) {
		this.txnType = transactionType;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return txnQuantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	@JsonSetter("TransactionQuantity")
	public void setQuantity(int quantity) {
		this.txnQuantity = quantity;
	}

}
