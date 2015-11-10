package com.jimmie.domain.items;

public class Bedroll extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(1, CoinType.SILVER_PIECE);
	}

	@Override
	public int getWeight() {
		return 5;
	}

	@Override
	public GearType getGearType() {
		return GearType.BEDROLL;
	}
}
