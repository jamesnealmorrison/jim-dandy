package com.jimmie.domain.items.armor;

import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;


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
	public Price getPrice() {
		return new Price(1, CoinType.GOLD_PIECE);
	}
}
