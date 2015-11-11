package com.jimmie.domain.items.armor;

import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;


public class LightShield extends Shield {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getBonus() {
		return 1;
	}

	@Override
	public int getSkillPenalty() {
		return 0;
	}

	@Override
	public int getWeight() {
		return 6;
	}

	@Override
	public Price getPrice() {
		return new Price(5, CoinType.GOLD_PIECE);
	}

	@Override
	public ShieldType getShieldType() {
		return ShieldType.LIGHT_SHIELD;
	}
}
