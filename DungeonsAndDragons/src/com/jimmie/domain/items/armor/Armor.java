package com.jimmie.domain.items.armor;

import java.io.Serializable;
import java.util.List;
import com.jimmie.domain.items.Price;
import com.jimmie.powers.Power;

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
	
	public abstract Price getPrice();

	public List<Power> getPowers() {
		return null;
	}
}
