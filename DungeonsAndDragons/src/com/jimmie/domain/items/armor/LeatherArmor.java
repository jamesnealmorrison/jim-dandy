package com.jimmie.domain.items.armor;

import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;

public class LeatherArmor extends Armor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getBonus() {
		return 2;
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
		return 15;
	}

	@Override
	public ArmorType getArmorType() {
		return ArmorType.LEATHER;
	}

	@Override
	public ArmorGroup getArmorGroup() {
		return ArmorGroup.LEATHER;
	}

	@Override
	public Price getPrice() {
		return new Price(25, CoinType.GOLD_PIECE);
	}
}
