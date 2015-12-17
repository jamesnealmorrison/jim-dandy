package com.jimmie.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;

import com.jimmie.domain.CombatAdvantageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.TemporaryCombatAdvantage;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Race;
import com.jimmie.encounters.Encounter;
import com.jimmie.gui.DungeonGUI;
import com.jimmie.domain.creatures.DndCharacter;

public class Utils {
	static IntegratedCommandConsole icc = null;
	static DungeonGUI gui = null;

	public static void setGui(DungeonGUI gui) {
		Utils.gui = gui;
	}

	public static void setICC(IntegratedCommandConsole i)
	{
		icc = i;
	}

	public static String getInput()
	{
		if (icc != null)
		{
			return icc.getInput();
		} else {
			byte byteInput[] = new byte[256];
			int length;
			String input;

			try
			{
				length = System.in.read(byteInput);
				/* length - 2 because length includes the newline/carriage return */
				input = new String(byteInput, 0, length - 2);
				return input;
			}
			catch(IOException e)
			{
				Utils.print("HUH?");
				return "ERROR";
			}
		}
	}

	public static int parseInt(String input)
	{
		int temp;
		try
		{
			temp = Integer.parseInt(input);
			return temp;
		}
		catch(NumberFormatException e)
		{
			Utils.print("Not a number, dork!  Setting to 0.");
			return 0;
		}
	}

	public static int getInt()
	{
		return parseInt(getInput());
	}

	public static String getValidInput(List<String> validInputs)
	{
		boolean found = false;
		String input = new String();
		String validInput = new String();
		Iterator<String> i;

		if ((validInputs == null) || (validInputs.size() == 0)) {
			Utils.print("No valid Inputs.  Setting to null.  It's your own fault if you get a null pointer exception.");
			return null;
		}
		do
		{
			input = getInput();
			i = validInputs.iterator();

			while(i.hasNext())
			{
				validInput = (String) i.next();

				if(validInput.compareToIgnoreCase(input) == 0)
				{
					found = true;
					break;
				}
			}
			if(found == true)
			{
				return validInput;
			}
			else
			{
				Utils.print("Didn't understand your input.  Please try again.");
			}
		} while(found == false);

		return null;
	}

	public static String getYesOrNoInput()
	{
		if (icc != null)
		{
			return icc.getYesOrNoInput();
		}
		else
		{

			String input = new String();

			do
			{
				input = getInput();

				if("Y".compareToIgnoreCase(input) == 0)
				{
					return "Y";
				}
				if("N".compareToIgnoreCase(input) == 0)
				{
					return "N";
				}

				Utils.print("Didn't understand your input.  Please enter Y or N.");
			} while(true);
		}
	}

	public static Integer getValidIntInput(List<Integer> choices)
	{
		boolean found = false;
		int input;
		int validInput = 0;
		Iterator<Integer> i;

		if ((choices == null) || (choices.size() == 0)) {
			Utils.print("No valid Inputs.  Setting to null.  It's your own fault if you get a null pointer exception.");
			return null;
		}
		do
		{
			input = getInt();
			i = choices.iterator();

			while(i.hasNext())
			{
				validInput = ((Integer) i.next()).intValue();

				if(validInput == input)
				{
					found = true;
					break;
				}
			}
			if(found == true)
			{
				return(new Integer(validInput));
			}
			else
			{
				Utils.print("Didn't understand your input.  Please try again.");
			}
		} while(found == false);

		return null;
	}

	/* Get a valid integer input within a certain range (inclusive) */
	public static int getValidIntInputInRange(int start, int end)
	{
		if ((start > end) || (end == 0)) {
			Utils.print("No valid Inputs.  Setting to 0.");
			return 0;
		}

		if (icc != null)
		{
			return icc.getValidIntInputInRange(start, end);
		}
		else
		{

			boolean found = false;
			int input;

			do
			{
				input = getInt();

				if((input >= start) && (input <= end))
				{
					found = true;
				}

				if(found == true)
				{
					return(input);
				}
				else
				{
					Utils.print("Didn't understand your input.  Please try again.");
				}
			} while(found == false);

			return 0;
		}
	}

