package com.jimmie.domain.items;

public class Wand extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(7, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public GearType getGearType() {
		return GearType.WAND;
	}
}
