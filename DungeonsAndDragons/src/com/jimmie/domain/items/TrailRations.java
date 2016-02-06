package com.jimmie.domain.items;

public class TrailRations extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(5, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 10;
	}

	@Override
	public GearType getGearType() {
		return GearType.TRAIL_RATIONS;
	}

	@Override
	public String getName() {
		return "Trail Rations";
	}
}
