package com.jimmie.domain;

public class AlternativeMovementMode {
	private AlternativeMovementModeType type;
	private int speed;
	public AlternativeMovementModeType getType() {
		return type;
	}
	public void setType(AlternativeMovementModeType type) {
		this.type = type;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public AlternativeMovementMode(AlternativeMovementModeType type, int speed) {
		this.setType(type);
		this.setSpeed(speed);
	}
}