	/*
	 * public static void pickSpecialty(Skill skill) { // If this skill has
	 * specialties, and one hasn't already been picked if
	 * (((skill.getAllSpecialties() != null) &&
	 * !(skill.getAllSpecialties().isEmpty())) && ((skill.getSpecialties() ==
	 * null) || (skill.getSpecialties().size() == 0))) { pickSpecialty(skill); } }
	 */
	public static void print(String s)
	{
		if (icc != null)
		{
			icc.print(s);
		}
		else
		{
			System.out.println(s);
		}

		if (gui != null)
		{
			gui.refreshAll();
		}

	}

	public static int rollForDamage(int damageRolls, DiceType damageDiceType,
			int weaponDamageBonus, int attributeModifier, Race race) {
		int rollTotal = 0;
		Dice damageDice = new Dice(damageDiceType);
		for (int index = 0; index < damageRolls; index++) {
			int currentRoll = damageDice.roll();
			Utils.print("Rolled a " + currentRoll + " for damage.");
			rollTotal = rollTotal + currentRoll;
			Utils.print("Total so far = " + rollTotal);
		}
		Utils.print("Adding weapon bonus of " + weaponDamageBonus);
		rollTotal = rollTotal + weaponDamageBonus;
		Utils.print("Adding attribute bonus of " + attributeModifier);
		rollTotal = rollTotal + attributeModifier;
		int raceBonus = 0;
		if (race != null) {
			raceBonus = race.getRacialDamageBonus();
		}
		Utils.print("Adding racial bonus of " + raceBonus);
		rollTotal = rollTotal + raceBonus;
		Utils.print("Doing " + rollTotal + " worth of damage.");

		return rollTotal;
	}

	public static int rollForHalfDamage(int damageRolls, DiceType damageDiceType,
			int weaponDamageBonus, int attributeModifier, Race race) {
		int rollTotal = 0;
		Dice damageDice = new Dice(damageDiceType);
		for (int index = 0; index < damageRolls; index++) {
			int currentRoll = damageDice.roll();
			Utils.print("Rolled a " + currentRoll + " for damage.");
			rollTotal = rollTotal + currentRoll;
			Utils.print("Total so far = " + rollTotal);
		}
		Utils.print("Adding weapon bonus of " + weaponDamageBonus);
		rollTotal = rollTotal + weaponDamageBonus;
		Utils.print("Adding attribute bonus of " + attributeModifier);
		rollTotal = rollTotal + attributeModifier;
		int raceBonus = 0;
		if (race != null) {
			raceBonus = race.getRacialDamageBonus();
		}
		Utils.print("Adding racial bonus of " + raceBonus);
		rollTotal = rollTotal + raceBonus;
		Utils.print("Doing " + rollTotal + " worth of damage.");

		return rollTotal / 2;
	}

	public static boolean hasCombatAdvantage(Creature source,
			Creature target, Encounter encounter) {
		/* Does the target have a condition that grants combat advantage? */
		// TODO: Prone says only melee attacks.
		if (target.isBlinded() || target.isDazed() || target.isDominated() || target.isDying() || target.isHelpless() || target.isProne() || target.isRestrained()
				|| target.isStunned() || target.isSurprised() || target.isUnconscious()) {
			return true;
		}

		/* Is the source creature flanking the target? */
		if (Utils.isFlanking(source, target, encounter)) {
			return true;
		}

		/* Am I invisible to the target? */
		if (source.isInvisibleTo(target)) {
			return true;
		}
		
		for (Iterator<TemporaryEffect> it = target.getTemporaryEffects().iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();
			if (TemporaryCombatAdvantage.class.isAssignableFrom(tempEffect.getClass())) {
				TemporaryCombatAdvantage temporaryCombatAdvantage = (TemporaryCombatAdvantage) tempEffect;

				if  (temporaryCombatAdvantage.stillApplies()) {
					// Warden's Fury combat advantage applies to everyone.  For other types I may need to check who has the combat advantage.
					if (CombatAdvantageType.WARDENS_FURY == temporaryCombatAdvantage.getTypeOfCombatAdvantage()) {
						Utils.print("Granting combat advantage because of Warden's Fury");
						return true;
					}
				} else {
					Utils.print("Temporary Combat Advantage no longer applies. Removing.");
					it.remove();
				}
			}
		}

		return false;
	}

