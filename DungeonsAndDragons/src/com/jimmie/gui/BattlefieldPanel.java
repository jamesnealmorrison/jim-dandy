package com.jimmie.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.springframework.stereotype.Component;

import com.jimmie.domain.Position;
import com.jimmie.domain.Zone;
import com.jimmie.domain.ZoneShape;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.monsters.Monster;
import com.jimmie.domain.map.Map;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

@Component
public class BattlefieldPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int SQUARE_SIZE = 69;
	private Map map;
	List<Creature> creatures;
	private BufferedImage img;
	private File imageSrc;
	
	public void init(Map map, List<Creature> creatures) {
		this.setMap(map);
		Dimension dimension = new Dimension((map.getWidth())*SQUARE_SIZE,(map.getHeight())*SQUARE_SIZE);
		this.setPreferredSize(dimension);
		this.creatures = creatures;		
		try {
			imageSrc = new File("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KoboldOutsideLair.JPG");
			img = ImageIO.read(imageSrc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Image shrinkCreatureTile(Image image) {
		// Only do this if the creature tile is 200, 400, or 600 square.
		if (image != null) {
			int imageWidth = image.getWidth(null);
			double shrinkPercent = (double) SQUARE_SIZE/200.0;
			if ((imageWidth == 200) || (imageWidth == 400) || (imageWidth == 600)) {
				int newWidth = (int) (imageWidth*shrinkPercent);
				int newHeight = (int) (image.getHeight(null)*shrinkPercent);
				Image newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
				Graphics g = newImage.getGraphics();
				g.drawImage(image, 0, 0, newWidth, newHeight, null);
				return newImage;

			}
			return image;
		}
		return null;
	}
	
	@Override
	public void paint(Graphics g) {
		if (Graphics2D.class.isAssignableFrom(g.getClass())) {
			Graphics2D g2d = (Graphics2D) g;
//			g2d.setBackground(Color.black);
//			g2d.clearRect(0,0,getBounds().width,getBounds().height);
			
			g.drawImage(img, 0, 0, null);
			
			/* Now draw the characters. */
			for (Iterator<Creature> it = creatures.iterator(); it.hasNext();) {
				Creature creature = it.next();
				// Don't draw dead monsters.
				if (Monster.class.isAssignableFrom(creature.getClass())) {
					if (creature.getCurrentHitPoints() <= 0) {
						it.remove();
						continue;
					}
				}
				ScreenPosition screenPosition = new ScreenPosition(creature.getCurrentPosition(), new Dimension(map.getWidth(),map.getHeight()), SQUARE_SIZE);
				//g2d.setXORMode(Color.white);
				if (creature.getImage() != null) {

//				Image newImage = transformWhiteToTransparency(CreatureTile(creature.getImage()));
				double shrinkPercent = (double) SQUARE_SIZE/200.0;
				Image newImage = creature.getScaledImage(shrinkPercent);
								
				if (creature.isBloodied()) {
					newImage = creature.getScaledBloodiedImage(shrinkPercent); 
				}
				g2d.drawImage(newImage, screenPosition.getTopLeftCorner().getX(), screenPosition.getTopLeftCorner().getY(), null);
				g2d.drawString(creature.getDisplayName(), screenPosition.getTopLeftCorner().getX(), screenPosition.getTopLeftCorner().getY());
				}
			}
			
			// See if there are any zones that need to be painted.
			List<Zone> zones = Encounter.getEncounter().getZones();
			if (zones != null) {
				for (Zone zone : zones) {
					if (zone.stillApplies()) {
						Position origin = zone.getZoneOrigin();
						ZoneShape shape = zone.getZoneShape();
						if (shape == ZoneShape.BURST) {
							// For each square on the map, see if it's in range of the zone burst.
							for (int row = 0; row <= map.getWidth(); row++) {
								for (int col = 0; col <= map.getHeight(); col++) {
									Position mapPosition = new Position(row,col);
									if (mapPosition.isWithinReachOf(origin, zone.getSize())) {
										ScreenPosition screenPosition = new ScreenPosition(mapPosition, new Dimension(map.getWidth(),map.getHeight()), SQUARE_SIZE);
										g2d.setColor(Color.RED);
										g2d.drawRect(screenPosition.getTopLeftCorner().getX(), screenPosition.getTopLeftCorner().getY(), SQUARE_SIZE, SQUARE_SIZE);
									}
								}
							}
						} else {
							Utils.print("There is a zone on the encounter that I don't know how to paint yet.  See BattlefieldPanel.");
						}
					}
				}
			}

		}
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
