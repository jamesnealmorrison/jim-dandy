package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponType;
import com.jimmie.util.Utils;

public class Rogue extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RogueTactics rogueTactics;

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
		trainedSkills.add("Stealth");
		trainedSkills.add("Thievery");
		Utils.print("Automatically trained in Stealth and Thievery.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Bluff");
		choices.add("Dungeoneering");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Perception");
		choices.add("Streetwise");
		
		Utils.print("Choose 4 of the following");
		for (int i = 0; i < 4; i++) {
			Utils.printValidStringChoices(choices);
			Utils.print("Your choice:");
			String choice = Utils.getValidInput(choices);
			trainedSkills.add(choice);
			choices.remove(choice);
		}
		
		return trainedSkills;
	}

	public RogueTactics getRogueTactics() {
		return rogueTactics;
	}

	public void setRogueTactics(RogueTactics rogueTactics) {
		this.rogueTactics = rogueTactics;
	}

	@Override
	public void makeClassChoicesBeforeAbilityScores(PlayerCharacter pc) {
		Utils.print("Setting role to Striker.");
		pc.setRole(Role.STRIKER);
		
		Utils.print("Setting power Source to Martial.");
		pc.setPowerSource(PowerSource.MARTIAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		
		Utils.print("Adding Weapon Proficiencies: Dagger, Hand crossbow, Shuriken, Sling, Short Sword");
		pc.addWeaponTypeProficiency(WeaponType.DAGGER);
		pc.addWeaponTypeProficiency(WeaponType.HAND_CROSSBOW);
		pc.addWeaponTypeProficiency(WeaponType.SHURIKEN);
		pc.addWeaponTypeProficiency(WeaponType.SLING);
		pc.addWeaponTypeProficiency(WeaponType.SHORTSWORD);
		
		Utils.print("Adding bonus of +2 Reflex");
		if (pc.getReflexMisc1() == 0) {
			pc.setReflexMisc1(2);
		} else {
			pc.setReflexMisc2(pc.getReflexMisc2() + 2);
		}
		
		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Brawny Rogue: You like powers that deal plenty of damage and stun, immobilize, knock down, or push your foes.");
		Utils.print("2. Trickster Rogue: You like powers that deceive and misdirect your foes.  You dart in and out of the fray in combat.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Dexterity your primary score and Strength your secondary score.  Charisma is third.  Choose powers that deal a lot of damage.");
			Utils.print("Select Brutal Scoundrel as you class feature.");
			Utils.print("Suggested Feat: Weapon Focus (Human feat: Toughness)");
			Utils.print("Suggested Skills: Athletics, Dungeoneering, Intimidate, Stealth, Streetwise, Thievery");
			Utils.print("Suggested At-Will Powers: Piercing Strike, Riposte Strike");
			Utils.print("Suggested Encounter Power: Torturous Strike");
			Utils.print("Suggested Daily Power: Easy Target");
		} else {
			Utils.print("Make Dexterity your primary score and Charisma your secondary score. Strength is third.  Choose powers that take advantage of your high charisma score.");
			Utils.print("Select Artful Dodger as your class feature.");
			Utils.print("Suggested Feat: Backstabber (Human feat: Human Perseverance)");
			Utils.print("Suggested Skills: Acrobatics, Bluff, Insight, Perception, Stealth, Thievery");
			Utils.print("Suggested At-Will Powers: Deft Strike, Sly Flourish");
			Utils.print("Suggested Encounter Power: Positioning Strike");
			Utils.print("Suggested Daily Power: Trick Strike");
		}
		
		Utils.print("Choose between the following Rogue Tactics:");
		Utils.print("1. Artful Dodger: You gain a bonus to AC equal to your Charisma modifier against Opportunity Attacks.");
		Utils.print("2. Brutal Scoundrel: You gain a bonus to Sneak Attack damage equal to your Strength Modifier.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setRogueTactics(RogueTactics.ARTFUL_DODGER);
		} else {
			setRogueTactics(RogueTactics.BRUTAL_SCOUNDREL);
		}
		
		// TODO: First Strike, Rogue Tactics, Rogue Weapon Talent, Sneak Attack
		Utils.print("NOTE: I have not yet coded First Strike, Rogue Tactics, Rogue Weapon Talent, Sneak Attack");
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
