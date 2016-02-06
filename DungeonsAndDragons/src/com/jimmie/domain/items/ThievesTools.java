package com.jimmie.domain.items;

public class ThievesTools extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(20, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 1;
	}

	@Override
	public GearType getGearType() {
		return GearType.THIEVES_TOOLS;
	}

	@Override
	public String getName() {
		return "Thieves Tools";
	}
}
