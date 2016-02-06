package com.jimmie.domain.items;

public class RitualBook extends Gear {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(50, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 3;
	}

	@Override
	public GearType getGearType() {
		return GearType.RITUAL_BOOK;
	}

	@Override
	public String getName() {
		return "Ritual Book";
	}
}
