package com.jimmie.domain;

public class FloatingPosition {
	@Override
	public boolean equals(Object arg0) {
		if (FloatingPosition.class.isInstance(arg0)) {
			if ((((FloatingPosition) arg0).getX() == this.getX()) &&
			(((FloatingPosition) arg0).getY() == this.getY())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public FloatingPosition(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	private Double x;
	private Double y;
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
}
