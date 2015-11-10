package com.jimmie.domain.items;

public class BeltPouch extends Gear {
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
		return 0; // Book says 1/2 pound.  Oh, well.
	}

	@Override
	public GearType getGearType() {
		return GearType.BELT_POUCH;
	}
}
