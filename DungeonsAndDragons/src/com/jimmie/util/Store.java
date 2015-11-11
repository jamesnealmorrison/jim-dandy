package com.jimmie.util;

import java.util.HashMap;
import java.util.List;

import com.jimmie.domain.NotEnoughCurrencyException;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.Backpack;
import com.jimmie.domain.items.Bedroll;
import com.jimmie.domain.items.BeltPouch;
import com.jimmie.domain.items.FlintAndSteel;
import com.jimmie.domain.items.Gear;
import com.jimmie.domain.items.GearType;
import com.jimmie.domain.items.GrapplingHook;
import com.jimmie.domain.items.Hammer;
import com.jimmie.domain.items.HempenRope;
import com.jimmie.domain.items.Pitons;
import com.jimmie.domain.items.Sunrods;
import com.jimmie.domain.items.TrailRations;
import com.jimmie.domain.items.Waterskin;
import com.jimmie.domain.items.armor.Armor;
import com.jimmie.domain.items.armor.Shield;
import com.jimmie.domain.items.weapons.Weapon;

public abstract class Store {

	public void shop(PlayerCharacter pc) {
		Utils.print("Welcome to the store.");
		boolean done = false;

		while (!done) {
			Utils.printCoins(pc);
			Utils.print("What do you want to look at now?");
			Utils.print("1. Armor");
			Utils.print("2. Weapons");
			Utils.print("3. Gear");
			Utils.print("Your choice:");
			int typeChoice = Utils.getValidIntInputInRange(1, 3);
			if (typeChoice == 1) {
				buyArmor(pc);
			} else if (typeChoice == 2) {
				buyWeapons(pc);
			} else {
				buyGear(pc);
			}
			
			if (pc.getCoins().valueInCopperPieces() == 0) {
				Utils.print("You are out of money now.");
				done = true;
			} else {
				Utils.print("Are you done spending money?");
				String choice = Utils.getYesOrNoInput();
				if ("Y".equalsIgnoreCase(choice)) {
					done = true;
				} else {
					done = false;
				}
			}
		}
	}

	private void buyGear(PlayerCharacter pc) {
		Utils.print("Please select an item to purchase:");
		
		List<Gear> availableGear = getGear();
		HashMap<Integer, Gear> gearChoices = new HashMap<Integer, Gear>();
		int i = 0;
		for (Gear gear : availableGear) {
			i++;
			Utils.print(i + ". " + gear.getGearType() + ": " + gear.getPrice().getAmount() + " " + gear.getPrice().getCoinType());
			gearChoices.put(i, gear);
		}
		
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, i);
		Gear chosenGear = gearChoices.get(choice);
		
		try {
			pc.spendCoins(chosenGear.getPrice());
			// Check for gear kits
			if (chosenGear.getGearType() == GearType.STANDARD_ADVENTURERS_KIT) {
				pc.addGear(new Backpack());
				pc.addGear(new Bedroll());
				pc.addGear(new FlintAndSteel());
				pc.addGear(new BeltPouch());
				pc.addGear(new TrailRations());
				pc.addGear(new HempenRope());
				pc.addGear(new Sunrods());
				pc.addGear(new Waterskin());
			} else if (chosenGear.getGearType() == GearType.CLIMBERS_KIT) {
				pc.addGear(new GrapplingHook());
				pc.addGear(new Hammer());
				pc.addGear(new Pitons());				
			} else {
				pc.addGear(chosenGear);				
			}
		} catch (NotEnoughCurrencyException e) {
			Utils.print("Sorry, you do not have enough currency to purchase that item.");
		}
	}

	private void buyWeapons(PlayerCharacter pc) {
		Utils.print("Please select a weapon to purchase:");
		Utils.print("Please note that the following choices do NOT take into consideration what your character can actually use.  Choose wisely.");
		
		List<Weapon> availableWeapons = getWeapons();
		HashMap<Integer, Weapon> weaponChoices = new HashMap<Integer, Weapon>();
		int i = 0;
		for (Weapon weapon : availableWeapons) {
			i++;
			Utils.print(i + ". " + weapon.getWeaponType() + ": " + weapon.getPrice().getAmount() + " " + weapon.getPrice().getCoinType());
			weaponChoices.put(i, weapon);
		}
		
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, i);
		Weapon chosenWeapon = weaponChoices.get(choice);
		
		try {
			pc.spendCoins(chosenWeapon.getPrice());
			pc.addWeapon(chosenWeapon);
		} catch (NotEnoughCurrencyException e) {
			Utils.print("Sorry, you do not have enough currency to purchase that item.");
		}
	}

	private void buyArmor(PlayerCharacter pc) {
		Utils.print("Please select some armor to purchase:");
		Utils.print("Please note that the following choices do NOT take into consideration what your character can actually use.  Choose wisely.");
		
		List<Armor> availableArmor = getArmor();
		HashMap<Integer, Armor> armorChoices = new HashMap<Integer, Armor>();
		int i = 0;
		for (Armor armor : availableArmor) {
			i++;
			Utils.print(i + ". " + armor.getArmorType() + ": " + armor.getPrice().getAmount() + " " + armor.getPrice().getCoinType());
			armorChoices.put(i, armor);
		}
		
		List<Shield> availableShields = getShields();
		HashMap<Integer, Shield> shieldChoices = new HashMap<Integer, Shield>();
		int shieldChoicesStart = i+1;
		for (Shield shield : availableShields) {
			i++;
			Utils.print(i + ". " + shield.getShieldType() + ": " + shield.getPrice().getAmount() + " " + shield.getPrice().getCoinType());
			shieldChoices.put(i, shield);
		}
		
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, i);
		if (choice < shieldChoicesStart) {
			Armor chosenArmor = armorChoices.get(choice);
		
			try {
				pc.spendCoins(chosenArmor.getPrice());
				pc.addArmor(chosenArmor);
			} catch (NotEnoughCurrencyException e) {
				Utils.print("Sorry, you do not have enough currency to purchase that item.");
			}
		} else {
			Shield chosenShield = shieldChoices.get(choice);
			
			try {
				pc.spendCoins(chosenShield.getPrice());
				pc.addShield(chosenShield);
			} catch (NotEnoughCurrencyException e) {
				Utils.print("Sorry, you do not have enough currency to purchase that item.");
			}
		}
	}

	public abstract List<Armor> getArmor();

	public abstract List<Shield> getShields();

	public abstract List<Weapon> getWeapons();

	public abstract List<Gear> getGear();
}
