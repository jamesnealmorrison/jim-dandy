package com.jimmie.domain.items;

public class Flask extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(3, CoinType.COPPER_PIECE);
	}

	@Override
	public int getWeight() {
		return 1;
	}

	@Override
	public GearType getGearType() {
		return GearType.FLASK;
	}

	@Override
	public String getName() {
		return "Flask";
	}
}
