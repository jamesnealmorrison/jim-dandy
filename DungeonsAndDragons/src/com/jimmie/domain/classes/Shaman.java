package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.domain.items.weapons.WeaponType;
import com.jimmie.util.Utils;

public class Shaman extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CompanionSpirit companionSpirit;

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
		
		// Add automatic trained skill(s).
		trainedSkills.add("Nature");
		Utils.print("Automatically trained in Nature.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Arcana");
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("History");
		choices.add("Insight");
		choices.add("Perception");
		choices.add("Religion");
		
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
		Utils.print("Setting role to Leader.");
		pc.setRole(Role.LEADER);
		
		Utils.print("Setting power Source to Primal.");
		pc.setPowerSource(PowerSource.PRIMAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Longspear");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponTypeProficiency(WeaponType.LONGSPEAR);
		
		Utils.print("Adding bonus of +1 Fortitude, +1 Will");
		setFortitudeBonus(getFortitudeBonus() + 1);
		setWillBonus(getWillBonus() + 1);

		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Bear Shaman: You excel at protecting your allies and preserving life.");
		Utils.print("2. Panther Shaman: You wield the spirit's power to see events before they unfold and to shape them as you like.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Wisdom your primary score and Constitution your secondary score. Intelligence is third.");
			Utils.print("Suggested Class Feature: Protector Spirit");
			Utils.print("Suggested Feat: Shared Healing Spirit");
			Utils.print("Suggested Skills: Endurance, Heal, Nature, Perception");
			Utils.print("Suggested At-Will Powers: Defending Strike, Protecting Strike");
			Utils.print("Suggested Encounter Power: Thunder Bear's Warding");
			Utils.print("Suggested Daily Power: Spirit of the Healing Flood");
		} else {
			Utils.print("Make Wisdom your primary score and Intelligence your secondary score.  Constitution is third.");
			Utils.print("Suggested Class Feature: Stalker Spirit");
			Utils.print("Suggested Feat: Stalker Spirit Adept");
			Utils.print("Suggested Skills: Athletics, Heal, Nature, Perception");
			Utils.print("Suggested At-Will Powers: Stalker's Strike, Watcher's Strike");
			Utils.print("Suggested Encounter Power: Twin Panthers");
			Utils.print("Suggested Daily Power: Wrath of the Spirit World");
		}
		
		Utils.print("Choose between the following Companion Spirit:");
		Utils.print("1. Protector Spirit.");
		Utils.print("2. Stalker Spirit.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setCompanionSpirit(CompanionSpirit.PROTECTOR);
		} else {
			setCompanionSpirit(CompanionSpirit.STALKER);
		}
		
		// TODO: Companion Spirit, Healing Spirit, Speak with Spirits, Implements
		Utils.print("NOTE: I have not yet coded Companion Spirit, Healing Spirit, Speak with Spirits, Implements.");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 12 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 7 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public CompanionSpirit getCompanionSpirit() {
		return companionSpirit;
	}

	public void setCompanionSpirit(CompanionSpirit companionSpirit) {
		this.companionSpirit = companionSpirit;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
