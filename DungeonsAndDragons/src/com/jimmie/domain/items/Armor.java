package com.jimmie.domain.items;

import java.io.Serializable;

public abstract class Armor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract int getBonus();

	public abstract boolean isLightArmor();

	public abstract int getSkillPenalty();
}
