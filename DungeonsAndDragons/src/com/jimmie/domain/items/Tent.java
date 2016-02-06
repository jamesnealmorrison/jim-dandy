package com.jimmie.domain.items;

public class Tent extends Gear {
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
		return 20;
	}

	@Override
	public GearType getGearType() {
		return GearType.TENT;
	}

	@Override
	public String getName() {
		return "Tent";
	}
}
