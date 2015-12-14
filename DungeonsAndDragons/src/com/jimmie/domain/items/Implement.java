package com.jimmie.domain.items;

import com.jimmie.domain.ImplementType;

public abstract class Implement extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract int getAttackBonus();

	public abstract int getDamageBonus();

	public abstract ImplementType getImplementType();
}
