package com.jimmie.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.Sense;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.creatures.Gender;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Size;
import com.jimmie.domain.feats.Feat;
import com.jimmie.domain.items.Gear;
import com.jimmie.domain.items.armor.Armor;
import com.jimmie.domain.items.weapons.Weapon;
import com.jimmie.powers.Power;
import com.jimmie.rituals.Ritual;

public class PrintCharacterSheet {
	private static final int ROW1 = 155;
	private static final int ROW2 = 203;
	private static final int ROW3 = 314;
	private static final int ROW3_DEFENSES = 330;
	private static final int ROW4_DEFENSES = 459;
	private static final int ROW5_DEFENSES = 570;
	private static final int ROW6_DEFENSES = 687;
	private static final int ROW1_ABILITIES = 449;
	private static final int ROW2_ABILITIES = 497;
	private static final int ROW3_ABILITIES = 565;
	private static final int ROW4_ABILITIES = 612;
	private static final int ROW5_ABILITIES = 678;
	private static final int ROW6_ABILITIES = 724;
	private static final int ROW_HITPOINTS = 854;
	private static final int ROW1_SKILLS = 1365;
	private static final int ROW2_SKILLS = 1402;
	private static final int ROW3_SKILLS = 1438;
	private static final int ROW4_SKILLS = 1476;
	private static final int ROW5_SKILLS = 1513;
	private static final int ROW6_SKILLS = 1550;
	private static final int ROW7_SKILLS = 1587;
	private static final int ROW8_SKILLS = 1625;
	private static final int ROW9_SKILLS = 1663;
	private static final int ROW10_SKILLS = 1701;
	private static final int ROW11_SKILLS = 1739;
	private static final int ROW12_SKILLS = 1777;
	private static final int ROW13_SKILLS = 1813;
	private static final int ROW14_SKILLS = 1849;
	private static final int ROW15_SKILLS = 1888;
	private static final int ROW16_SKILLS = 1923;
	private static final int ROW17_SKILLS = 1964;
	private static final int ROW_SPEED = ROW3;
			
	private static final int COL1_DEFENSES = 550;
	private static final int COL2_DEFENSES = 688;
	private static final int COL3_DEFENSES = 742;
	private static final int COL4_DEFENSES = 790;
	private static final int COL5_DEFENSES = 838;
	private static final int COL6_DEFENSES = 880;
	private static final int COL7_DEFENSES = 924;
	private static final int COL8_DEFENSES = 972;
	private static final int COL1_ABILITIES = 80;
	private static final int COL2_ABILITIES = 324;
	private static final int COL3_ABILITIES = 444;
	private static final int COL1_SKILLS = 67;
	private static final int COL2_SKILLS = 330;
	private static final int COL3_SKILLS = 394;
	private static final int COL4_SKILLS = 436;
	private static final int COL5_SKILLS = 484;
	private static final int COL1_RIGHT = 1041;
	private static final int FEATURES_COL = 537;
	private static final int FEATS_COL = 1022;

	private File imageSrc;
	private File outputFile;
	private BufferedImage img;
	private String characterName;

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
		Font font = new Font("Century", Font.BOLD, 28);
		g2d.setFont(font);
		g2d.setColor(Color.BLACK);
		
		
		PlayerCharacter pc = getCharacterToPrint();
		if (pc == null) {
			Utils.print("Couldn't find that character.");
			return;
		}
		g2d.drawString(pc.getName(), 50, ROW1);		
		g2d.drawString(String.valueOf(pc.getLevel()), 565, ROW1-3);
		g2d.drawString(pc.getDndClass().getClass().getSimpleName(), 630, ROW1-4);
		// TODO: Paragon Path and Epic Destiny
		g2d.drawString(String.valueOf(pc.getExperiencePoints()), 1340, ROW1-8);
		
		g2d.drawString(pc.getRace().getClass().getSimpleName(), 50, ROW2);
		
