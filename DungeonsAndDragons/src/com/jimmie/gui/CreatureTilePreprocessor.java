package com.jimmie.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jimmie.util.Utils;

public class CreatureTilePreprocessor {
	private BufferedImage tileImage;

	
	public static void main(String[] args) {
		CreatureTilePreprocessor preprocessor = new CreatureTilePreprocessor();
		preprocessor.run();
	}


	private void run() {
		File dir = new File("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (child.getName().contains("jpg")) {
					Utils.print("Processing " + child.getName());
					try {
						tileImage = ImageIO.read(child);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Image newImage = transformWhite(tileImage);
					try {
						ImageIO.write((BufferedImage) newImage, "jpg", child);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else {
					Utils.print("Skipping " + child.getName());
				}
			}
		} else {
		}		
	}

	private Image transformWhite(BufferedImage image)
	{
		for (int row = 0; row < image.getWidth(null); row++) {
			for (int col = 0; col < image.getHeight(null); col++) {
				
				if (pixelInsideCircle(row, col, image.getWidth())) {

					int rgb = image.getRGB(row, col);
					int red = (rgb >> 16) & 0xFF;
					int green = (rgb >> 8) & 0xFF;
					int blue = rgb & 0xFF;
					// Take pixels that are exactly white and change them to off white.
					if ((red > 254) && (green > 254) && (blue > 254)) {
						Utils.print("Changing a white pixel.");
						image.setRGB(row, col, -2);
					}
				} else {
					// pixel is outside of circle.  Set it to white.
					image.setRGB(row, col, -1);
				}
			}
		}
		return image;
		
		
		
/*		ImageFilter filter = new RGBImageFilter()
		{
			public final int filterRGB(int x, int y, int rgb)
			{
				int red = (rgb >> 16) & 0xFF;
				int green = (rgb >> 8) & 0xFF;
				int blue = rgb & 0xFF;
				//	    	  if ((red > 200) && (green > 200) && (blue > 200)) { 
				if ((red > 254) && (green > 254) && (blue > 254)) {
					return 0;
				} else {
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
*/		
	}


	private boolean pixelInsideCircle(int x, int y, int width) {
		// For now, start with a width 4 pixels outside the circle.
		int r = (width/2) + 1;
		int h = width/2;
		int b = -2*h;
		int c = (x*x)-(2*h*x)+(2*h*h)-(r*r);
		
		// Use the quadratic formula to figure out the y edge values
		int yEdgeUp = (-b+(int) Math.sqrt((b*b)-(4*c)))/2;
		int yEdgeDown = (-b-(int) Math.sqrt((b*b)-(4*c)))/2;
		if ((y>=yEdgeUp) || (y<=yEdgeDown)) {
			return false;
		} else {
			return true;
		}
		
	}	
	
}
