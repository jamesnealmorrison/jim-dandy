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

public class Cleric extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		trainedSkills.add("Religion");
		Utils.print("Automatically trained in Religion.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Arcana");
		choices.add("Diplomacy");
		choices.add("Heal");
		choices.add("History");
		choices.add("Insight");
		
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
		
		Utils.print("Setting power Source to Divine.");
		pc.setPowerSource(PowerSource.DIVINE);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Chainmail");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.CHAINMAIL);
		
		Utils.print("Adding Weapon Proficiencies: Simple melee, simple ranged");
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
		Utils.print("1. Battle Cleric: You find a good assortment of melee strikes to your liking.");
		Utils.print("2. Devoted Cleric: You stand back and concentrate on keeping your fellow adventurers healthy and optimized.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Strength your primary score and Wisdom your secondary score.  Charisma is tertiary.  Choose powers that work with melee attacks.");
			Utils.print("Suggested Feat: Weapon Focus (Human feat: Action Surge)");
			Utils.print("Suggested Skills: Diplomacy, Heal, Insight, Religion");
			Utils.print("Suggested At-Will Powers: Righteous Brand, Priest's Shield");
			Utils.print("Suggested Encounter Power: Wrathful Thunder");
			Utils.print("Suggested Daily Power: Avenging Flame");
		} else {
			Utils.print("Make Wisdom your primary score and Charisma your secondary score.  Strength is tertiary.  Choose powers that grant bonuses and healing.");
			Utils.print("Suggested Feat: Channel Divinity (Human feat: Human Perseverance)");
			Utils.print("Suggested Skills: Arcana, Heal, History, Religion");
			Utils.print("Suggested At-Will Powers: Lance of Faith, Sacred Flame");
			Utils.print("Suggested Encounter Power: Healing Strike");
			Utils.print("Suggested Daily Power: Beacon of Hope");
		}
		
		// TODO: Channel divinity, Healer's Lore, Healing Word, Ritual Casting, Implement
		Utils.print("NOTE: I have not yet implemented Channel divinity, Healer's Lore, Healing Word, Ritual Casting, Implement.");
		
		// TODO: Selecting deities.
		Utils.print("NOTE: I also have not yet implemented anything related to deities.");
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

}
