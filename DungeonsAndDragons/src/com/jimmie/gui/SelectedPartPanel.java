package com.jimmie.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class SelectedPartPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		Dimension selectedPartPanelDimension = new Dimension(400,400);
		setPreferredSize(selectedPartPanelDimension);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Image image = BattlefieldCreator.shrinkImage(BattlefieldCreator.getSelectedImage());
		if (image != null) {
//			Dimension dimension = new Dimension(BattlefieldCreator.getImage().getWidth(null),BattlefieldCreator.getImage().getHeight(null));
//			this.setPreferredSize(dimension);

			if (Graphics2D.class.isAssignableFrom(g.getClass())) {
//				Graphics2D g2d = (Graphics2D) g;

				g.drawImage(image, 0, 0, null);

			}
		}
	}
}
