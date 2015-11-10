package com.jimmie.domain.items.armor;

import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;


public class HeavyShield extends Shield {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getBonus() {
		return 2;
	}

	@Override
	public int getSkillPenalty() {
		return -2;
	}

	@Override
	public int getWeight() {
		return 15;
	}

	@Override
	public Price getPrice() {
		return new Price(10, CoinType.GOLD_PIECE);
	}

	@Override
	public ShieldType getShieldType() {
		return ShieldType.HEAVY_SHIELD;
	}
}
