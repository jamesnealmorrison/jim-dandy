package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.util.Utils;

public class Human extends Race {

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
		Utils.print("Male Names: Alain, Alek, Benn, Brandis, Donn, Drew, Erik, Gregg, Jonn, Kris, Marc, Mikal, Pieter, Regdar, Quinn, Samm, Thom, Wil");
		Utils.print("Female Names: Ana, Cassi, Eliza, Gwenn, Henn, Kat, Keira, Luusi, Mari, Mika, Miri, Stasi, Shawna, Zanne");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 5' 6\" - 6' 2\" (66\" - 74\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 135 - 220 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setBaseSpeed(6);
		
		Utils.print("Adding normal vision to senses.");
		pc.addSense(new Sense(SenseType.NORMAL_VISION));
		
		pc.addLanguage("Common");

		/* TODO: Language choice. */
		Utils.print("NOTE: I have not yet coded the language choice for humans.");
		

		/* TODO: Bonus at will power. */
		Utils.print("NOTE: I have not yet coded the bonus at will power for humans.");
		
		Utils.print("NOTE: The extra feat for humans will be chosen later in the process because you must choose class features first.");
		
		/* TODO: Bonus skill. */
		Utils.print("NOTE: I have not yet coded the bonus skill from your class.");
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		/* Humans get to add +2 to one ability score of their choice. */
		Utils.print("As a human, you get to add +2 to one ability score of your choice.");
		Utils.print("1. Strength");
		Utils.print("2. Constitution");
		Utils.print("3. Dexterity");
		Utils.print("4. Intelligence");
		Utils.print("5. Wisdom");
		Utils.print("6. Charisma");
		Utils.print("Your choice: ");
		int choice = Utils.getValidIntInputInRange(1, 6);
		
		switch (choice) {
		case 1 :
			setStrengthBonus(getStrengthBonus()+2);
			break;
		case 2 :
			setConstitutionBonus(getConstitutionBonus()+2);
			break;
		case 3 :
			setDexterityBonus(getDexterityBonus()+2);
			break;
		case 4 :
			setIntelligenceBonus(getIntelligenceBonus()+2);
			break;
		case 5 :
			setWisdomBonus(getWisdomBonus()+2);
			break;
		case 6 :
			setCharismaBonus(getCharismaBonus()+2);
			break;
		}
	}
}
