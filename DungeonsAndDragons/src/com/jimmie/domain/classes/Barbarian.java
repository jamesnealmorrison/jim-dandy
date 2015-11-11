package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.util.Utils;

public class Barbarian extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FeralMight feralMight;

	@Override
	public void initializeForEncounter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Intimidate");
		choices.add("Nature");
		choices.add("Perception");
		
		Utils.print("Choose 3 of the following");
		for (int i = 0; i < 3; i++) {
			Utils.printValidStringChoices(choices);
			Utils.print("Your choice:");
			String choice = Utils.getValidInput(choices);
			trainedSkills.add(choice);
			choices.remove(choice);
		}
		
		return trainedSkills;
	}

	@Override
	public void makeClassChoicesBeforeAbilityScores(PlayerCharacter pc) {
		Utils.print("Setting role to Striker.");
		pc.setRole(Role.STRIKER);
		
		Utils.print("Setting power Source to Primal.");
		pc.setPowerSource(PowerSource.PRIMAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Military Melee");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		
		Utils.print("Adding bonus of +2 Fortitude");
		if (pc.getFortitudeMisc1() == 0) {
			pc.setFortitudeMisc1(2);
		} else {
			pc.setFortitudeMisc2(pc.getFortitudeMisc2() + 2);
		}

		Utils.print("Setting hit points per level gained = 6");
		pc.setHitPointsPerLevelGained(6);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Rageblood Barbarian: You can withstand a great deal of physical punishment, especially in the throes of rage.");
		Utils.print("2. Thaneborn Barbarian: You use primal power to fuel your mighty rages and lend power to your presence.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Strength your primary score and Constitution your secondary score. Charisma is third.");
			Utils.print("Suggested Class Feature: Rageblood Vigor");
			Utils.print("Suggested Feat: Weapon Expertise");
			Utils.print("Suggested Skills: Athletics, Endurance, Perception");
			Utils.print("Suggested At-Will Powers: Devastating Strike, Recuperating Strike");
			Utils.print("Suggested Encounter Power: Avalanche Strike");
			Utils.print("Suggested Daily Power: Bloodhunt Rage");
		} else {
			Utils.print("Make Strength your primary score and Charisma your secondary score.  Constitution is third.");
			Utils.print("Suggested Class Feature: Thaneborn Triumph");
			Utils.print("Suggested Feat: Rising Fury");
			Utils.print("Suggested Skills: Athletics, Intimidate, Perception");
			Utils.print("Suggested At-Will Powers: Howling Strike, Pressing Strike");
			Utils.print("Suggested Encounter Power: Vault the Fallen");
			Utils.print("Suggested Daily Power: Macetail's Rage");
		}
		
		Utils.print("Choose between the following Feral Mights:");
		Utils.print("1. Rageblood Vigor.");
		Utils.print("2. Thaneborn Triumph.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setFeralMight(FeralMight.RAGEBLOOD_VIGOR);
		} else {
			setFeralMight(FeralMight.THANEBORN_TRIUMPH);
		}
		
		// TODO: Barbarian Agility, Feral Might, Rage Strike, Rampage
		Utils.print("NOTE: I have not yet coded Barbarian Agility, Feral Might, Rage Strike, Rampage.");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 15 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 8 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public FeralMight getFeralMight() {
		return feralMight;
	}

	public void setFeralMight(FeralMight feralMight) {
		this.feralMight = feralMight;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
