package com.jimmie.domain.items;

import com.jimmie.domain.IlluminationType;
import com.jimmie.domain.LightSource;
import com.jimmie.domain.Position;

public class Sunrods extends Gear implements LightSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Position position;

	@Override
	public Price getPrice() {
		return new Price(4, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 2;
	}

	@Override
	public GearType getGearType() {
		return GearType.SUNRODS;
	}

	@Override
	public String getName() {
		return "Sunrods";
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public int getRadius() {
		return 20;
	}

	@Override
	public IlluminationType getIlluminationType() {
		return IlluminationType.BRIGHT_LIGHT;
	}

}
