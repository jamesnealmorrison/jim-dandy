package com.jimmie.util.aspects;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.GoblinGuardRoomEncounter;
import com.jimmie.gui.BattlefieldPanel;

public aspect BattlefieldPanelGetImgAspect {
	public pointcut getImg() : execution(Image com.jimmie.gui.BattlefieldPanel.getImg(..));
	private Image img = null;
		
	// This will determine if one of the player characters has passed beyond the trees and needs to make the monsters visible.
	Image around() : getImg() {
		if (GoblinGuardRoomEncounter.class.isAssignableFrom(Encounter.getEncounter().getClass())) {
			if (img == null) {
				try {
					File imageSrc = new File("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Battlefields\\Area1GoblinGuardRoomPitExposed.JPG");
					BufferedImage originalImg = ImageIO.read(imageSrc);
					double shrinkPercent = (double) BattlefieldPanel.SQUARE_SIZE/200.0;
					img = originalImg.getScaledInstance((int) (originalImg.getWidth(null)*shrinkPercent), (int)(originalImg.getHeight(null)*shrinkPercent), Image.SCALE_SMOOTH);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			GoblinGuardRoomEncounter encounter = (GoblinGuardRoomEncounter) Encounter.getEncounter();
			if (encounter.isPitTrapActivated()) {
				return img;
			}
		}
		return proceed();
	}

}
