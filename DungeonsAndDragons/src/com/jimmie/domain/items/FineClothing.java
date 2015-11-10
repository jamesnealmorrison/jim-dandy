package com.jimmie.domain.items;

public class FineClothing extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(30, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 6;
	}

	@Override
	public GearType getGearType() {
		return GearType.FINE_CLOTHING;
	}
}
