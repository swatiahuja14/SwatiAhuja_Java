package com.abc.ib.gl.enums;

public enum TransactionType {
	B("BUY"), S("SELL");
	
	@SuppressWarnings("unused")
	private String type;
	private TransactionType(String type) {
		this.type = type;
	}
}
