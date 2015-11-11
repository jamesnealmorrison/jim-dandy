package com.jimmie.domain.items;

public class EverburningTorch extends Gear {
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
		return 1;
	}

	@Override
	public GearType getGearType() {
		return GearType.EVERBURNING_TORCH;
	}
}
