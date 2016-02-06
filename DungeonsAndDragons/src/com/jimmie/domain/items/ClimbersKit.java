package com.jimmie.domain.items;

public class ClimbersKit extends Gear {
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
		return 11;
	}

	@Override
	public GearType getGearType() {
		return GearType.CLIMBERS_KIT;
	}

	@Override
	public String getName() {
		return "ClimbersKit";
	}
}
