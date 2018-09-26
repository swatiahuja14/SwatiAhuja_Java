package com.abc.ib.gl.enums;

public enum TransactionType {
	B("BUY"), S("SELL");
	
	private String type;
	private TransactionType(String type) {
		this.type = type;
	}
}
