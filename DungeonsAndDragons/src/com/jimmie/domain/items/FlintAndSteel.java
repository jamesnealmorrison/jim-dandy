package com.jimmie.domain.items;

public class FlintAndSteel extends Gear {
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
		return 0;
	}

	@Override
	public GearType getGearType() {
		return GearType.FLINT_AND_STEEL;
	}


	@Override
	public String getName() {
		return "Flint And Steel";
	}
}
