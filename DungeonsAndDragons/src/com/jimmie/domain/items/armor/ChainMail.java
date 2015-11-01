package com.jimmie.domain.items.armor;


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

	@Override
	public int getMinimumEnhancementBonus() {
		return 0;
	}

	@Override
	public int getSpeedPenalty() {
		return -1;
	}

	@Override
	public int getWeight() {
		return 40;
	}

	@Override
	public ArmorType getArmorType() {
		return ArmorType.CHAINMAIL;
	}

	@Override
	public ArmorGroup getArmorGroup() {
		return ArmorGroup.CHAINMAIL;
	}

	@Override
	public int getPrice() {
		return 40;
	}
}