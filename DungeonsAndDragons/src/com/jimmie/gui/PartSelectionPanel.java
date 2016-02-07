package com.jimmie.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PartSelectionPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// For Wilderness
//	private static final int TOTAL_HEIGHT = 1620;
	// For Dungeon
	private static final int TOTAL_HEIGHT = 2430;
	private HashMap<Integer, Image> parts = null;
	private Image allPartsImage = null;

	public void init() {
		// For Wilderness
//		Dimension dimension = new Dimension(10125, TOTAL_HEIGHT);
		// For Dungeon
		Dimension dimension = new Dimension(10125, TOTAL_HEIGHT);
		this.setPreferredSize(dimension);
		parts = new HashMap<Integer, Image>();
		allPartsImage = new BufferedImage(10125, TOTAL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		int imageRow = 1;
		int imageCol = 0;
		
		addMouseListener(this);
		
		Graphics g = allPartsImage.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 10125, TOTAL_HEIGHT);
		
		// Read all of the wilderness tiles in.
//		for (int index = 1; index <= 99; index++) {
		// Read all of the dungeon tiles in.
		for (int index = 1; index <= 133; index++) {		
			imageCol++;
			if (imageCol == 26) {
				imageCol = 1;
				imageRow++;
			}
			File tempFile  = new File("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\TheDungeon\\DungeonTile00"+index+".jpg");
			Image tempImage = null;
			try {
				tempImage = ImageIO.read(tempFile);
				
				// Add the image to a hashMap.
				parts.put(index,tempImage);
				
				Image shrunkenImage = BattlefieldCreator.shrinkImage(tempImage);
				int calculatedX = (int) (((imageCol-1)*8)*(BattlefieldCreator.SQUARE_SIZE*BattlefieldCreator.SHRINK_PERCENT))+((imageCol-1)*5);
				int calculatedY = (int) (((imageRow-1)*8)*(BattlefieldCreator.SQUARE_SIZE*BattlefieldCreator.SHRINK_PERCENT))+((imageRow-1)*5);
				g.drawImage(shrunkenImage, calculatedX, calculatedY, null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (allPartsImage != null) {
//			Dimension dimension = new Dimension(BattlefieldCreator.getImage().getWidth(null),BattlefieldCreator.getImage().getHeight(null));
//			this.setPreferredSize(dimension);

			if (Graphics2D.class.isAssignableFrom(g.getClass())) {
//				Graphics2D g2d = (Graphics2D) g;

				g.drawImage(allPartsImage, 0, 0, null);

			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		Point point = event.getPoint();
		
		int col = (int) (point.getX()/(((BattlefieldCreator.SQUARE_SIZE*BattlefieldCreator.SHRINK_PERCENT)*8)+5));
		int row = (int) (point.getY()/(((BattlefieldCreator.SQUARE_SIZE*BattlefieldCreator.SHRINK_PERCENT)*8)+5));
		
		int elementIndex = (row*25)+(col+1);
		BattlefieldCreator.setSelectedImage(parts.get(elementIndex));
		
		BattlefieldCreator.refreshAll();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		Point point = event.getPoint();
		
		int col = (int) (point.getX()/(((BattlefieldCreator.SQUARE_SIZE*BattlefieldCreator.SHRINK_PERCENT)*8)+5));
		int row = (int) (point.getY()/(((BattlefieldCreator.SQUARE_SIZE*BattlefieldCreator.SHRINK_PERCENT)*8)+5));
		
		int elementIndex = (row*25)+(col+1);
		BattlefieldCreator.setSelectedImage(parts.get(elementIndex));
		
		BattlefieldCreator.refreshAll();
	}
}
