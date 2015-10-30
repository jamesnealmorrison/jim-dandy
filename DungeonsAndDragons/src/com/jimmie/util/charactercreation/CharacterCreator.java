package com.jimmie.util.charactercreation;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.DiceType;
import com.jimmie.domain.classes.Ardent;
import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.classes.Barbarian;
import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.classes.Battlemind;
import com.jimmie.domain.classes.Cleric;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.classes.Fighter;
import com.jimmie.domain.classes.Invoker;
import com.jimmie.domain.classes.Monk;
import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.classes.Ranger;
import com.jimmie.domain.classes.Rogue;
import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.classes.Seeker;
import com.jimmie.domain.classes.Shaman;
import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.classes.Warlock;
import com.jimmie.domain.classes.Warlord;
import com.jimmie.domain.classes.Wizard;
import com.jimmie.domain.creatures.Deva;
import com.jimmie.domain.creatures.Dragonborn;
import com.jimmie.domain.creatures.Dwarf;
import com.jimmie.domain.creatures.Eladrin;
import com.jimmie.domain.creatures.Elf;
import com.jimmie.domain.creatures.Githzerai;
import com.jimmie.domain.creatures.Gnome;
import com.jimmie.domain.creatures.Goliath;
import com.jimmie.domain.creatures.HalfElf;
import com.jimmie.domain.creatures.HalfOrc;
import com.jimmie.domain.creatures.Halfling;
import com.jimmie.domain.creatures.Human;
import com.jimmie.domain.creatures.Minotaur;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Race;
import com.jimmie.domain.creatures.Shardmind;
import com.jimmie.domain.creatures.Shifter;
import com.jimmie.domain.creatures.Tiefling;
import com.jimmie.domain.creatures.Wilden;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class CharacterCreator {

	public static void main(String[] args) {
		CharacterCreator creator = new CharacterCreator();
		creator.run();
	}

	private void run() {
		Race race = chooseRace();
		DndClass dndClass = chooseClass(race);

		PlayerCharacter pc = new PlayerCharacter(race, dndClass);

		generateAbilityScores(pc);

		//Utils.saveCharacter(pc);
	}

	private void generateAbilityScores(PlayerCharacter pc) {
		Utils.print("Time to generate ability scores.");
		Utils.print("Choose which method you want to use:");
		Utils.print("1. Standard Array");
		Utils.print("2. Customizing Scores");
		Utils.print("3. Rolling Scores");
		Utils.print("Your choice?");

		int choice = Utils.getValidIntInputInRange(1, 3);
		switch (choice) {
		case 1 :
			standardArray(pc);
			break;
		case 2 :
			customizingScores(pc);
			break;
		case 3 :
			rollingScores(pc);
			break;
		}
	}

	private void rollingScores(PlayerCharacter pc) {
		Utils.print("Do you want to manually roll the dice yourself or have the computer roll for you?");
		Utils.print("1. Manual");
		Utils.print("2. Computer");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		Dice.setRollType(choice);
		List<Integer> choices = null;
		Dice dice = new Dice(DiceType.SIX_SIDED);
		boolean rollsQualify = false; // Determines if the player is to powerful or weak.

		while (!rollsQualify) {
			choices = new ArrayList<Integer>();
			for (int i = 0; i < 6; i++) {
				int min = 7;
				int roll1 = dice.basicRoll();
				if (roll1 < min) {
					min = roll1;
				}
				int roll2 = dice.basicRoll();
				if (roll2 < min) {
					min = roll2;
				}
				int roll3 = dice.basicRoll();
				if (roll3 < min) {
					min = roll3;
				}
				int roll4 = dice.basicRoll();
				if (roll4 < min) {
					min = roll4;
				}
				choices.add(roll1+roll2+roll3+roll4-min);
			}



			Utils.print("Which one do you want to assign to Strength?");
			Utils.printValidChoices(choices);
			Utils.print("Your choice?");
			choice = Utils.getValidIntInput(choices);
			pc.setStrength(choice);
			choices.remove(Utils.whichChoice(choice, choices));

			Utils.print("Which one do you want to assign to Constitution?");
			Utils.printValidChoices(choices);
			Utils.print("Your choice?");
			choice = Utils.getValidIntInput(choices);
			pc.setConstitution(choice);
			choices.remove(Utils.whichChoice(choice, choices));

			Utils.print("Which one do you want to assign to Dexterity?");
			Utils.printValidChoices(choices);
			Utils.print("Your choice?");
			choice = Utils.getValidIntInput(choices);
			pc.setDexterity(choice);
			choices.remove(Utils.whichChoice(choice, choices));

			Utils.print("Which one do you want to assign to Intelligence?");
			Utils.printValidChoices(choices);
			Utils.print("Your choice?");
			choice = Utils.getValidIntInput(choices);
			pc.setIntelligence(choice);
			choices.remove(Utils.whichChoice(choice, choices));

			Utils.print("Which one do you want to assign to Wisdom?");
			Utils.printValidChoices(choices);
			Utils.print("Your choice?");
			choice = Utils.getValidIntInput(choices);
			pc.setWisdom(choice);
			choices.remove(Utils.whichChoice(choice, choices));

			Utils.print("Assiging " + choices.get(0) + " to Charisma.");
			pc.setStrength(choices.get(0));
			
			int totalAbilityModifiers = pc.getStrengthModifier() + pc.getDexterityModifier() + pc.getConstitutionModifier() + pc.getIntelligenceModifier()
					+ pc.getWisdomModifier() + pc.getCharismaModifier();
			if (totalAbilityModifiers >= 4 && totalAbilityModifiers <= 8) {
				rollsQualify = true;
			} else {
				Utils.print("Your total ability modifiers = " + totalAbilityModifiers + ". This character is too weak or strong to play the game.");
				Utils.print("Roll again.");
			}
		}
	}

	private void customizingScores(PlayerCharacter pc) {
		Utils.print("With this method, you will start by assigning 8, 10, 10, 10, 10, 10 to your abilities.  You will then spend points to improve them.");
		pc.setStrength(10);
		pc.setConstitution(10);
		pc.setDexterity(10);
		pc.setIntelligence(10);
		pc.setWisdom(10);
		pc.setCharisma(10);
		Utils.print("Which ability will you assign the 8 to? (The rest will be assigned 10's)");
		Utils.print("1. Strength");
		Utils.print("2. Constitution");
		Utils.print("3. Dexterity");
		Utils.print("4. Intelligence");
		Utils.print("5. Wisdom");
		Utils.print("6. Charisma");
		int choice = Utils.getValidIntInputInRange(1, 6);
		switch (choice) {
		case 1 :
			pc.setStrength(8);
			break;
		case 2 :
			pc.setConstitution(8);
			break;
		case 3 :
			pc.setDexterity(8);
			break;
		case 4 :
			pc.setIntelligence(8);
			break;
		case 5 :
			pc.setWisdom(8);
			break;
		case 6 :
			pc.setCharisma(8);
			break;
		}

		int pointsToSpend = 22;
		List<Integer> choices = null;
		while (pointsToSpend > 0) {
			choices = new ArrayList<Integer>();
			Utils.print("");
			Utils.print("Here are your current ability scores.");
			if (pc.getStrength() <= 10) {
				Utils.print("1. Strength: " + pc.getStrength());
				choices.add(1);
			} else {
				Utils.print("Strength: " + pc.getStrength() + ". Can't update again.");
			}
			if (pc.getConstitution() <= 10) {
				Utils.print("2. Constitution: " + pc.getConstitution());
				choices.add(2);
			} else {
				Utils.print("Constitution: " + pc.getConstitution() + ". Can't update again.");
			}
			if (pc.getDexterity() <= 10) {
				Utils.print("3. Dexterity: " + pc.getDexterity());
				choices.add(3);
			} else {
				Utils.print("Dexterity: " + pc.getDexterity() + ". Can't update again.");
			}
			if (pc.getIntelligence() <= 10) {
				Utils.print("4. Intelligence: " + pc.getIntelligence());
				choices.add(4);
			} else {
				Utils.print("Intelligence: " + pc.getIntelligence() + ". Can't update again.");
			}
			if (pc.getWisdom() <= 10) {
				Utils.print("5. Wisdom: " + pc.getWisdom());
				choices.add(5);
			} else {
				Utils.print("Wisdom: " + pc.getWisdom() + ". Can't update again.");
			}
			if (pc.getCharisma() <= 10) {
				Utils.print("6. Charisma: " + pc.getCharisma());
				choices.add(6);
			} else {
				Utils.print("Charisma: " + pc.getCharisma() + ". Can't update again.");
			}

			Utils.print("Points to spend: " + pointsToSpend);

			Utils.print("Your choice?");
			int abilityChoice = Utils.getValidIntInput(choices);
			int score = 0;

			switch (abilityChoice) {
			case 1 :
				score = pc.getStrength();
				break;
			case 2 :
				score = pc.getConstitution();
				break;
			case 3 :
				score = pc.getDexterity();
				break;
			case 4 :
				score = pc.getIntelligence();
				break;
			case 5 :
				score = pc.getWisdom();
				break;
			case 6 :
				score = pc.getCharisma();
				break;
			}

			/* If 8 or 9, they can only increase it to 9 or 10. */
			choices = new ArrayList<Integer>();
			if (score == 8) {
				Utils.print("Choose");
				if (pointsToSpend >= 1) {
					Utils.print("1. Use one point to raise this to 9.");
					choices.add(1);
				}
				if (pointsToSpend >= 2) {
					Utils.print("2. Use two points to raise this to 10.");
					choices.add(2);
				}
				Utils.print("Your choice:");
				choice = Utils.getValidIntInput(choices);
				if (choice == 1) {
					score = 9;
					pointsToSpend -= 1;
				} else {
					score = 10;
					pointsToSpend -= 2;
				}
			} else if (score == 9) {
				if (pointsToSpend >= 1) {
					Utils.print("1. You chose to spend one point to raise this to 10.");
					score = 10;
					pointsToSpend -= 1;
				}
			} else { // score must be 10 at this point.
				Utils.print("Choose");
				if (pointsToSpend >= 1) {
					Utils.print("1. Use one point to raise this to 11.");
					choices.add(1);
				}
				if (pointsToSpend >= 2) {
					Utils.print("2. Use two points to raise this to 12.");
					choices.add(2);
				}
				if (pointsToSpend >= 3) {
					Utils.print("3. Use three points to raise this to 13.");
					choices.add(3);
				}
				if (pointsToSpend >= 5) {
					Utils.print("4. Use five points to raise this to 14.");
					choices.add(4);
				}
				if (pointsToSpend >= 7) {
					Utils.print("5. Use seven points to raise this to 15");
					choices.add(5);
				}
				if (pointsToSpend >= 9) {
					Utils.print("6. Use nine points to raise this to 16");
					choices.add(6);
				}
				if (pointsToSpend >= 12) {
					Utils.print("7. Use twelve points to raise this to 17");
					choices.add(7);
				}
				if (pointsToSpend >= 16) {
					Utils.print("8. Use sixteen points to raise this to 18");
					choices.add(8);
				}
				Utils.print("Your choice:");
				choice = Utils.getValidIntInput(choices);

				switch (choice) {
				case 1 :
					score = 11;
					pointsToSpend -= 1;
					break;
				case 2 :
					score = 12;
					pointsToSpend -= 2;
					break;
				case 3 :
					score = 13;
					pointsToSpend -= 3;
					break;
				case 4 :
					score = 14;
					pointsToSpend -= 5;
					break;
				case 5 :
					score = 15;
					pointsToSpend -= 7;
					break;
				case 6 :
					score = 16;
					pointsToSpend -= 9;
					break;
				case 7 :
					score = 17;
					pointsToSpend -= 12;
					break;
				case 8 :
					score = 18;
					pointsToSpend -= 16;
					break;
				}
			}

			switch (abilityChoice) {
			case 1 :
				pc.setStrength(score);
				break;
			case 2 :
				pc.setConstitution(score);
				break;
			case 3 :
				pc.setDexterity(score);
				break;
			case 4 :
				pc.setIntelligence(score);
				break;
			case 5 :
				pc.setWisdom(score);
				break;
			case 6 :
				pc.setCharisma(score);
				break;
			}
		}
	}

	private void standardArray(PlayerCharacter pc) {
		Utils.print("With this method, you will assign the following scores to your abilities: 16, 14, 13, 12, 11, 10");
		List<Integer> choices = new ArrayList<Integer>();
		choices.add(16);
		choices.add(14);
		choices.add(13);
		choices.add(12);
		choices.add(11);
		choices.add(10);

		Utils.print("Which one do you want to assign to Strength?");
		Utils.printValidChoices(choices);
		Utils.print("Your choice?");
		int choice = Utils.getValidIntInput(choices);
		pc.setStrength(choice);
		choices.remove(Utils.whichChoice(choice, choices));

		Utils.print("Which one do you want to assign to Constitution?");
		Utils.printValidChoices(choices);
		Utils.print("Your choice?");
		choice = Utils.getValidIntInput(choices);
		pc.setConstitution(choice);
		choices.remove(Utils.whichChoice(choice, choices));

		Utils.print("Which one do you want to assign to Dexterity?");
		Utils.printValidChoices(choices);
		Utils.print("Your choice?");
		choice = Utils.getValidIntInput(choices);
		pc.setDexterity(choice);
		choices.remove(Utils.whichChoice(choice, choices));

		Utils.print("Which one do you want to assign to Intelligence?");
		Utils.printValidChoices(choices);
		Utils.print("Your choice?");
		choice = Utils.getValidIntInput(choices);
		pc.setIntelligence(choice);
		choices.remove(Utils.whichChoice(choice, choices));

		Utils.print("Which one do you want to assign to Wisdom?");
		Utils.printValidChoices(choices);
		Utils.print("Your choice?");
		choice = Utils.getValidIntInput(choices);
		pc.setWisdom(choice);
		choices.remove(Utils.whichChoice(choice, choices));

		Utils.print("Assiging " + choices.get(0) + " to Charisma.");
		pc.setStrength(choices.get(0));
	}

	private Race chooseRace() {
		Race race = null;

		Utils.print("What Race is your character?");
		Utils.print("Races from book 1:");
		Utils.print("1. Dragonborn");
		Utils.print("2. Dwarf");
		Utils.print("3. Eladrin");
		Utils.print("4. Elf");
		Utils.print("5. Half-Elf");
		Utils.print("6. Halfling");
		Utils.print("7. Human");
		Utils.print("8. Tiefling");
		Utils.print("Races from book 2:");
		Utils.print("9. Deva");
		Utils.print("10. Gnome");
		Utils.print("11. Goliath");
		Utils.print("12. Half-Orc");
		Utils.print("13. Shifter");
		Utils.print("Races from book 3:");
		Utils.print("14. Githzerai");
		Utils.print("15. Minotaur");
		Utils.print("16. Shardmind");
		Utils.print("17. Wilden");		
		Utils.print("Your choice?");

		int choice = Utils.getValidIntInputInRange(1, 17);

		switch (choice) {
		case 1 :
			race = new Dragonborn();
			break;
		case 2 :
			race = new Dwarf();
			break;
		case 3 :
			race = new Eladrin();
			break;
		case 4 :
			race = new Elf();
			break;
		case 5 :
			race = new HalfElf();
			break;
		case 6 :
			race = new Halfling();
			break;
		case 7 :
			race = new Human();
			break;
		case 8 :
			race = new Tiefling();
			break;
		case 9 :
			race = new Deva();
			break;
		case 10 :
			race = new Gnome();
			break;
		case 11 :
			race = new Goliath();
			break;
		case 12 :
			race = new HalfOrc();
			break;
		case 13 :
			race = new Shifter();
			break;
		case 14 :
			race = new Githzerai();
			break;
		case 15 :
			race = new Minotaur();
			break;
		case 16 :
			race = new Shardmind();
			break;
		case 17 :
			race = new Wilden();
			break;
		}
		return race;
	}

	private DndClass chooseClass(Race race) {
		DndClass dndClass = null;

		/* Print the books' suggested classes for that race. */
		if (Dragonborn.class.isInstance(race)) {
			Utils.print("According to the book, a Dragonborn favors the warloard, fighter, paladin, barbarian, sorcerer, warden, and ardent classes.");
		} else if (Dwarf.class.isInstance(race)) {
			Utils.print("According to the book, a Dwarf favors the paladin, cleric, fighter, barbarian, druid, invoker, shaman, warden, battlemind, and runepriest classes.");
		} else if (Eladrin.class.isInstance(race)) {
			Utils.print("According to the book, an Eladrin favors the wizard, rogue, warlord, invoker, and psion classes.");
		} else if (Elf.class.isInstance(race)) {
			Utils.print("According to the book, an Elf favors the ranger, rogue, cleric, avenger, druid, invoker, shaman, monk, runepriest, and seeker classes.");
		} else if (HalfElf.class.isInstance(race)) {
			Utils.print("According to the book, a HalfElf favors the warloard, paladin, warlock, barbarian, bard, sorcerer, ardent, and battlemind classes.");
		} else if (Halfling.class.isInstance(race)) {
			Utils.print("According to the book, a Halfling favors the rogue, ranger, warlock, barbarian, sorcerer, and ardent classes.");
		} else if (Human.class.isInstance(race)) {
			Utils.print("According to the book, a Human favors all classes.");
		} else if (Tiefling.class.isInstance(race)) {
			Utils.print("According to the book, a Tiefling favors the warlock, warlord, rogue, bard, sorcerer, ardent, battlemind, and psion classes.");
		} else if (Deva.class.isInstance(race)) {
			Utils.print("According to the book, a Deva favors the avenger, cleric, invoker, wizard, shaman, and runepriest classes.");
		} else if (Gnome.class.isInstance(race)) {
			Utils.print("According to the book, a Gnome favors the bard, sorcerer, warlock, wizard, ardent, battlemind, and psion classes.");
		} else if (Goliath.class.isInstance(race)) {
			Utils.print("According to the book, a Goliath favors the barbarian, fighter, warden, druid, battlemind, runepriest, and seeker classes.");
		} else if (HalfOrc.class.isInstance(race)) {
			Utils.print("According to the book, a HalfOrc favors the barbarian, fighter, ranger, rogue, and warden classes.");
		} else if (Shifter.class.isInstance(race)) {
			Utils.print("According to the book, a Shifter favors the druid, fighter, ranger, warden, shaman, and seeker classes.");
		} else if (Githzerai.class.isInstance(race)) {
			Utils.print("According to the book, a Githzerai favors the avenger, monk, ranger, and seeker classes.");
		} else if (Minotaur.class.isInstance(race)) {
			Utils.print("According to the book, a Minotaur favors the barbarian, fighter, warden, and runepriest classes.");
		} else if (Shardmind.class.isInstance(race)) {
			Utils.print("According to the book, a Shardmind favors the psion, wizard, and invoker classes.");
		} else if (Wilden.class.isInstance(race)) {
			Utils.print("According to the book, a Wilden favors the battlemind, druid, invoker, shaman, and seeker classes.");
		}

		/* Choose Class */
		Utils.print("What Class is your character?");
		Utils.print("Classes from book 1:");
		Utils.print("1. Cleric");
		Utils.print("2. Fighter");
		Utils.print("3. Paladin");
		Utils.print("4. Ranger");
		Utils.print("5. Rogue");
		Utils.print("6. Warlock");
		Utils.print("7. Warlord");
		Utils.print("8. Wizard");
		Utils.print("Classes from book 2:");
		Utils.print("9. Avenger");
		Utils.print("10. Barbarian");
		Utils.print("11.Bard");
		Utils.print("12. Druid");
		Utils.print("13. Invoker");
		Utils.print("14. Shaman");
		Utils.print("15. Sorcerer");
		Utils.print("16. Warden");
		Utils.print("Classes from book 3:");
		Utils.print("17. Ardent");
		Utils.print("18. Battlemind");
		Utils.print("19. Monk");
		Utils.print("20. Psion");
		Utils.print("21. Runepriest");
		Utils.print("22. Seeker");

		Utils.print("Your choice?");
		int choice = Utils.getValidIntInputInRange(1, 22);

		switch (choice) {
		case 1 :
			dndClass = new Cleric();
			break;
		case 2 :
			dndClass = new Fighter();
			break;
		case 3 :
			dndClass = new Paladin();
			break;
		case 4 :
			dndClass = new Ranger();
			break;
		case 5 :
			dndClass = new Rogue();
			break;
		case 6 :
			dndClass = new Warlock();
			break;
		case 7 :
			dndClass = new Warlord();
			break;
		case 8 :
			dndClass = new Wizard();
			break;
		case 9 :
			dndClass = new Avenger();
			break;
		case 10 :
			dndClass = new Barbarian();
			break;
		case 11 :
			dndClass = new Bard();
			break;
		case 12 :
			dndClass = new Druid();
			break;
		case 13 :
			dndClass = new Invoker();
			break;
		case 14 :
			dndClass = new Shaman();
			break;
		case 15 :
			dndClass = new Sorcerer();
			break;
		case 16 :
			dndClass = new Warden();
			break;
		case 17 :
			dndClass = new Ardent();
			break;
		case 18 :
			dndClass = new Battlemind();
			break;
		case 19 :
			dndClass = new Monk();
			break;
		case 20 :
			dndClass = new Psion();
			break;
		case 21 :
			dndClass = new Runepriest();
			break;
		case 22 :
			dndClass = new Seeker();
			break;
		}

		return dndClass;
	}
}
