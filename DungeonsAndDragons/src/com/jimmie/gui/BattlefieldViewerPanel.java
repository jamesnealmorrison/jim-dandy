package com.jimmie.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class BattlefieldViewerPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		Image tempImage = BattlefieldCreator.shrinkImage(BattlefieldCreator.getBattlefieldImage());
		
		int width = tempImage.getWidth(null);
		int height = tempImage.getHeight(null);
		Dimension dimension = new Dimension(width,height);
		this.setPreferredSize(dimension);
		addMouseListener(this);
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Image image = BattlefieldCreator.shrinkImage(BattlefieldCreator.getBattlefieldImage());
		if (image != null) {
//			Dimension dimension = new Dimension(BattlefieldCreator.getImage().getWidth(null),BattlefieldCreator.getImage().getHeight(null));
//			this.setPreferredSize(dimension);

			if (Graphics2D.class.isAssignableFrom(g.getClass())) {
//				Graphics2D g2d = (Graphics2D) g;

				g.drawImage(image, 0, 0, null);

			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		
		int col = (int) (point.getX()/(((BattlefieldCreator.SQUARE_SIZE*BattlefieldCreator.SHRINK_PERCENT))));
		int row = (int) (point.getY()/(((BattlefieldCreator.SQUARE_SIZE*BattlefieldCreator.SHRINK_PERCENT))));
		
		Image image = BattlefieldCreator.getBattlefieldImage();
		Graphics g = image.getGraphics();
		g.drawImage(BattlefieldCreator.getSelectedImage(), (col*BattlefieldCreator.SQUARE_SIZE), (row*BattlefieldCreator.SQUARE_SIZE), null);
		
		BattlefieldCreator.refreshAll();
	}
	
}
