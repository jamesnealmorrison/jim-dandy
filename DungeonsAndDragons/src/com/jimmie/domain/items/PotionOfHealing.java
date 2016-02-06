package com.jimmie.domain.items;

public class PotionOfHealing extends Potion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(50,CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public GearType getGearType() {
		return GearType.POTION_OF_HEALING;
	}


	@Override
	public String getName() {
		return "Potion Of Healing";
	}
}
