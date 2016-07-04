package com.jimmie.domain.items;

public class Pitons extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(5, CoinType.SILVER_PIECE);
	}

	@Override
	public int getWeight() {
		return 5;
	}

	@Override
	public GearType getGearType() {
		return GearType.PITONS;
	}

	@Override
	public String getName() {
		return "Pitons";
	}
}
