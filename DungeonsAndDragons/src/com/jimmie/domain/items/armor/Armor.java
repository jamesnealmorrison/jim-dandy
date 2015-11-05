package com.jimmie.domain.items.armor;

import java.io.Serializable;

public abstract class Armor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract int getBonus();

	public abstract boolean isLightArmor();

	public abstract int getSkillPenalty();
	
	public abstract int getMinimumEnhancementBonus();
	
	public abstract int getSpeedPenalty();
	
	public abstract int getWeight();
	
	public abstract ArmorType getArmorType();
	
	public abstract ArmorGroup getArmorGroup();
	
	public abstract int getPrice();
}
