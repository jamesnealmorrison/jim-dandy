package com.jimmie.domain.items;

public class ChainMail extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getBonus() {
		return 6;
	}

	@Override
	public boolean isLightArmor() {
		return false;
	}

	@Override
	public int getSkillPenalty() {
		return -1;
	}
}
