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

public class Wizard extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArcaneImplement arcaneImplement;

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
		choices.add("Diplomacy");
		choices.add("Dungeoneering");
		choices.add("History");
		choices.add("Insight");
		choices.add("Nature");
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
		Utils.print("Setting role to Controller.");
		pc.setRole(Role.CONTROLLER);
		
		Utils.print("Setting power Source to Arcane.");
		pc.setPowerSource(PowerSource.ARCANE);

		Utils.print("Adding Armor Proficiencies: Cloth");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		
		Utils.print("Adding Weapon Proficiencies: Dagger, Quarterstaff");
		pc.addWeaponTypeProficiency(WeaponType.DAGGER);
		pc.addWeaponTypeProficiency(WeaponType.QUARTERSTAFF);
		
		Utils.print("Adding bonus of +2 Will");
		if (pc.getWillMisc1() == 0) {
			pc.setWillMisc1(1);
		} else {
			pc.setWillMisc2(pc.getWillMisc2() + 2);
		}
		
		Utils.print("Setting hit points per level gained = 4");
		pc.setHitPointsPerLevelGained(4);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Control Wizard: Your favorite powers restrict your enemies in various ways.");
		Utils.print("2. War Wizard: Your delight is in powers that deal damage to many foes at a time.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Intelligence your primary score and Wisdom your secondary score.  Dexterity is third.");
			Utils.print("Suggested Feat: Improved Initiative (Human feat: Human Perseverence)");
			Utils.print("Suggested Skills: Arcana, Diplomacy, Insight, Nature");
			Utils.print("Suggested At-Will Powers: Cloud of Daggers, Thunderwave");
			Utils.print("Suggested Encounter Power: Icy Terrain");
			Utils.print("Suggested Daily Power: Sleep");
		} else {
			Utils.print("Make Intelligence your primary score and Dexterity your secondary score.  Wisdom is third.");
			Utils.print("Suggested Feat: Expanded Spellbook (Human feat: Action Surge)");
			Utils.print("Suggested Skills: Arcana, Dungeoneering, History, Religion");
			Utils.print("Suggested At-Will Powers: Magic Missile, Scorching Burst");
			Utils.print("Suggested Encounter Power: Burning Hands");
			Utils.print("Suggested Daily Power: Acid Arrow");
		}
		
		Utils.print("Choose between the following Arcane Implement Masteries:");
		Utils.print("1. Orb of Imposition.");
		Utils.print("2. Staff of Defense.");
		Utils.print("3. Wand of Accuracy.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 3);
		if (choice == 1) {
			setArcaneImplement(ArcaneImplement.ORB_OF_IMPOSITION);
		} else if (choice == 2) {
			setArcaneImplement(ArcaneImplement.STAFF_OF_DEFENSE);
		} else {
			setArcaneImplement(ArcaneImplement.WAND_OF_ACCURACY);
		}
		
		// TODO: Arcane Implement Mastery, Cantrips, Ritual Casting, Spellbook
		Utils.print("NOTE: I have not yet coded Arcane Implement Mastery, Cantrips, Ritual Casting, Spellbook");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 10 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 6 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public ArcaneImplement getArcaneImplement() {
		return arcaneImplement;
	}

	public void setArcaneImplement(ArcaneImplement arcaneImplement) {
		this.arcaneImplement = arcaneImplement;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
