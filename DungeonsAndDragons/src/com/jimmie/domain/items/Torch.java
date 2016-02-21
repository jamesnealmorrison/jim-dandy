package com.jimmie.domain.items;

import com.jimmie.domain.IlluminationType;
import com.jimmie.domain.LightSource;
import com.jimmie.domain.Position;

public class Torch extends Gear implements LightSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Position position;
	
	@Override
	public Price getPrice() {
		return new Price(1, CoinType.SILVER_PIECE);
	}

	@Override
	public int getWeight() {
		return 1;
	}

	@Override
	public GearType getGearType() {
		return GearType.TORCH;
	}

	@Override
	public String getName() {
		return "Torch";
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public int getRadius() {
		return 5;
	}

	@Override
	public IlluminationType getIlluminationType() {
		return IlluminationType.BRIGHT_LIGHT;
	}

}
