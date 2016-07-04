package com.jimmie.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jimmie.domain.Position;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.Zone;
import com.jimmie.domain.ZoneShape;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.Monster;
import com.jimmie.domain.map.Map;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

@Component
public class BattlefieldPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int SQUARE_SIZE = 69;
	private Map map;
	List<Creature> creatures;
	private Image img;
	private BufferedImage originalImg;
	private File imageSrc;
	@Autowired
	private String battlefieldImageFilePath;
	private HashMap<String, Image> images;
	private boolean printedInvisibilityMessage = false;

	public void init(Map map, List<Creature> creatures) {
		this.setMap(map);
		images = new HashMap<String, Image>();
		Dimension dimension = new Dimension((map.getWidth())*SQUARE_SIZE,(map.getHeight())*SQUARE_SIZE);
		this.setPreferredSize(dimension);
		this.creatures = creatures;		
		try {
			imageSrc = new File(battlefieldImageFilePath);
			originalImg = ImageIO.read(imageSrc);
			double shrinkPercent = (double) SQUARE_SIZE/200.0;
			img = originalImg.getScaledInstance((int) (originalImg.getWidth(null)*shrinkPercent), (int)(originalImg.getHeight(null)*shrinkPercent), Image.SCALE_SMOOTH);
			
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
			
			g.drawImage(getImg(), 0, 0, null);
			
			/* Now draw the characters. */
			for (Iterator<Creature> it = creatures.iterator(); it.hasNext();) {
				Creature creature = it.next();
				if (Monster.class.isAssignableFrom(creature.getClass())) {
					// Don't draw dead monsters.
					if (creature.getCurrentHitPoints() <= 0) {
						it.remove();
						continue;
					}
					
					if (!Encounter.areMonstersVisible()) {
						continue;
					}
				}

				// Check for invisibility.
				if (creature.isInvisibleTo(Encounter.getEncounter().getCurrentParticipant())) {
					// Don't just continue.  If the creature is invisible to everyone, the "isInvisibleTo" method will return true always.
					// But I still need to be able to see the creature during its own turn so I can move it appropriately.
					if (creature == Encounter.getEncounter().getCurrentParticipant()) {
						// Print this only once.
						if (!printedInvisibilityMessage ) {
							Utils.print("Even though " + creature.getName() + " is invisible right now, I'm drawing them on the screen during their turn.");
							Utils.print("Everyone except the DM and " + creature.getName() + " should 'forget' what they see now. ;-) ");
							printedInvisibilityMessage = true;
						}
					} else {
						continue;
					}
				}
				
				if (DndCharacter.class.isAssignableFrom(creature.getClass())) {
					if (!Encounter.areCharactersVisible()) {
						continue;
					}
				}
				ScreenPosition screenPosition = new ScreenPosition(creature.getCurrentPosition(), new Dimension(map.getWidth(),map.getHeight()), SQUARE_SIZE);

				String imagePath = creature.getImagePath();
				if (imagePath != null) {
					double shrinkPercent = (double) SQUARE_SIZE/200.0;
					// Look in the HashMap for the image.
					Image newImage = images.get(imagePath);
					// If not found, read it from the file and put it in the hash map.
					if (newImage == null) {
						try {
							Image image = ImageIO.read(new File(imagePath));
							newImage = getScaledImage(image, shrinkPercent);

							// Put the scaled image in there.
							images.put(imagePath, newImage);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
					}
					
					

					if (creature.isBloodied()) {
						String bloodiedImagePath = creature.getBloodiedImagePath();
						// Look in the HashMap for the bloodied image.
						newImage = images.get(bloodiedImagePath);
						if (newImage == null) {
							try {
								Image image = ImageIO.read(new File(bloodiedImagePath));
								newImage = getScaledImage(image, shrinkPercent);

								// Put the scaled bloodied image in there.
								images.put(bloodiedImagePath, newImage);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}						
						}
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
			
			// Do any shading based on visibility/line of sight/etc.
			TurnTaker currentTurnTaker = Encounter.getEncounter().getCurrentParticipant();
			if (currentTurnTaker != null) {
				// Loop through all of the squares on the board.
				for (int row = 0; row <= map.getWidth(); row++) {
					for (int col = 0; col <= map.getHeight(); col++) {
						Position mapPosition = new Position(row,col);
						// If they don't have line of sight, then they can't even see it.
						if (!Encounter.getEncounter().hasLineOfSight(currentTurnTaker.getCurrentPosition(), mapPosition, null, null)) {
							ScreenPosition screenPosition = new ScreenPosition(mapPosition, new Dimension(map.getWidth(),map.getHeight()), SQUARE_SIZE);
							g2d.setColor(Color.BLACK);
							g2d.fillRect(screenPosition.getTopLeftCorner().getX(), screenPosition.getTopLeftCorner().getY(), SQUARE_SIZE, SQUARE_SIZE);
						} else {
							// They have line of sight, but see if it's obscured
							if (Creature.class.isAssignableFrom(currentTurnTaker.getClass())) {
								Creature currentCreature = (Creature) currentTurnTaker;
								int increment = 0;
								if (Encounter.getEncounter().hasTotalConcealment(currentCreature, mapPosition)) {
									ScreenPosition screenPosition = new ScreenPosition(mapPosition, new Dimension(map.getWidth(),map.getHeight()), SQUARE_SIZE);
									g2d.setColor(Color.BLACK);
									g2d.fillRect(screenPosition.getTopLeftCorner().getX(), screenPosition.getTopLeftCorner().getY(), SQUARE_SIZE, SQUARE_SIZE);									
								} else if (Encounter.getEncounter().hasConcealment(currentCreature, mapPosition)) {
									g2d.setColor(Color.BLACK);
									increment = 3;
								}

								if (increment > 0) {
									ScreenPosition screenPosition = new ScreenPosition(mapPosition, new Dimension(map.getWidth(),map.getHeight()), SQUARE_SIZE);
									// Fill every other dot with black.
									int topLeftX = screenPosition.getTopLeftCorner().getX();
									boolean evenRow = false;
									for (int x = topLeftX; x < topLeftX + (SQUARE_SIZE-1); x = x + increment) {
										int topLeftY = screenPosition.getTopLeftCorner().getY();
										for (int y = topLeftY; y < topLeftY + SQUARE_SIZE; y = y + increment) {
											if (evenRow) {
												g2d.drawRect(x, y, 1, 1);
											} else {
												g2d.drawRect(x, y+2, 1, 1);
											}
										}
										if (evenRow) {
											evenRow = false;
										} else {
											evenRow = true;
										}
									}
									
								}
							}
						}
					}
				}
			}
			
			
			// See if the coordinate system should be shown or not.
			if (Encounter.isShowCoordinateSystem()) {
				for (int row = 0; row <= map.getWidth(); row++) {
					for (int col = 0; col <= map.getHeight(); col++) {
						Position mapPosition = new Position(row,col);
						ScreenPosition screenPosition = new ScreenPosition(mapPosition, new Dimension(map.getWidth(),map.getHeight()), SQUARE_SIZE);
						g2d.setColor(Color.BLACK);
						g2d.drawRect(screenPosition.getTopLeftCorner().getX(), screenPosition.getTopLeftCorner().getY(), SQUARE_SIZE, SQUARE_SIZE);
						g2d.drawString("("+row+","+col+")", screenPosition.getTopLeftCorner().getX()+(SQUARE_SIZE/2)-8, screenPosition.getTopLeftCorner().getY()+((SQUARE_SIZE/2)+8));
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
	
	public String getImageFilePath() {
		return battlefieldImageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.battlefieldImageFilePath = imageFilePath;
	}

	public Image getScaledImage(Image image, double shrinkPercent) {
		Image scaledImage = null;
		if ((image.getWidth(null) == 200) || (image.getWidth(null) == 400) || (image.getWidth(null) == 600)) {
			Image newImage = makeTransparent((BufferedImage)image);

			scaledImage = newImage.getScaledInstance((int) (newImage.getWidth(null)*shrinkPercent), (int)(newImage.getHeight(null)*shrinkPercent), Image.SCALE_SMOOTH);
		}
		return scaledImage;
	}

	private Image makeTransparent(BufferedImage image)
	{
		ImageFilter filter = new RGBImageFilter()
		{
			public final int filterRGB(int x, int y, int rgb)
			{
				if (!pixelInsideCircle(x,y,image.getWidth())) {
					return 0;
				} else {
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
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

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}	


}
