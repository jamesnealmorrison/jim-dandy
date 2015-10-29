package com.jimmie.domain.items;

public class HideArmor extends Armor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getBonus() {
		return 3;
	}

	@Override
	public boolean isLightArmor() {
		return true;
	}

	@Override
	public int getSkillPenalty() {
		return -1;
	}
}