	private static boolean isFlanking(Creature source, Creature target,
			Encounter encounter) {
		/* First thing to do is find out if the source is adjacent to the target. */
		if (source.isAdjacentTo(target)) {
			/* Get all the other adjacent allies.  PLEASE NOTE: I'm calling the "getAdjacentEnemies method on purpose.
			 * It finds enemies of the TARGET, which would be MY allies!!!!!!!! */
			List<Creature> adjacentAllies = encounter.getAdjacentEnemies(target);
			for (Creature adjacentAlly : adjacentAllies) {
				if (adjacentAlly.canFlank()) {
					if ((source.getCurrentPosition().isNorthOf(target.getCurrentPosition())) && (adjacentAlly.getCurrentPosition().isSouthOf(target.getCurrentPosition()))) {
						return true;
					}
					if ((source.getCurrentPosition().isNorthEastOf(target.getCurrentPosition())) && (adjacentAlly.getCurrentPosition().isSouthWestOf(target.getCurrentPosition()))) {
						return true;
					}
					if ((source.getCurrentPosition().isEastOf(target.getCurrentPosition())) && (adjacentAlly.getCurrentPosition().isWestOf(target.getCurrentPosition()))) {
						return true;
					}
					if ((source.getCurrentPosition().isSouthEastOf(target.getCurrentPosition())) && (adjacentAlly.getCurrentPosition().isNorthWestOf(target.getCurrentPosition()))) {
						return true;
					}
					if ((source.getCurrentPosition().isSouthOf(target.getCurrentPosition())) && (adjacentAlly.getCurrentPosition().isNorthOf(target.getCurrentPosition()))) {
						return true;
					}
					if ((source.getCurrentPosition().isSouthWestOf(target.getCurrentPosition())) && (adjacentAlly.getCurrentPosition().isNorthEastOf(target.getCurrentPosition()))) {
						return true;
					}
					if ((source.getCurrentPosition().isWestOf(target.getCurrentPosition())) && (adjacentAlly.getCurrentPosition().isEastOf(target.getCurrentPosition()))) {
						return true;
					}
					if ((source.getCurrentPosition().isNorthWestOf(target.getCurrentPosition())) && (adjacentAlly.getCurrentPosition().isSouthEastOf(target.getCurrentPosition()))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void saveCharacter(DndCharacter c) {
		String fileName = c.getName();
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(c);
			out.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static DndCharacter loadCharacter(String filename) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		DndCharacter c = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			c = (DndCharacter) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	public static void printValidChoices(List<Integer> choices) {
		for (Integer i : choices) {
			print(i.toString());
		}
	}

	public static int whichChoice(int choice, List<Integer> choices) {
		for (int i = 0; i < choices.size(); i++) {
			if (choice == choices.get(i)) {
				return i;
			}
		}
		return 0;
	}

	public static void printValidStringChoices(List<String> choices) {
		for (String s : choices) {
			print(s);
		}
	}

	public static void printCoins(DndCharacter c) {
		print(c.getName() + " has the following coins:");
		print("Copper pieces:   " + c.getCoins().getCopperPieces());
		print("Silver pieces:   " + c.getCoins().getSilverPieces());
		print("Gold pieces:     " + c.getCoins().getGoldPieces());
		print("Platinum pieces: " + c.getCoins().getPlatinumPieces());
		print("Astral diamonds: " + c.getCoins().getAstralDiamonds());
	}
}
