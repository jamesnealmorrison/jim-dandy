package com.jimmie.domain.items.armor;


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

	@Override
	public int getMinimumEnhancementBonus() {
		return 0;
	}

	@Override
	public int getSpeedPenalty() {
		return 0;
	}

	@Override
	public int getWeight() {
		return 25;
	}

	@Override
	public ArmorType getArmorType() {
		return ArmorType.HIDE;
	}

	@Override
	public ArmorGroup getArmorGroup() {
		return ArmorGroup.HIDE;
	}

	@Override
	public int getPrice() {
		return 30;
	}
}
