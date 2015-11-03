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

public class Ranger extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	RangerFightingStyle fightingStyle;

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
		List<String> choices = new ArrayList<String>();
		choices.add("Dungeoneering");
		choices.add("Nature");
		Utils.print("Choose one:");
		Utils.printValidStringChoices(choices);
		Utils.print("Your choice:");
		String choice = Utils.getValidInput(choices);
		if ("Dungeoneering".equalsIgnoreCase(choice)) {
			trainedSkills.add("Dungeoneering");
		} else {
			trainedSkills.add("Nature");
		}
		choices.remove(choice);
		
		// Now make selections.
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Perception");
		choices.add("Stealth");
		
		Utils.print("Choose 4 of the following");
		for (int i = 0; i < 4; i++) {
			Utils.printValidStringChoices(choices);
			Utils.print("Your choice:");
			choice = Utils.getValidInput(choices);
			trainedSkills.add(choice);
			choices.remove(choice);
		}
		
		return trainedSkills;
	}

	@Override
	public void makeClassChoicesBeforeAbilityScores(PlayerCharacter pc) {
		Utils.print("Setting role to Striker.");
		pc.setRole(Role.STRIKER);
		
		Utils.print("Setting power Source to Martial.");
		pc.setPowerSource(PowerSource.MARTIAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		
		Utils.print("Adding Weapon Proficiencies: Simple melee, simple ranged, military melee, military ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_RANGED);
		
		Utils.print("Adding bonus of +1 Fortitude, +1 Reflex");
		if (pc.getFortitudeMisc1() == 0) {
			pc.setFortitudeMisc1(1);
		} else {
			pc.setFortitudeMisc2(pc.getFortitudeMisc2() + 1);
		}
		
		if (pc.getReflexMisc1() == 0) {
			pc.setReflexMisc1(1);
		} else {
			pc.setReflexMisc2(pc.getReflexMisc2() + 1);
		}
		
		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Archer Ranger: You are master of the bow.");
		Utils.print("2. Two-Blade Ranger: You like to get up close and rely on the two-weapon fighting style.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Dexterity your primary score and Strength your secondary score. Wisdom is third.  Choose powers that work with ranged weapons.");
			Utils.print("Suggested Feat: Agile Hunter (Human feat: Human Perseverence)");
			Utils.print("Suggested Skills: Endurance, Heal, Nature, Perception, Stealth");
			Utils.print("Suggested At-Will Powers: Careful Attack, Nimble Strike");
			Utils.print("Suggested Encounter Power: Evasive Strike");
			Utils.print("Suggested Daily Power: Split the tree");
		} else {
			Utils.print("Make Strength your primary score and Dexterity your secondary score.  Wisdom is third. Choose powers for fighting with two melee weapons.");
			Utils.print("Suggested Feat: Lethal Hunter (Human feat: Action Surge)");
			Utils.print("Suggested Skills: Acrobatics, Dungeoneering, Enduance, Heal, Perception");
			Utils.print("Suggested At-Will Powers: Hit and Run, Twin Strike");
			Utils.print("Suggested Encounter Power: Dire Wolverine Strike");
			Utils.print("Suggested Daily Power: Jaws of the Wolf");
		}
		
		Utils.print("Please choose a fighting style:");
		Utils.print("1. Archer: Because of your focus on ranged attacks, you gain Defensive Mobility as a bonus feat.");
		Utils.print("2. Two-Blade: You can wield a weapon in your off-hand with no penalty.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		
		if (choice == 1) {
			fightingStyle = RangerFightingStyle.ARCHER;
		} else {
			fightingStyle = RangerFightingStyle.TWO_BLADE;
		}
		
		// TODO: Fighting Style coding, Hunter's Quarry, Prime Shot
		Utils.print("NOTE: I have not yet coded Fighting Style coding, Hunter's Quarry, Prime Shot");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 15 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 9 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}
}
