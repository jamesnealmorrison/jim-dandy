package com.jimmie.domain.creatures;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.items.weapons.WeaponType;
import com.jimmie.util.Utils;

public class Eladrin extends Race {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getRacialDamageBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initializeForEncounter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeRaceChoices(PlayerCharacter pc, DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Aesthetic, Arannis, Berrian, Dayereth, Erevan, Galinndan, Hadarai, Immeral, Mindartis, Paelias, Quarion, Riardon, Soveliss");
		Utils.print("Female Names: Althaea, Anastrianna, Andraste, Bethrynna, Caelynna, Jeleneth, Leshanna, Meriele, Naivara, Quelenna, Sariel, Shanairra, Theirastra, Valenae");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 5' 5\" - 6' 1\" (65\" - 73\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 130 - 180 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setBaseSpeed(6);
		
		Utils.print("Adding low-light vision to senses.");
		pc.addSense(new Sense(SenseType.LOWLIGHT_VISION));
		
		pc.addLanguage("Common");
		pc.addLanguage("Elven");
		
		Utils.print("As an Eladrin, you get +2 Arcana and History.");
		Skill arcana = pc.getSkill(SkillType.ARCANA);
		arcana.setMisc(arcana.getMisc()+2);
		
		Skill history = pc.getSkill(SkillType.HISTORY);
		history.setMisc(history.getMisc()+2);
		
		Utils.print("As an Eladrin, you get to choose one extra skill.");
		
		List<String> choices = new ArrayList<String>();
		choices.add("Acrobatics");
		choices.add("Arcana");
		choices.add("Athletics");
		choices.add("Bluff");
		choices.add("Diplomacy");
		choices.add("Dungeoneering");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("History");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Nature");
		choices.add("Perception");
		choices.add("Religion");
		choices.add("Stealth");
		choices.add("Streetwise");
		choices.add("Thievery");
		
		Utils.print("Choose one of the following");
		Utils.printValidStringChoices(choices);
		Utils.print("Your choice:");
		String s = Utils.getValidInput(choices);

		Skill skill = null;
		if ("Acrobatics".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.ACROBATICS);
		} else if ("Arcana".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.ARCANA);
		} else if ("Athletics".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.ATHLETICS);
		} else if ("Bluff".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.BLUFF);
		} else if ("Diplomacy".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.DIPLOMACY);
		} else if ("Dungeoneering".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.DUNGEONEERING);
		} else if ("Endurance".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.ENDURANCE);
		} else if ("Heal".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.HEAL);
		} else if ("History".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.HISTORY);
		} else if ("Insight".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.INSIGHT);
		} else if ("Intimidate".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.INTIMIDATE);
		} else if ("Nature".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.NATURE);
		} else if ("Perception".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.PERCEPTION);
		} else if ("Religion".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.RELIGION);
		} else if ("Stealth".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.STEALTH);
		} else if ("Streetwise".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.STREETWISE);
		} else if ("Thievery".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.THIEVERY);
		} else {
			Utils.print("ERROR!!!!! There was a skill selected that I don't understand: " + s);
		}
		if (skill != null) {
			skill.setTrained(true);
		}
		Utils.print("PLEASE MAKE NOTE OF THE SELECTION YOU MADE, AS THE CLASS SELECTIONS MAY OFFER YOU THE SAME CHOICE AGAIN.");
		Utils.print("Unless I coded it to figure it out, you don't want to choose the same thing twice.");

		Utils.print("As an Eladrin, you get a weapon proficiency with the longsword.");
		
		pc.addWeaponTypeProficiency(WeaponType.LONGSWORD);

		Utils.print("As an Eladrin, you get a +1 bonus to your will defense.");
		setWillBonus(getWillBonus()+1);
		
		// TODO: +5 racial bonus to charm effects, Fey Origin, Trance, Fey Step
		Utils.print("NOTE: I have not yet coded +5 racial bonus to charm effects, Fey Origin, Trance, Fey Step.");
	
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As an Eladrin you get +2 to Dexterity and Intelligence.");
		setDexterityBonus(getDexterityBonus()+2);
		setIntelligenceBonus(getIntelligenceBonus()+2);		
	}

}
