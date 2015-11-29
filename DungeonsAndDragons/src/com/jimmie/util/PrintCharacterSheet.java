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
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.creatures.Gender;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Size;

public class PrintCharacterSheet {
	private static final int ROW1 = 47;
	private static final int ROW2 = 66;
	private static final int ROW3 = 106;
	private static final int ROW3_DEFENSES = 113;
	private static final int ROW4_DEFENSES = 159;
	private static final int ROW5_DEFENSES = 199;
	private static final int ROW6_DEFENSES = 241;
	private static final int ROW1_ABILITIES = 155;
	private static final int ROW2_ABILITIES = 172;
	private static final int ROW3_ABILITIES = 196;
	private static final int ROW4_ABILITIES = 213;
	private static final int ROW5_ABILITIES = 237;
	private static final int ROW6_ABILITIES = 254;
	private static final int ROW_HITPOINTS = 300;
	private static final int ROW1_SKILLS = 482;
	private static final int ROW2_SKILLS = 496;
	private static final int ROW3_SKILLS = 510;
	private static final int ROW4_SKILLS = 523;
	private static final int ROW5_SKILLS = 536;
	private static final int ROW6_SKILLS = 550;
	private static final int ROW7_SKILLS = 563;
	private static final int ROW8_SKILLS = 576;
	private static final int ROW9_SKILLS = 590;
	private static final int ROW10_SKILLS = 603;
	private static final int ROW11_SKILLS = 617;
	private static final int ROW12_SKILLS = 630;
	private static final int ROW13_SKILLS = 643;
	private static final int ROW14_SKILLS = 657;
	private static final int ROW15_SKILLS = 670;
	private static final int ROW16_SKILLS = 683;
	private static final int ROW17_SKILLS = 697;
	private static final int ROW_SPEED = ROW3;
			
	private static final int COL1_DEFENSES = 184;
	private static final int COL2_DEFENSES = 234;
	private static final int COL3_DEFENSES = 254;
	private static final int COL4_DEFENSES = 271;
	private static final int COL5_DEFENSES = 288;
	private static final int COL6_DEFENSES = 303;
	private static final int COL7_DEFENSES = 319;
	private static final int COL8_DEFENSES = 336;
	private static final int COL1_ABILITIES = 14;
	private static final int COL2_ABILITIES = 102;
	private static final int COL3_ABILITIES = 145;
	private static final int COL1_SKILLS = 11;
	private static final int COL2_SKILLS = 105;
	private static final int COL3_SKILLS = 127;
	private static final int COL4_SKILLS = 142;
	private static final int COL5_SKILLS = 162;
	private static final int COL1_RIGHT = 362;

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

		
		g2d.drawString(String.valueOf(pc.getFortitude()), COL1_DEFENSES, ROW4_DEFENSES);
		g2d.drawString(String.valueOf(10+(pc.getLevel()/2)), COL2_DEFENSES, ROW4_DEFENSES);
		g2d.drawString(String.valueOf(pc.getFortitudeAbilityModifier()), COL3_DEFENSES, ROW4_DEFENSES);
		g2d.drawString(String.valueOf(pc.getDndClass().getFortitudeBonus()), COL4_DEFENSES, ROW4_DEFENSES);
		g2d.drawString(String.valueOf(pc.getFeatFortitudeBonus()), COL5_DEFENSES, ROW4_DEFENSES);
		// TODO: Enhancement
		g2d.drawString(String.valueOf(0), COL6_DEFENSES, ROW4_DEFENSES);
		// Race bonus doesn't have a "home" on the sheet, so I'm going to add it to misc bonus 1.		
		g2d.drawString(String.valueOf(pc.getRace().getFortitudeBonus() + pc.getFortitudeMisc1()), COL7_DEFENSES, ROW4_DEFENSES);
		g2d.drawString(String.valueOf(pc.getFortitudeMisc2()), COL8_DEFENSES, ROW4_DEFENSES);

