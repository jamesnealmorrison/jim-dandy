package com.jimmie.util;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.creatures.DndCharacter;

public class RestUtil {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RestUtil restUtil = new RestUtil();
		
		restUtil.run();
	}
	
	protected List<DndCharacter> characters;
	
	private void run() {
		DndCharacter percian = Utils.loadCharacter("Percian");
		DndCharacter gamal = Utils.loadCharacter("Gamal");
		DndCharacter keothi = Utils.loadCharacter("Keothi");
		DndCharacter travok = Utils.loadCharacter("Travok");
		DndCharacter hazel = Utils.loadCharacter("Hazel");
		
Utils.print("Surge Uses");
Utils.print("Percian: " + percian.getCurrentSurgeUses());
Utils.print("Gamal: " + gamal.getCurrentSurgeUses());
Utils.print("Keothi: " + keothi.getCurrentSurgeUses());
Utils.print("Travok: " + travok.getCurrentSurgeUses());
Utils.print("Hazel: " + hazel.getCurrentSurgeUses());
		
		characters = new ArrayList<DndCharacter>();
		characters.add(percian);
		characters.add(gamal);
		characters.add(keothi);
		characters.add(travok);
		characters.add(hazel);
		
		Utils.print("Do you want to take an:");
		Utils.print("1. Extended Rest");
		Utils.print("2. Short Rest");
		
		int choice = Utils.getValidIntInputInRange(1,2);
		if (choice == 1) {
			/* Extended Rest. */
			for (DndCharacter c : characters) {
				c.setCurrentHitPoints(c.getMaxHitPoints());
				Utils.print(c.getName());
				c.setActionPoints(1);
				c.setCurrentSurgeUses(0);
				c.initializeForEncounter();
				c.initializeForNewDay();
				c.setTemporaryHitPoints(0);
			}
		} else {
			Utils.print("Jim, do the characters get another action point (i.e. have they completed two encounters without an extended rest)?");
			String actionPoint = Utils.getYesOrNoInput();
			for (DndCharacter c : characters) {
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
		for (DndCharacter c : characters) {
			// Also, remove temporary effects
			c.setTemporaryEffects(new ArrayList<TemporaryEffect>());
			Utils.saveCharacter(c);
		}
	}
}
