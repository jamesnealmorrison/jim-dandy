package com.jimmie.domain.items;

public class Waterskin extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(1, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 4;
	}

	@Override
	public GearType getGearType() {
		return GearType.WATERSKIN;
	}

	@Override
	public String getName() {
		return "Waterskin";
	}
}
