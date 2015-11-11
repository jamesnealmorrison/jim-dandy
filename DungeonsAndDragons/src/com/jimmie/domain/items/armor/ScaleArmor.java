package com.jimmie.domain.items.armor;

import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;


public class ScaleArmor extends Armor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getBonus() {
		return 7;
	}

	@Override
	public boolean isLightArmor() {
		return false;
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
		return -1;
	}

	@Override
	public int getWeight() {
		return 45;
	}

	@Override
	public ArmorType getArmorType() {
		return ArmorType.SCALE;
	}

	@Override
	public ArmorGroup getArmorGroup() {
		return ArmorGroup.SCALE;
	}

	@Override
	public Price getPrice() {
		return new Price(45, CoinType.GOLD_PIECE);
	}
}