		// TODO: ALL pc's are small or medium.  I don't care about the rest.
		if (pc.getSize() == Size.SMALL) {
			g2d.drawString("SM", 340, ROW2-2);
		} else if (pc.getSize() == Size.MEDIUM) {
			g2d.drawString("MED", 340, ROW2-2);
		}
		g2d.drawString(String.valueOf(pc.getAge()), 437, ROW2-2);
		if (pc.getGender() == Gender.MALE) {
			g2d.drawString("M", 517, ROW2-2);
		} else {
			g2d.drawString("F", 517, ROW2-2);
		}
		g2d.drawString(String.valueOf(pc.getHeight())+" \"", 585, ROW2-3);
		g2d.drawString(String.valueOf(pc.getWeight())+"lb", 658, ROW2-3);
		int alignmentX = 747;
		switch (pc.getAlignment()) {
		case GOOD :
			g2d.drawString("Good", alignmentX, ROW2-3);
			break;
		case LAWFUL_GOOD :
			g2d.drawString("Lawful", alignmentX, ROW2-3);
			break;
		case EVIL :
			g2d.drawString("Evil", alignmentX, ROW2-3);
			break;
		case CHAOTIC_EVIL :
			g2d.drawString("Chaotic", alignmentX, ROW2-3);
			break;
		case UNALIGNED :
			g2d.drawString("Unaligned", alignmentX, ROW2-3);
			break;
		}
		int deityX = 904;
		if (pc.getDeity() != null) {
			switch (pc.getDeity()) {
			case AVANDRA :
				g2d.drawString("Avandra", deityX, ROW2-4);
				break;
			case BAHAMUT :
				g2d.drawString("Bahamut", deityX, ROW2-4);
				break;
			case CORELLON :
				g2d.drawString("Corellon", deityX, ROW2-4);
				break;
			case ERATHIS :
				g2d.drawString("Erathis", deityX, ROW2-4);
				break;
			case IOUN :
				g2d.drawString("Ioun", deityX, ROW2-4);
				break;
			case KORD :
				g2d.drawString("Kord", deityX, ROW2-4);
				break;
			case MELORA :
				g2d.drawString("Melora", deityX, ROW2-4);
				break;
			case MORADIN :
				g2d.drawString("Moradin", deityX, ROW2-4);
				break;
			case PELOR :
				g2d.drawString("Pelor", deityX, ROW2-4);
				break;
			case THE_RAVEN_QUEEN :
				// RavenQueen is a bit long.
				g2d.drawString("RavenQueen", deityX-2, ROW2-4);
				break;
			case SEHANINE :
				g2d.drawString("Sehanine", deityX, ROW2-4);
				break;
			case NONE :
				g2d.drawString("None", deityX, ROW2-4);
				break;
			}
		}
		g2d.drawString(pc.getAdventuringCompanyOrOtherAffiliations(), 1065, ROW2-5);
		
		g2d.drawString(String.valueOf(pc.getInitiative()), 86, ROW3);
		g2d.drawString(String.valueOf(pc.getAbilityModifier(AbilityType.DEXTERITY)), 283, ROW3);
		g2d.drawString(String.valueOf(pc.getLevel()/2), 338, ROW3);
		g2d.drawString(String.valueOf(pc.getInitiativeMisc()), 483, ROW3);
		
		
		g2d.drawString(String.valueOf(pc.getBaseArmorClass()), COL1_DEFENSES, ROW3_DEFENSES);
		g2d.drawString(String.valueOf(10+(pc.getLevel()/2)), COL2_DEFENSES, ROW3_DEFENSES);
		g2d.drawString(String.valueOf(pc.getReadiedArmor().getBonus() + pc.getReadiedShield().getBonus() + pc.getArmorAbilityBonus()), COL3_DEFENSES, ROW3_DEFENSES-1);
		g2d.drawString(String.valueOf(pc.getDndClass().getArmorClassBonus()), COL4_DEFENSES, ROW3_DEFENSES-1);
		g2d.drawString(String.valueOf(pc.getFeatArmorClassBonus()), COL5_DEFENSES, ROW3_DEFENSES-2);
		// TODO: fill these in when I implement them.
		g2d.drawString(String.valueOf(0), COL6_DEFENSES, ROW3_DEFENSES-2);
		g2d.drawString(String.valueOf(0), COL7_DEFENSES, ROW3_DEFENSES-3);
		g2d.drawString(String.valueOf(0), COL8_DEFENSES, ROW3_DEFENSES-3);

