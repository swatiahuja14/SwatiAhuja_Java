package com.abc.ib.gl.enums;

public enum AccountType {
	I("Internal"), E("External");
	
	private String type;
	private AccountType(String type) {
		this.type = type;
	}
}
