package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.ImplementType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.powers.ChannelDivinityAbjureUndead;
import com.jimmie.powers.ChannelDivinityDivineGuidance;
import com.jimmie.powers.OathOfEnmity;
import com.jimmie.powers.Power;
import com.jimmie.util.Utils;

public class Avenger extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AvengersCensure censure;
	private boolean usedChannelDivinity;
	public boolean isUsedChannelDivinity() {
		return usedChannelDivinity;
	}

	public void setUsedChannelDivinity(boolean usedChannelDivinity) {
		this.usedChannelDivinity = usedChannelDivinity;
	}

	private boolean aspectOfMightEncounterBonus;

	public boolean isAspectOfMightEncounterBonus() {
		return aspectOfMightEncounterBonus;
	}

	public void setAspectOfMightEncounterBonus(boolean aspectOfMightEncounterBonus) {
		this.aspectOfMightEncounterBonus = aspectOfMightEncounterBonus;
	}

	@Override
	public void initializeForEncounter() {
		usedChannelDivinity = false;
		aspectOfMightEncounterBonus = false;
	}

	@Override
	public void initializeForNewDay() {
	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Add automatic trained skill(s).
		trainedSkills.add("Religion");
		Utils.print("Automatically trained in Religion.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Intimidate");
		choices.add("Perception");
		choices.add("Stealth");
		choices.add("Streetwise");
		
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

	public AvengersCensure getCensure() {
		return censure;
	}

	public void setCensure(AvengersCensure censure) {
		this.censure = censure;
	}

	@Override
	public void makeClassChoicesBeforeAbilityScores(PlayerCharacter pc) {
		Utils.print("Setting role to Striker.");
		pc.setRole(Role.STRIKER);
		
		Utils.print("Setting power Source to Divine.");
		pc.setPowerSource(PowerSource.DIVINE);

		Utils.print("Adding Armor Proficiencies: Cloth");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Military Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding Implement Proficiencies: Holy symbol");
		pc.addImplementProficiency(ImplementType.HOLY_SYMBOL);

		Utils.print("Adding bonus of +1 Fortitude, +1 Reflex, +1 Will");
		setFortitudeBonus(getFortitudeBonus() + 1);
		setReflexBonus(getReflexBonus() + 1);
		setWillBonus(getWillBonus() + 1);
		
		Utils.print("Setting hit points per level gained = 6");
		pc.setHitPointsPerLevelGained(6);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Isolating Avenger: After you swear an oath of enmity, you keep your enemy beside you and drive other foes away.");
		Utils.print("2. Pursuing Avenger: Once you swear an oath of enmity, you pursue that creature wherever it goes.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Wisdom your primary score and Intelligence your secondary score.");
			Utils.print("Suggested Class Feature: Censure of Retribution");
			Utils.print("Suggested Feat: Toughness");
			Utils.print("Suggested Skills: Athletics, Intimidate, Religion, Streetwise");
			Utils.print("Suggested At-Will Powers: Bond of Retribution, Overwhelming Strike");
			Utils.print("Suggested Encounter Power: Avenging Echo");
			Utils.print("Suggested Daily Power: Temple of Light");
		} else {
			Utils.print("Make Wisdom your primary score and Dexterity your secondary score.");
			Utils.print("Suggested Class Feature: Censure of Pursuit");
			Utils.print("Suggested Feat: Invigorating Pursuit");
			Utils.print("Suggested Skills: Acrobatics, Perception, Religion, Stealth");
			Utils.print("Suggested At-Will Powers: Bond of Pursuit, Radiant Vengeance");
			Utils.print("Suggested Encounter Power: Angelic Alacity");
			Utils.print("Suggested Daily Power: Oath of the Final Duel");
		}
		
		Utils.print("Choose between the following Avenger's Censures:");
		Utils.print("1. Censure of Pursuit.");
		Utils.print("2. Censure of Retribution.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			censure = AvengersCensure.CENSURE_OF_PURSUIT;
		} else {
			censure = AvengersCensure.CENSURE_OF_RETRIBUTION;
		}
		
		pc.addPower(new OathOfEnmity());
		pc.addPower(new ChannelDivinityAbjureUndead());
		pc.addPower(new ChannelDivinityDivineGuidance());
		
		// TODO: Armor of Faith, Avenger's Censure, Deities
		Utils.print("NOTE: I have not yet coded Armor of Faith, Avenger's Censure, Deities");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 14 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 7 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	public AttackTarget getOathOfEnmityTarget(DndCharacter attacker) {
		// Find the Oath Of Enmity power
		for (Power power : attacker.getPowers()) {
			if (OathOfEnmity.class.isAssignableFrom(power.getClass())) {
				return ((OathOfEnmity) power).getOathOfEnmityTarget();
			}
		}
		return null;
	}
}
