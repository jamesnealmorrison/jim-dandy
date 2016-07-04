package com.jimmie.domain.items;

public class Spellbook extends Gear {
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
		return GearType.SPELLBOOK;
	}

	@Override
	public String getName() {
		return "Spellbook";
	}
}
