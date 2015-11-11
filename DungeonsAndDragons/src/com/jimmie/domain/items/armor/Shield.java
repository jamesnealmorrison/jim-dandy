package com.jimmie.domain.items.armor;

import java.io.Serializable;

import com.jimmie.domain.items.Price;

public abstract class Shield implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract int getBonus();

	public abstract int getSkillPenalty();

	public abstract int getWeight();

	public abstract Price getPrice();

	public abstract ShieldType getShieldType();
}