		g2d.drawString(String.valueOf(pc.getReflex()), COL1_DEFENSES, ROW5_DEFENSES);
		g2d.drawString(String.valueOf(10+(pc.getLevel()/2)), COL2_DEFENSES, ROW5_DEFENSES);
		g2d.drawString(String.valueOf(pc.getReflexAbilityModifier()), COL3_DEFENSES, ROW5_DEFENSES);
		g2d.drawString(String.valueOf(pc.getDndClass().getReflexBonus()), COL4_DEFENSES, ROW5_DEFENSES);
		g2d.drawString(String.valueOf(pc.getFeatReflexBonus()), COL5_DEFENSES, ROW5_DEFENSES);
		// TODO: Enhancement
		g2d.drawString(String.valueOf(0), COL6_DEFENSES, ROW5_DEFENSES);
		// Race bonus doesn't have a "home" on the sheet, so I'm going to add it to misc bonus 1.		
		g2d.drawString(String.valueOf(pc.getRace().getReflexBonus() + pc.getReflexMisc1()), COL7_DEFENSES, ROW5_DEFENSES);
		g2d.drawString(String.valueOf(pc.getReflexMisc2()), COL8_DEFENSES, ROW5_DEFENSES);

		g2d.drawString(String.valueOf(pc.getWill()), COL1_DEFENSES, ROW6_DEFENSES);
		g2d.drawString(String.valueOf(10+(pc.getLevel()/2)), COL2_DEFENSES, ROW6_DEFENSES);
		g2d.drawString(String.valueOf(pc.getWillAbilityModifier()), COL3_DEFENSES, ROW6_DEFENSES);
		g2d.drawString(String.valueOf(pc.getDndClass().getWillBonus()), COL4_DEFENSES, ROW6_DEFENSES);
		g2d.drawString(String.valueOf(pc.getFeatWillBonus()), COL5_DEFENSES, ROW6_DEFENSES);
		// TODO: Enhancement
		g2d.drawString(String.valueOf(0), COL6_DEFENSES, ROW6_DEFENSES);
		// Race bonus doesn't have a "home" on the sheet, so I'm going to add it to misc bonus 1.		
		g2d.drawString(String.valueOf(pc.getRace().getWillBonus() + pc.getWillMisc1()), COL7_DEFENSES, ROW6_DEFENSES);
		g2d.drawString(String.valueOf(pc.getWillMisc2()), COL8_DEFENSES, ROW6_DEFENSES);