		g2d.drawString(String.valueOf(pc.getFortitude(null)), COL1_DEFENSES, ROW4_DEFENSES);
		g2d.drawString(String.valueOf(10+(pc.getLevel()/2)), COL2_DEFENSES, ROW4_DEFENSES);
		g2d.drawString(String.valueOf(pc.getFortitudeAbilityModifier()), COL3_DEFENSES, ROW4_DEFENSES-1);
		g2d.drawString(String.valueOf(pc.getDndClass().getFortitudeBonus()), COL4_DEFENSES, ROW4_DEFENSES-1);
		g2d.drawString(String.valueOf(pc.getFeatFortitudeBonus()), COL5_DEFENSES, ROW4_DEFENSES-2);
		// TODO: Enhancement
		g2d.drawString(String.valueOf(0), COL6_DEFENSES, ROW4_DEFENSES-2);
		// Race bonus doesn't have a "home" on the sheet, so I'm going to add it to misc bonus 1.		
		g2d.drawString(String.valueOf(pc.getRace().getFortitudeBonus() + pc.getFortitudeMisc1()), COL7_DEFENSES, ROW4_DEFENSES-2);
		g2d.drawString(String.valueOf(pc.getFortitudeMisc2()), COL8_DEFENSES, ROW4_DEFENSES-2);

		g2d.drawString(String.valueOf(pc.getReflex(null)), COL1_DEFENSES, ROW5_DEFENSES);
		g2d.drawString(String.valueOf(10+(pc.getLevel()/2)), COL2_DEFENSES, ROW5_DEFENSES);
		g2d.drawString(String.valueOf(pc.getReflexAbilityModifier()), COL3_DEFENSES, ROW5_DEFENSES-1);
		g2d.drawString(String.valueOf(pc.getDndClass().getReflexBonus()), COL4_DEFENSES, ROW5_DEFENSES-1);
		g2d.drawString(String.valueOf(pc.getFeatReflexBonus()), COL5_DEFENSES, ROW5_DEFENSES-2);
		// TODO: Enhancement
		g2d.drawString(String.valueOf(0), COL6_DEFENSES, ROW5_DEFENSES-2);
		// Race bonus doesn't have a "home" on the sheet, so I'm going to add it to misc bonus 1.		
		g2d.drawString(String.valueOf(pc.getRace().getReflexBonus() + pc.getReflexMisc1()), COL7_DEFENSES, ROW5_DEFENSES-2);
		g2d.drawString(String.valueOf(pc.getReflexMisc2()), COL8_DEFENSES, ROW5_DEFENSES-2);

