package com.jimmie.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;


public class BattleCardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Image> images;

	public void init() {
		images = new HashMap<String, Image>();
		Dimension dimension = new Dimension(1000,900);
		this.setPreferredSize(dimension);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (Graphics2D.class.isAssignableFrom(g.getClass())) {
			Graphics2D g2d = (Graphics2D) g;

			TurnTaker turnTaker = Encounter.getEncounter().getCurrentParticipant();
			// Get the current turn taker.
			if (turnTaker != null) {
				if (Creature.class.isAssignableFrom(turnTaker.getClass())) {
					Creature creature = (Creature) turnTaker;
					
					String imagePath = creature.getBattleCardImagePath();
					if (imagePath != null) {
						// Look in the HashMap for the image.
						Image newImage = images.get(imagePath);
						// If not found, read it from the file and put it in the hash map.
						if (newImage == null) {
							try {
								newImage = ImageIO.read(new File(imagePath));
								images.put(imagePath, newImage);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}						
						}
						
						g2d.drawImage(newImage, 0, 0, null);
						setPreferredSize(new Dimension(newImage.getWidth(null), newImage.getHeight(null)));
					} else {
						g2d.setColor(Color.WHITE);
						g2d.drawRect(0, 0, getWidth(), getHeight());
						setPreferredSize(new Dimension(0,0));
					}
				}
			} else {
				g2d.setColor(Color.WHITE);
				g2d.drawRect(0, 0, getWidth(), getHeight());
				setPreferredSize(new Dimension(0,0));
			}
			revalidate();
		}
	}
}
