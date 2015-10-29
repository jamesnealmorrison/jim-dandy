package com.jimmie.gui;

import java.awt.Dimension;

import com.jimmie.domain.Position;

public class ScreenPosition {
	private Position topLeftCorner;
	public Position getTopLeftCorner() {
		return topLeftCorner;
	}

	public void setTopLeftCorner(Position topLeftCorner) {
		this.topLeftCorner = topLeftCorner;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	private Dimension dimension;
	
	public ScreenPosition(Position mapPosition, Dimension mapDimension, int squareSize) {
		/* This method will convert the mapPosition (where 0,0 is on the bottom left) to screen dimensions (where 0,0 is on the top left). */
		topLeftCorner = new Position(0,0);
		topLeftCorner.setX((mapPosition.getX()-1) * squareSize);
		topLeftCorner.setY(((mapDimension.height-(mapPosition.getY()-1))*squareSize)-squareSize);
		dimension = new Dimension();
		dimension.setSize(squareSize,squareSize);
	}
}