		// Race modifier is built into this one.
		g2d.drawString(String.valueOf(pc.getStrength()), COL1_ABILITIES, ROW1_ABILITIES);
		// TODO: Ability Modifier
		g2d.drawString(String.valueOf(0), COL2_ABILITIES, ROW1_ABILITIES);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH)), COL3_ABILITIES, ROW1_ABILITIES);

		// Race modifier is built into this one.
		g2d.drawString(String.valueOf(pc.getConstitution()), COL1_ABILITIES, ROW2_ABILITIES);
		// TODO: Ability Modifier
		g2d.drawString(String.valueOf(0), COL2_ABILITIES, ROW2_ABILITIES);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(AbilityType.CONSTITUTION)), COL3_ABILITIES, ROW2_ABILITIES);
		
		// Race modifier is built into this one.
		g2d.drawString(String.valueOf(pc.getDexterity()), COL1_ABILITIES, ROW3_ABILITIES);
		// TODO: Ability Modifier
		g2d.drawString(String.valueOf(0), COL2_ABILITIES, ROW3_ABILITIES);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY)), COL3_ABILITIES, ROW3_ABILITIES);
		
		// Race modifier is built into this one.
		g2d.drawString(String.valueOf(pc.getIntelligence()), COL1_ABILITIES, ROW4_ABILITIES);
		// TODO: Ability Modifier
		g2d.drawString(String.valueOf(0), COL2_ABILITIES, ROW4_ABILITIES);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE)), COL3_ABILITIES, ROW4_ABILITIES);
		
		// Race modifier is built into this one.
		g2d.drawString(String.valueOf(pc.getWisdom()), COL1_ABILITIES, ROW5_ABILITIES);
		// TODO: Ability Modifier
		g2d.drawString(String.valueOf(0), COL2_ABILITIES, ROW5_ABILITIES);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM)), COL3_ABILITIES, ROW5_ABILITIES);
		
		// Race modifier is built into this one.
		g2d.drawString(String.valueOf(pc.getCharisma()), COL1_ABILITIES, ROW6_ABILITIES);
		// TODO: Ability Modifier
		g2d.drawString(String.valueOf(0), COL2_ABILITIES, ROW6_ABILITIES);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA)), COL3_ABILITIES, ROW6_ABILITIES);

		// Hit point row
		g2d.drawString(String.valueOf(pc.getMaxHitPoints()), 16, ROW_HITPOINTS);
		g2d.drawString(String.valueOf(pc.getBloodyValue()), 60, ROW_HITPOINTS);
		g2d.drawString(String.valueOf(pc.getHealingSurgeValue()), 110, ROW_HITPOINTS);
		g2d.drawString(String.valueOf(pc.getHealingSurgesPerDay()), 150, ROW_HITPOINTS);
		
		// Skills
		Skill skill = pc.getSkill(SkillType.ACROBATICS);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW1_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW1_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW1_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW1_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW1_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW1_SKILLS);
		
		skill = pc.getSkill(SkillType.ARCANA);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW2_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW2_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW2_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW2_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW2_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW2_SKILLS);
		
		skill = pc.getSkill(SkillType.ATHLETICS);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW3_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW3_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW3_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW3_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW3_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW3_SKILLS);
		
		skill = pc.getSkill(SkillType.BLUFF);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW4_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW4_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW4_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW4_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW4_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW4_SKILLS);
		
		skill = pc.getSkill(SkillType.DIPLOMACY);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW5_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW5_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW5_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW5_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW5_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW5_SKILLS);
		
		skill = pc.getSkill(SkillType.DUNGEONEERING);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW6_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW6_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW6_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW6_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW6_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW6_SKILLS);
		
		skill = pc.getSkill(SkillType.ENDURANCE);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW7_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW7_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW7_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW7_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW7_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW7_SKILLS);
		
		skill = pc.getSkill(SkillType.HEAL);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW8_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW8_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW8_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW8_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW8_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW8_SKILLS);
		
		skill = pc.getSkill(SkillType.HISTORY);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW9_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW9_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW9_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW9_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW9_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW9_SKILLS);
		
		skill = pc.getSkill(SkillType.INSIGHT);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW10_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW10_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW10_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW10_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW10_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW10_SKILLS);
		
		skill = pc.getSkill(SkillType.INTIMIDATE);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW11_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW11_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW11_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW11_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW11_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW11_SKILLS);
		
		skill = pc.getSkill(SkillType.NATURE);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW12_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW12_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW12_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW12_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW12_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW12_SKILLS);
		
		skill = pc.getSkill(SkillType.PERCEPTION);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW13_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW13_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW13_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW13_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW13_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW13_SKILLS);
		
		skill = pc.getSkill(SkillType.RELIGION);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW14_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW14_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW14_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW14_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW14_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW14_SKILLS);
		
		skill = pc.getSkill(SkillType.STEALTH);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW15_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW15_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW15_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW15_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW15_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW15_SKILLS);
		
		skill = pc.getSkill(SkillType.STREETWISE);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW16_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW16_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW16_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW16_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW16_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW16_SKILLS);
		
		skill = pc.getSkill(SkillType.THIEVERY);
		g2d.drawString(String.valueOf(pc.getSkillModifier(skill.getSkillType())), COL1_SKILLS, ROW17_SKILLS);
		g2d.drawString(String.valueOf(pc.getAbilityModifierPlusHalfLevel(skill.getAbilityType())), COL2_SKILLS, ROW17_SKILLS);
		if (skill.isTrained()) {
			g2d.drawString(String.valueOf(5), COL3_SKILLS, ROW17_SKILLS);			
		} else {
			g2d.drawString(String.valueOf(0), COL3_SKILLS, ROW17_SKILLS);						
		}
		if (skill.hasArmorPenalty()) {
			g2d.drawString(String.valueOf(pc.getReadiedArmor().getSkillPenalty()), COL4_SKILLS, ROW17_SKILLS);
		}
		g2d.drawString(String.valueOf(skill.getMisc()), COL5_SKILLS, ROW17_SKILLS);

		// Speed
		g2d.drawString(String.valueOf(pc.getSpeed()), COL1_RIGHT, ROW_SPEED);
		g2d.drawString(String.valueOf(pc.getBaseSpeed()), 451, ROW_SPEED);
		g2d.drawString(String.valueOf(pc.getReadiedArmor().getSpeedPenalty()), 469, ROW_SPEED);
		g2d.drawString(String.valueOf(0), 489, ROW_SPEED);
		g2d.drawString(String.valueOf(0), 508, ROW_SPEED);
		
		// Senses
		g2d.drawString(String.valueOf(10+pc.getSkillModifier(SkillType.INSIGHT)), COL1_RIGHT-3, 157);
		g2d.drawString(String.valueOf(pc.getSkillModifier(SkillType.INSIGHT)), 498, 157);
		g2d.drawString(String.valueOf(10+pc.getSkillModifier(SkillType.PERCEPTION)), COL1_RIGHT-3, 175);
		g2d.drawString(String.valueOf(pc.getSkillModifier(SkillType.PERCEPTION)), 498, 175);
		
		
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
