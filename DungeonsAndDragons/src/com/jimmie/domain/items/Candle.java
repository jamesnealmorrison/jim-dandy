package com.jimmie.domain.items;

import com.jimmie.domain.IlluminationType;
import com.jimmie.domain.LightSource;
import com.jimmie.domain.Position;

public class Candle extends Gear implements LightSource {
	private Position position;
	
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

	@Override
	public int getRadius() {
		return 2;
	}

	@Override
	public IlluminationType getIlluminationType() {
		return IlluminationType.DIM_LIGHT;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
