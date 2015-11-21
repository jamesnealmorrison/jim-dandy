package com.jimmie.util;

import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.creatures.Gender;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Size;
import com.jimmie.domain.creatures.Alignment;

public class PrintCharacterSheet {
	private static final int ROW1 = 47;
	private static final int ROW2 = 66;
	private static final int ROW3 = 106;
	private static final int ROW3_DEFENSES = 113;
	private static final int ROW4_DEFENSES = 159;
		
	private static final int COL1_DEFENSES = 184;
	private static final int COL2_DEFENSES = 234;
	private static final int COL3_DEFENSES = 254;
	private static final int COL4_DEFENSES = 271;
	private static final int COL5_DEFENSES = 288;
	private static final int COL6_DEFENSES = 303;
	private static final int COL7_DEFENSES = 319;
	private static final int COL8_DEFENSES = 336;

	private File imageSrc;
	private File outputFile;
	private BufferedImage img;

	public PrintCharacterSheet() {
		
	}
	
	public static void main(String[] args) {
		PrintCharacterSheet pcs = new PrintCharacterSheet();
		pcs.run();
	}

	private void run() {

		try {
			imageSrc = new File("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\characterSheet_page1.JPG");
			img = ImageIO.read(imageSrc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Graphics2D g2d = img.createGraphics();
		Font font = new Font("Century", Font.PLAIN, 10);
		g2d.setFont(font);
		g2d.setColor(Color.BLACK);
		
		
		PlayerCharacter pc = getCharacterToPrint();
		g2d.drawString(pc.getName(), 5, ROW1);		
		g2d.drawString(String.valueOf(pc.getLevel()), 190, ROW1);
		g2d.drawString(pc.getDndClass().getClass().getSimpleName(), 215, ROW1);
		// TODO: Paragon Path and Epic Destiny
		g2d.drawString(String.valueOf(pc.getExperiencePoints()), 480, ROW1);
		
		g2d.drawString(pc.getRace().getClass().getSimpleName(), 5, ROW2);
		
		// TODO: ALL pc's are small or medium.  I don't care about the rest.
		if (pc.getSize() == Size.SMALL) {
			g2d.drawString("SM", 110, ROW2);
		} else if (pc.getSize() == Size.MEDIUM) {
			g2d.drawString("MED", 110, ROW2);
		}
		g2d.drawString(String.valueOf(pc.getAge()), 140, ROW2);
		if (pc.getGender() == Gender.MALE) {
			g2d.drawString("M", 172, ROW2);
		} else {
			g2d.drawString("F", 172, ROW2);
		}
		g2d.drawString(String.valueOf(pc.getHeight())+" \"", 197, ROW2);
		g2d.drawString(String.valueOf(pc.getWeight())+"lb", 224, ROW2);
		int alignmentX = 257;
		switch (pc.getAlignment()) {
		case GOOD :
			g2d.drawString("Good", alignmentX, ROW2);
			break;
		case LAWFUL_GOOD :
			g2d.drawString("Lawful", alignmentX, ROW2);
			break;
		case EVIL :
			g2d.drawString("Evil", alignmentX, ROW2);
			break;
		case CHAOTIC_EVIL :
			g2d.drawString("Chaotic", alignmentX, ROW2);
			break;
		case UNALIGNED :
			g2d.drawString("Unaligned", alignmentX, ROW2);
			break;
		}
		int deityX = 312;
		switch (pc.getDeity()) {
		case AVANDRA :
			g2d.drawString("Avandra", deityX, ROW2);
			break;
		case BAHAMUT :
			g2d.drawString("Bahamut", deityX, ROW2);
			break;
		case CORELLON :
			g2d.drawString("Corellon", deityX, ROW2);
			break;
		case ERATHIS :
			g2d.drawString("Erathis", deityX, ROW2);
			break;
		case IOUN :
			g2d.drawString("Ioun", deityX, ROW2);
			break;
		case KORD :
			g2d.drawString("Kord", deityX, ROW2);
			break;
		case MELORA :
			g2d.drawString("Melora", deityX, ROW2);
			break;
		case MORADIN :
			g2d.drawString("Moradin", deityX, ROW2);
			break;
		case PELOR :
			g2d.drawString("Pelor", deityX, ROW2);
			break;
		case THE_RAVEN_QUEEN :
			// RavenQueen is a bit long.
			g2d.drawString("RavenQueen", deityX-2, ROW2);
			break;
		case SEHANINE :
			g2d.drawString("Sehanine", deityX, ROW2);
			break;
		case NONE :
			g2d.drawString("None", deityX, ROW2);
			break;
		}
		g2d.drawString(pc.getAdventuringCompanyOrOtherAffiliations(), 370, ROW2);
		
		g2d.drawString(String.valueOf(pc.getInitiative()), 14, ROW3);
		g2d.drawString(String.valueOf(pc.getAbilityModifier(AbilityType.DEXTERITY)), 87, ROW3);
		g2d.drawString(String.valueOf(pc.getLevel()/2), 108, ROW3);
		g2d.drawString(String.valueOf(pc.getInitiativeMisc()), 160, ROW3);
		
		
		g2d.drawString(String.valueOf(pc.getBaseArmorClass()), COL1_DEFENSES, ROW3_DEFENSES);
		g2d.drawString(String.valueOf(10+(pc.getLevel()/2)), COL2_DEFENSES, ROW3_DEFENSES);
		g2d.drawString(String.valueOf(pc.getReadiedArmor().getBonus() + pc.getReadiedShield().getBonus()), COL3_DEFENSES, ROW3_DEFENSES);
		g2d.drawString(String.valueOf(pc.getDndClass().getArmorClassBonus()), COL4_DEFENSES, ROW3_DEFENSES);
		g2d.drawString(String.valueOf(pc.getFeatArmorClassBonus()), COL5_DEFENSES, ROW3_DEFENSES);
		// TODO: fill these in when I implement them.
		g2d.drawString(String.valueOf(0), COL6_DEFENSES, ROW3_DEFENSES);
		g2d.drawString(String.valueOf(0), COL7_DEFENSES, ROW3_DEFENSES);
		g2d.drawString(String.valueOf(0), COL8_DEFENSES, ROW3_DEFENSES);

		
//		g2d.drawString(String.valueOf(pc.getBaseFortitude()), COL1_DEFENSES, ROW4_DEFENSES);
//		g2d.drawString(String.valueOf(10+(pc.getLevel()/2)), COL2_DEFENSES, ROW4_DEFENSES);
//		g2d.drawString(String.valueOf(pc.getReadiedArmor().getBonus() + pc.getReadiedShield().getBonus()), COL3_DEFENSES, ROW4_DEFENSES);
//		g2d.drawString(String.valueOf(pc.getDndClass().getArmorClassBonus()), COL4_DEFENSES, ROW4_DEFENSES);
//		g2d.drawString(String.valueOf(pc.getFeatArmorClassBonus()), COL5_DEFENSES, ROW4_DEFENSES);
//		// TODO: fill these in when I implement them.
//		g2d.drawString(String.valueOf(0), COL6_DEFENSES, ROW4_DEFENSES);
//		g2d.drawString(String.valueOf(0), COL7_DEFENSES, ROW4_DEFENSES);
//		g2d.drawString(String.valueOf(0), COL8_DEFENSES, ROW4_DEFENSES);

		
		
		try {
			outputFile = new File("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\outputImage.JPG");
			ImageIO.write(img, "jpg", outputFile);
			Utils.print("Wrote it");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Now print it:
/*		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(new ImagePrintable(printJob, img));
		
		if (printJob.printDialog()) {
			try {
				printJob.print();
			} catch (PrinterException prt) {
				prt.printStackTrace();
			}
		}
*/		
	}

	private PlayerCharacter getCharacterToPrint() {
		PlayerCharacter pc = (PlayerCharacter) Utils.loadCharacter("Arannis");
		pc.setReadiedArmor(pc.getArmor().get(1));
		return pc;
	}
	
}