		g2d.drawString(String.valueOf(pc.getWill(null)), COL1_DEFENSES, ROW6_DEFENSES);
		g2d.drawString(String.valueOf(10+(pc.getLevel()/2)), COL2_DEFENSES, ROW6_DEFENSES);
		g2d.drawString(String.valueOf(pc.getWillAbilityModifier()), COL3_DEFENSES, ROW6_DEFENSES-1);
		g2d.drawString(String.valueOf(pc.getDndClass().getWillBonus()), COL4_DEFENSES, ROW6_DEFENSES-1);
		g2d.drawString(String.valueOf(pc.getFeatWillBonus()), COL5_DEFENSES, ROW6_DEFENSES-2);
		// TODO: Enhancement
		g2d.drawString(String.valueOf(0), COL6_DEFENSES, ROW6_DEFENSES-2);
		// Race bonus doesn't have a "home" on the sheet, so I'm going to add it to misc bonus 1.		
		g2d.drawString(String.valueOf(pc.getRace().getWillBonus() + pc.getWillMisc1()), COL7_DEFENSES, ROW6_DEFENSES-2);
		g2d.drawString(String.valueOf(pc.getWillMisc2()), COL8_DEFENSES, ROW6_DEFENSES-2);

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
		g2d.drawString(String.valueOf(pc.getMaxHitPoints()), 86, ROW_HITPOINTS);
		g2d.drawString(String.valueOf(pc.getBloodyValue()), 207, ROW_HITPOINTS);
		g2d.drawString(String.valueOf(pc.getHealingSurgeValue()), 348, ROW_HITPOINTS);
		g2d.drawString(String.valueOf(pc.getHealingSurgesPerDay()), 458, ROW_HITPOINTS);
		
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
		g2d.drawString(String.valueOf(pc.getSpeed()), COL1_RIGHT, ROW_SPEED-3);
		g2d.drawString(String.valueOf(pc.getBaseSpeed()), 1292, ROW_SPEED-3);
		g2d.drawString(String.valueOf(pc.getReadiedArmor().getSpeedPenalty()), 1344, ROW_SPEED-3);
		g2d.drawString(String.valueOf(0), 1398, ROW_SPEED-4);
		g2d.drawString(String.valueOf(0), 1447, ROW_SPEED-4);
		
		// Senses
		g2d.drawString(String.valueOf(10+pc.getSkillModifier(SkillType.INSIGHT)), COL1_RIGHT-6, 451);
		g2d.drawString(String.valueOf(pc.getSkillModifier(SkillType.INSIGHT)), 1420, 451);
		g2d.drawString(String.valueOf(10+pc.getSkillModifier(SkillType.PERCEPTION)), COL1_RIGHT-6, 501);
		g2d.drawString(String.valueOf(pc.getSkillModifier(SkillType.PERCEPTION)), 1420, 501);
		
		// Action Points
		g2d.drawString(String.valueOf(pc.getActionPoints()), 566, 821);
		
		// Race Features
		font = new Font("Century", Font.BOLD, 18);
		g2d.setFont(font);

		// Special Senses
		int col = 1021;
		for (Sense sense : pc.getSenses()) {
			g2d.drawString(sense.getType().toString(), col, 556);
			col += 249;
			if (col > 1400) {
				System.out.println("Not enough room to print all the senses.");
				break;
			}
		}

		if (pc.getRace().getRaceFeaturesText1() != null) {
			g2d.drawString(pc.getRace().getRaceFeaturesText1(), FEATURES_COL, 946);
		}
		if (pc.getRace().getRaceFeaturesText2() != null) {
			g2d.drawString(pc.getRace().getRaceFeaturesText2(), FEATURES_COL, 983);
		}
		if (pc.getRace().getRaceFeaturesText3() != null) {
			g2d.drawString(pc.getRace().getRaceFeaturesText3(), FEATURES_COL, 1021);
		}
		if (pc.getRace().getRaceFeaturesText4() != null) {
			g2d.drawString(pc.getRace().getRaceFeaturesText4(), FEATURES_COL, 1060);
		}
		if (pc.getRace().getRaceFeaturesText5() != null) {
			g2d.drawString(pc.getRace().getRaceFeaturesText5(), FEATURES_COL, 1097);
		}
		if (pc.getRace().getRaceFeaturesText6() != null) {
			g2d.drawString(pc.getRace().getRaceFeaturesText6(), FEATURES_COL, 1134);
		}
		if (pc.getRace().getRaceFeaturesText7() != null) {
			g2d.drawString(pc.getRace().getRaceFeaturesText7(), FEATURES_COL, 1171);
		}
		if (pc.getRace().getRaceFeaturesText8() != null) {
			g2d.drawString(pc.getRace().getRaceFeaturesText8(), FEATURES_COL, 1210);
		}
		if (pc.getRace().getRaceFeaturesText9() != null) {
			g2d.drawString(pc.getRace().getRaceFeaturesText9(), FEATURES_COL, 1244);
		}
		
