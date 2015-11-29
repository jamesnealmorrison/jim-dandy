package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.util.Utils;

public class Dragonborn extends Race {

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
	public void processAfterHurtEffects(Creature creature) {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeRaceChoices(PlayerCharacter pc, DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Arjhan, Balasar, Bharash, Donaar, Ghesh, Heskan, Kriv, Medrash, Nadarr, Patrin, Rhogar, Shamash, Shedinn, Torinn");
		Utils.print("Female Names: Akra, Biri, Daar, Harann, Kava, Korinn, Mishann, Nala, Perra, Raiann, Sora, Surina, Thava");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 6' 2\" - 6' 8\" (74\" - 80\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 220 - 320 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setBaseSpeed(6);
		
		Utils.print("Adding normal vision to senses.");
		pc.addSense(new Sense(SenseType.NORMAL_VISION));
		
		pc.addLanguage("Common");
		pc.addLanguage("Draconic");
		
		Utils.print("As a Dragonborn, you get +2 History and Intimidate");
		Skill history = pc.getSkill(SkillType.HISTORY);
		history.setMisc(history.getMisc()+2);
		
		Skill intimidate = pc.getSkill(SkillType.INTIMIDATE);
		intimidate.setMisc(intimidate.getMisc()+2);
		
		// TODO Implement Dragonborn fury and draconic heritage.
		Utils.print("NOTE: I have not yet coded the dragonborn fury and draconic heritage.");
		
		
		// TODO Choices for dragon breath.
		Utils.print("NOTE: I have not yet coded the choices for dragon breath.");
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As a Dragonborn you get +2 to Strength and Charisma.");
		setStrengthBonus(getStrengthBonus()+2);
		setCharismaBonus(getCharismaBonus()+2);		
	}

}
