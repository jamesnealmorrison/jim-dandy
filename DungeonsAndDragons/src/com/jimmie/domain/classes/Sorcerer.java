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

public class Sorcerer extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SorcererSpellSource spellSource;

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
		trainedSkills.add("Arcana");
		Utils.print("Automatically trained in Arcana.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Athletics");
		choices.add("Bluff");
		choices.add("Diplomacy");
		choices.add("Dungeoneering");
		choices.add("Endurance");
		choices.add("History");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Nature");
		
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
		
		Utils.print("Setting power Source to Arcane.");
		pc.setPowerSource(PowerSource.ARCANE);

		Utils.print("Adding Armor Proficiencies: Cloth");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding bonus of +2 Will");
		if (pc.getWillMisc1() == 0) {
			pc.setWillMisc1(2);
		} else {
			pc.setWillMisc2(pc.getWillMisc2() + 2);
		}

		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Chaos Sorcerer: You can be reckless and hard to control.");
		Utils.print("2. Dragon Sorcerer: You command the ancient arcane power that flows through dragons.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Charisma your primary score and Dexterity your secondary score.");
			Utils.print("Suggested Class Feature: Wild Magic");
			Utils.print("Suggested Feat: Arcane Spellfury");
			Utils.print("Suggested Skills: Arcana, Bluff, Endurance, Insight");
			Utils.print("Suggested At-Will Powers: Chaos Bolt, Storm Walk");
			Utils.print("Suggested Encounter Power: Bedeviling Burst");
			Utils.print("Suggested Daily Power: Dazzling Ray");
		} else {
			Utils.print("Make Charisma your primary score and Strength your secondary score.");
			Utils.print("Suggested Class Feature: Dragon Magic");
			Utils.print("Suggested Feat: Implement Expertise");
			Utils.print("Suggested Skills: Arcana, Athletics, History, Intimidate");
			Utils.print("Suggested At-Will Powers: Burning Spray, Dragonfrost");
			Utils.print("Suggested Encounter Power: Tempest Breath");
			Utils.print("Suggested Daily Power: Lightning Breath");
		}
		
		Utils.print("Choose between the following Spell Source:");
		Utils.print("1. Dragon Magic");
		Utils.print("2. Wild Magic.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setSpellSource(SorcererSpellSource.DRAGON_MAGIC);
		} else {
			setSpellSource(SorcererSpellSource.WILD_MAGIC);
		}
		
		// TODO: Spell Source, Implements
		Utils.print("NOTE: I have not yet coded Spell Source, Implements.");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 12 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 68 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public SorcererSpellSource getSpellSource() {
		return spellSource;
	}

	public void setSpellSource(SorcererSpellSource spellSource) {
		this.spellSource = spellSource;
	}
}
