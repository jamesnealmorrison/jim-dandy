package com.jimmie.domain.items;

public class Torch extends Gear {
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
		return 1;
	}

	@Override
	public GearType getGearType() {
		return GearType.TORCH;
	}
}
