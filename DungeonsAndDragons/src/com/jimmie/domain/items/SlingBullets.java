package com.jimmie.domain.items;

public class SlingBullets extends Gear {
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
		return 5;
	}

	@Override
	public GearType getGearType() {
		return GearType.SLING_BULLETS;
	}

	@Override
	public String getName() {
		return "Sling Bullets";
	}
}
