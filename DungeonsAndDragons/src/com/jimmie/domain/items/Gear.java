package com.jimmie.domain.items;

import java.io.Serializable;

public abstract class Gear implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract Price getPrice();
	
	public abstract int getWeight();
	
	public abstract GearType getGearType();
}
