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

public class Seeker extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SeekerBond seekerBond;

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
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Perception");
		choices.add("Stealth");
		
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
		Utils.print("Setting role to Controller.");
		pc.setRole(Role.CONTROLLER);
		
		Utils.print("Setting power Source to Primal.");
		pc.setPowerSource(PowerSource.PRIMAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Simple Ranged, Military Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_RANGED);
		
		Utils.print("Adding bonus of +1 Reflex, Will");
		if (pc.getReflexMisc1() == 0) {
			pc.setReflexMisc1(1);
		} else {
			pc.setReflexMisc2(pc.getReflexMisc2() + 1);
		}

		if (pc.getWillMisc1() == 0) {
			pc.setWillMisc1(1);
		} else {
			pc.setWillMisc2(pc.getWillMisc2() + 1);
		}

		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Protecting Seeker: You spoke your vow and forged a bond with the primal spirits, swearing to safeguard the land from harm.");
		Utils.print("2. Vengeful Seeker: A blood oath compels you to hunt down and destroy the world's enemies.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Wisdom your primary score and Strength your secondary score.");
			Utils.print("Suggested Class Feature: Spiritbond");
			Utils.print("Suggested Feat: Spiritbond Defense");
			Utils.print("Suggested Skills: Heal, Insight, Nature, Perception");
			Utils.print("Suggested At-Will Powers: Biting Swarm, Guardian Harrier");
			Utils.print("Suggested Encounter Power: Serpent Arrow");
			Utils.print("Suggested Daily Power: Spirit Rider");
		} else {
			Utils.print("Make Wisdom your primary score and Dexterity your secondary score.");
			Utils.print("Suggested Class Feature: Bloodbond");
			Utils.print("Suggested Feat: Bloodied Elusion");
			Utils.print("Suggested Skills: Acrobatics, Athletics, Nature, Stealth");
			Utils.print("Suggested At-Will Powers: Biting Swarm, Elemental Spirits");
			Utils.print("Suggested Encounter Power: Flickering Arrow");
			Utils.print("Suggested Daily Power: Storm of Spirit Shards");
		}
		
		Utils.print("Choose between the following Seekers Bonds:");
		Utils.print("1. Bloodbond.");
		Utils.print("2. Spiritbond.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setSeekerBond(SeekerBond.BLOODBOND);
		} else {
			setSeekerBond(SeekerBond.SPIRITBOND);
		}
		
		// TODO: Inevitable Shot, Seeker's Bond
		Utils.print("NOTE: I have not yet coded Inevitable Shot, Seeker's Bond.");
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

	public SeekerBond getSeekerBond() {
		return seekerBond;
	}

	public void setSeekerBond(SeekerBond seekerBond) {
		this.seekerBond = seekerBond;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
