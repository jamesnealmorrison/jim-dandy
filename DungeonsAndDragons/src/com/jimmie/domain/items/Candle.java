package com.jimmie.domain.items;

public class Candle extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(1, CoinType.COPPER_PIECE);
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public GearType getGearType() {
		return GearType.CANDLE;
	}

	@Override
	public String getName() {
		return "Candle";
	}
}
