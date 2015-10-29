package com.jimmie.util;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.creatures.Character;

public class RestUtil {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RestUtil restUtil = new RestUtil();
		
		restUtil.run();
	}
	
	protected List<Character> characters;
	
	private void run() {
		Character elfAvenger = Utils.loadCharacter("Thokul Moonshadow");
		Character kellen = Utils.loadCharacter("Kellen Wordsmith");
		Character glock = Utils.loadCharacter("Glock Elmhurst");
		Character halfOrcFighter = Utils.loadCharacter("Eleak Nightraider");
		Character zanros = Utils.loadCharacter("Zanros Hawklight");
		
		characters = new ArrayList<Character>();
		characters.add(elfAvenger);
		characters.add(kellen);
		characters.add(glock);
		characters.add(halfOrcFighter);
		characters.add(zanros);
		
		Utils.print("Do you want to take an:");
		Utils.print("1. Extended Rest");
		Utils.print("2. Short Rest");
		
		int choice = Utils.getValidIntInputInRange(1,2);
		if (choice == 1) {
			/* Extended Rest. */
			for (Character c : characters) {
				c.setCurrentHitPoints(c.getMaxHitPoints());
				c.setActionPoints(1);
				c.setCurrentSurgeUses(0);
				c.initializeForEncounter();
				c.initializeForNewDay();
				c.setTemporaryHitPoints(0);
			}
		} else {
			Utils.print("Jim, do the characters get another action point (i.e. have they completed two encounters without an extended rest)?");
			String actionPoint = Utils.getYesOrNoInput();
			for (Character c : characters) {
				Utils.print(c.getName() + " currently has " + c.getCurrentHitPoints() + " out of " + c.getMaxHitPoints() + " hit points.");
				Utils.print(c.getName() + " has " + (c.getHealingSurgesPerDay() - c.getCurrentSurgeUses()) + " healing surges left and each one restores " + c.getHealingSurgeValue() + " hp.");
				Utils.print("How many do you want to use?");
				int surges = Utils.getValidIntInputInRange(0, (c.getHealingSurgesPerDay() - c.getCurrentSurgeUses()));
				for (int i = 0; i < surges; i++) {
					c.useHealingSurge();
				}
				c.initializeForEncounter();
				c.setTemporaryHitPoints(0);
				if ("Y".equalsIgnoreCase(actionPoint)) {
					c.setActionPoints(c.getActionPoints()+1);
				}
			}
		}

		/* Now save them. */
		for (Character c : characters) {
			Utils.saveCharacter(c);
		}
	}
}