		// Class Features
		if (pc.getDndClass().getClassFeaturesText1() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText1(), FEATURES_COL, 1315);
		}
		if (pc.getDndClass().getClassFeaturesText2() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText2(), FEATURES_COL, 1353);
		}
		if (pc.getDndClass().getClassFeaturesText3() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText3(), FEATURES_COL, 1391);
		}
		if (pc.getDndClass().getClassFeaturesText4() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText4(), FEATURES_COL, 1429);
		}
		if (pc.getDndClass().getClassFeaturesText5() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText5(), FEATURES_COL, 1467);
		}
		if (pc.getDndClass().getClassFeaturesText6() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText6(), FEATURES_COL, 1505);
		}
		if (pc.getDndClass().getClassFeaturesText7() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText7(), FEATURES_COL, 1543);
		}
		if (pc.getDndClass().getClassFeaturesText8() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText8(), FEATURES_COL, 1581);
		}
		if (pc.getDndClass().getClassFeaturesText9() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText9(), FEATURES_COL, 1619);
		}
		if (pc.getDndClass().getClassFeaturesText10() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText10(), FEATURES_COL, 1657);
		}
		if (pc.getDndClass().getClassFeaturesText11() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText11(), FEATURES_COL, 1695);
		}
		if (pc.getDndClass().getClassFeaturesText12() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText12(), FEATURES_COL, 1733);
		}
		if (pc.getDndClass().getClassFeaturesText13() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText13(), FEATURES_COL, 1771);
		}
		if (pc.getDndClass().getClassFeaturesText14() != null) {
			g2d.drawString(pc.getDndClass().getClassFeaturesText14(), FEATURES_COL, 1809);
		}
		
		// Languages
		int languageRow = 1880;
		col = 0; 
		for (String language : pc.getLanguages()) {
			if (col == 0) {
				g2d.drawString(language, FEATURES_COL, languageRow);
				col++;
			} else {
				g2d.drawString(language, FEATURES_COL+236, languageRow);
				col = 0;
				languageRow += 38;
			}
		}
		
		// Put a note reminding me to fill some sections in manually via paint.
		g2d.setColor(Color.RED);
		g2d.drawString("Jim, fill this section in. Use Century font.", 1077, 622);
		g2d.setColor(Color.BLACK);
		
		int row = 1357;
		for (Feat feat : pc.getFeats()) {
			g2d.drawString(feat.getName(), FEATS_COL, row);
			
			row += 38;
			if (row > 1964) {
				// Not enough room.
				System.out.println("Not enough room to print all the feats.");
				break;
			}
		}
		
		try {
			outputFile = new File("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\" + characterName + "CharacterSheet1.JPG");
			ImageIO.write(img, "jpg", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// I'm not going to populate the back sheet.  But I'll print everything here in case I want to fill it in manually.
		Utils.print("Powers:");
		for (Power power : pc.getPowers()) {
			Utils.print(power.getName() + ": " + power.getPowerUsage());
		}
		
		Utils.print("");
		Utils.print("Weapons:");
		for (Weapon weapon : pc.getWeapons()) {
			Utils.print(weapon.getName());
		}
		
		Utils.print("");
		Utils.print("Armor:");
		for (Armor armor : pc.getArmor()) {
			Utils.print(armor.getClass().getSimpleName());
		}
		
		Utils.print("");
		Utils.print("Gear:");
		for (Gear gear : pc.getGear()) {
			Utils.print(gear.getName());
		}
		
		Utils.print("");
		Utils.printCoins(pc);
		
		Utils.print("");
		Utils.print("Rituals:");
		if (pc.getRituals() != null) {
			for (Ritual ritual : pc.getRituals()) {
				Utils.print(ritual.getName());
			}
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
		Utils.print("Enter Character name you wish to print: (Case sensitive)");
		characterName = Utils.getInput();
		PlayerCharacter pc = (PlayerCharacter) Utils.loadCharacter(characterName);
		return pc;
	}
	
}
