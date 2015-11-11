package com.jimmie.domain.items.armor;

import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;


public class NoShield extends Shield {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getBonus() {
		return 0;
	}

	@Override
	public int getSkillPenalty() {
		return 0;
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public Price getPrice() {
		return new Price(0, CoinType.GOLD_PIECE);
	}

	@Override
	public ShieldType getShieldType() {
		return ShieldType.NO_SHIELD;
	}
}
