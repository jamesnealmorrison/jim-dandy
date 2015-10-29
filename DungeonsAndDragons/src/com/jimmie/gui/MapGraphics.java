package com.jimmie.gui;

import java.awt.Graphics2D;

public class MapGraphics {
	Graphics2D graphics;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MapGraphics mapGraphics = new MapGraphics();
		mapGraphics.run();

	}

	private void run() {
		graphics = new DndGraphics();
		
		graphics.draw3DRect(1,100,0,200,true);
	}
}
