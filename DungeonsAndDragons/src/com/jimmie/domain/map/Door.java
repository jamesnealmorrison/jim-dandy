package com.jimmie.domain.map;

import com.jimmie.domain.Position;

public class Door {
	private boolean open;
	private int width; // in squares
	private Position position; // For doors wider than 1 square, location will be the southern-most point (for vertical doors) or western-most point (for horizontal doors).
	                          // Also, a door is assumed to be between squares. For a vertical door, the door is assumed to be on the left side or bottom of its position (i.e. a door
	                          // at position (5,5) is standing between row 4 and 5 (for vertical door) or between column 4 and 5 for horizontal doors.
	private Orientation orientation;
	
	public Door(boolean open, int width, Position position, Orientation orientation) {
		this.open = open;
		this.width = width;
		this.position = position;
		this.orientation = orientation;
	}

	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position location) {
		this.position = location;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

}
