package com.jimmie.domain;

import java.io.Serializable;

public class Position implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean equals(Object arg0) {
		if (Position.class.isInstance(arg0)) {
			if ((((Position) arg0).getX() == this.getX()) &&
			(((Position) arg0).getY() == this.getY())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Double x, Double y) {
		this.x = x.intValue();
		this.y = y.intValue();
	}

	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isWithinReachOf(Position p2, int reach) {
		if ((Math.abs(p2.getX()-getX()) <= reach) && (Math.abs(p2.getY()-getY()) <= reach)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNorthOf(Position target) {
		/* TODO: Make this method work for creatures that are bigger than one square. */
		if ((this.getX() == target.getX()) && (this.getY() > target.getY())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEastOf(Position target) {
		/* TODO: Make this method work for creatures that are bigger than one square. */
		if ((this.getX() > target.getX()) && (this.getY() == target.getY())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSouthOf(Position target) {
		/* TODO: Make this method work for creatures that are bigger than one square. */
		if ((this.getX() == target.getX()) && (this.getY() < target.getY())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isWestOf(Position target) {
		/* TODO: Make this method work for creatures that are bigger than one square. */
		if ((this.getX() < target.getX()) && (this.getY() == target.getY())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNorthEastOf(Position target) {
		/* TODO: Make this method work for creatures that are bigger than one square. */
		if ((this.getX() > target.getX()) && (this.getY() > target.getY())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNorthWestOf(Position target) {
		/* TODO: Make this method work for creatures that are bigger than one square. */
		if ((this.getX() < target.getX()) && (this.getY() > target.getY())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSouthEastOf(Position target) {
		/* TODO: Make this method work for creatures that are bigger than one square. */
		if ((this.getX() > target.getX()) && (this.getY() < target.getY())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSouthWestOf(Position target) {
		/* TODO: Make this method work for creatures that are bigger than one square. */
		if ((this.getX() < target.getX()) && (this.getY() < target.getY())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isWithinBlast(int lowerLeftX, int lowerLeftY, int size) {
		if (((this.getX() >= lowerLeftX) && (this.getX() < lowerLeftX + size)) &&
			((this.getY() >= lowerLeftY) && (this.getY() < lowerLeftY + size))) {
			return true;
		} else {
			return false;
		}
	}
	public int getDistanceTo(Position toPosition) {
		return (int)(Math.sqrt(((getX() - toPosition.getX())*(getX() - toPosition.getX())) + ((getY() - toPosition.getY())*(getY() - toPosition.getY()))));
	}

}
