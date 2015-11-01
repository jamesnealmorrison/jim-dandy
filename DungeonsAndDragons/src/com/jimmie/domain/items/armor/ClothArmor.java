package com.jimmie.domain.items.armor;


public class ClothArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getBonus() {
		return 0;
	}

	@Override
	public boolean isLightArmor() {
		return true;
	}

	@Override
	public int getSkillPenalty() {
		return 0;
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
		return 4;
	}

	@Override
	public ArmorType getArmorType() {
		return ArmorType.CLOTH;
	}

	@Override
	public ArmorGroup getArmorGroup() {
		return ArmorGroup.CLOTH;
	}

	@Override
	public int getPrice() {
		return 1;
	}
}
