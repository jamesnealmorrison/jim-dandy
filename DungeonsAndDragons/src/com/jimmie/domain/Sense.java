package com.jimmie.domain;

import java.io.Serializable;

public class Sense implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public SenseType getType() {
		return type;
	}

	public void setType(SenseType type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private SenseType type;
	private int value;
	
	public Sense(SenseType type, int value) {
		this.type = type;
		this.value = value;
	}

	public Sense(SenseType type) {
		this.type = type;
		this.value = 0;
	}
}
