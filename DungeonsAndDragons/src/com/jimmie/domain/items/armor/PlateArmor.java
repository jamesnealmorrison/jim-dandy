package com.jimmie.domain.items.armor;

import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;

public class PlateArmor extends Armor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getBonus() {
		return 8;
	}

	@Override
	public boolean isLightArmor() {
		return false;
	}

	@Override
	public int getSkillPenalty() {
		return -2;
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
		return 50;
	}

	@Override
	public ArmorType getArmorType() {
		return ArmorType.PLATE;
	}

	@Override
	public ArmorGroup getArmorGroup() {
		return ArmorGroup.PLATE;
	}

	@Override
	public Price getPrice() {
		return new Price(50, CoinType.GOLD_PIECE);
	}
}
