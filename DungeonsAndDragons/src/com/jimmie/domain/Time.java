package com.jimmie.domain;

import java.io.Serializable;

public class Time implements Serializable {

	public Time(int quantity, TimeType type) {
		super();
		this.quantity = quantity;
		this.type = type;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int quantity;
	private TimeType type;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public TimeType getType() {
		return type;
	}
	public void setType(TimeType type) {
		this.type = type;
	}

}
