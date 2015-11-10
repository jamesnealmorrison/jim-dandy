package com.jimmie.domain.items;

public class Backpack extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(2, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 2;
	}

	@Override
	public GearType getGearType() {
		return GearType.BACKPACK;
	}
}
