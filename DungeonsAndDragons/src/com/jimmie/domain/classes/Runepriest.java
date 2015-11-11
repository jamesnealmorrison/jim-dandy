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

public class Runepriest extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RunicArtistry runicArtistry;

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
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("History");
		choices.add("Insight");
		choices.add("Thievery");
		
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

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Chainmail, Scale, Light Shield");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.CHAINMAIL);
		pc.addArmorGroupProficiency(ArmorGroup.SCALE);
		pc.addArmorGroupProficiency(ArmorGroup.LIGHT_SHIELD);
		
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
		Utils.print("1. Defiant Runepriest: You fight on the front lines, sustaining and aiding your allies with divine runes.");
		Utils.print("2. Wrathful Runepriest: Your place is at the forefront of any battle, using your hammer to smite your enemies.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Strength your primary score and Wisdom your secondary score.");
			Utils.print("Suggested Class Feature: Defiant Word");
			Utils.print("Suggested Feat: Rune of Hope");
			Utils.print("Suggested Skills: Arcana, History, Insight, Religion");
			Utils.print("Suggested At-Will Powers: Word of Binding, Word of Exchange");
			Utils.print("Suggested Encounter Power: Divine Rune of Thunder");
			Utils.print("Suggested Daily Power: Rune of the Undeniable Dawn");
		} else {
			Utils.print("Make Strength your primary score and Constitution your secondary score.");
			Utils.print("Suggested Class Feature: Wrathful Hammer");
			Utils.print("Suggested Feat: Rune of Vengeance");
			Utils.print("Suggested Skills: Athletics, Endurance, Heal, Religion");
			Utils.print("Suggested At-Will Powers: Word of Diminishment, Word of Shielding");
			Utils.print("Suggested Encounter Power: Anvil of Battle");
			Utils.print("Suggested Daily Power: Rune of Endless Fire");
		}
		
		Utils.print("Choose between the following Runic Artistries:");
		Utils.print("1. Defiant Word.");
		Utils.print("2. Wrathful Hammer.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setRunicArtistry(RunicArtistry.DEFIANT_WORD);
		} else {
			setRunicArtistry(RunicArtistry.WRATHFUL_HAMMER);
		}
		
		// TODO: Rune Master, Rune of Mending, Runic Artistry
		Utils.print("NOTE: I have not yet coded Rune Master, Rune of Mending, Runic Artistry.");
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

	public RunicArtistry getRunicArtistry() {
		return runicArtistry;
	}

	public void setRunicArtistry(RunicArtistry runicArtistry) {
		this.runicArtistry = runicArtistry;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
