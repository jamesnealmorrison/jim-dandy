package com.jimmie.domain.items;

import com.jimmie.domain.ImplementType;

public class Orb extends Implement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(15, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 2;
	}

	@Override
	public GearType getGearType() {
		return GearType.ORB;
	}

	@Override
	public int getAttackBonus() {
		// According to the book, a non-magical implement gives you no bonus. 
		return 0;
	}

	@Override
	public int getDamageBonus() {
		return 0;
	}

	@Override
	public ImplementType getImplementType() {
		return ImplementType.ORB;
	}
}
