package com.jimmie.domain.items;

public class StandardAdventurersKit extends Gear {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(15, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 33;
	}

	@Override
	public GearType getGearType() {
		return GearType.STANDARD_ADVENTURERS_KIT;
	}

	@Override
	public String getName() {
		return "Standard Adventurer's Kit";
	}
}
