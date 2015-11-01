package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.util.Utils;

public class Shifter extends Race {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shifterType;

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
	public void processAfterHurtEffects(Creature creature) {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeRaceChoices(PlayerCharacter pc, DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Ash, Brook, Claw, Cliff, Flint, Frost, River, Rock, Storm, Thorn, Tor");
		Utils.print("Female Names: Aurora, Autumn, Dawn, Hazel, Iris, Lily, Rain, Rose, Summer");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 5' 7\" - 6' 0\" (67\" - 72\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 130 - 180 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setSpeed(6);
		
		Utils.print("Adding low-light vision to senses.");
		pc.addSense(new Sense(SenseType.LOWLIGHT_VISION));
		
		pc.addLanguage("Common");
		// TODO: Language choice.
		Utils.print("NOTE: I have not yet coded the choice of a language.");

		Utils.print("You must choose between a Longtooth Shifter and a Razorclaw Shifter.");
		Utils.print("1. Longtooth Shifter");
		Utils.print("2. Razorclaw Shifter");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		
		if (choice == 1) {
			shifterType = "Longtooth";
			Utils.print("As a Longtooth Shifter you get +2 to Strength and Wisdom.");
			pc.setStrength(pc.getStrength() + 2);
			pc.setWisdom(pc.getWisdom() + 2);
			
			
			Utils.print("As a Longtooth Shifter, you get +2 Athletics and Endurance");
			Skill athletics = pc.getSkill(SkillType.ATHLETICS);
			athletics.setMisc(athletics.getMisc()+2);
			
			Skill endurance = pc.getSkill(SkillType.ENDURANCE);
			endurance.setMisc(endurance.getMisc()+2);
			
			// TODO: Longtooth Shifting
			Utils.print("NOTE: I have not yet coded the Longtooth Shifting.");
		} else {
			shifterType = "Razorclaw";
			Utils.print("As a Razorclaw Shifter you get +2 to Dexterity and Wisdom.");
			pc.setDexterity(pc.getDexterity() + 2);
			pc.setWisdom(pc.getWisdom() + 2);
			
			
			Utils.print("As a Razorclaw Shifter, you get +2 Athletics and Stealth");
			Skill athletics = pc.getSkill(SkillType.ATHLETICS);
			athletics.setMisc(athletics.getMisc()+2);
			
			Skill stealth = pc.getSkill(SkillType.STEALTH);
			stealth.setMisc(stealth.getMisc()+2);
			
			// TODO: Razorclaw Shifting
			Utils.print("NOTE: I have not yet coded the Razorclaw Shifting.");
		}
	}

}
