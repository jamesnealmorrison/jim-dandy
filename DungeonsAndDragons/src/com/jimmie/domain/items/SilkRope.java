package com.jimmie.domain.items;

public class SilkRope extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(10, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 5;
	}

	@Override
	public GearType getGearType() {
		return GearType.SILK_ROPE;
	}
}
