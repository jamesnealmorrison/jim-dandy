package com.jimmie.domain.items;

public class Chest extends Gear {
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
		return 25;
	}

	@Override
	public GearType getGearType() {
		return GearType.CHEST;
	}

	@Override
	public String getName() {
		return "Chest";
	}
}
