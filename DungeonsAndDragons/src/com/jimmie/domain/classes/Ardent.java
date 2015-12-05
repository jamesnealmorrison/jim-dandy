package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.powers.ArdentAlacrity;
import com.jimmie.powers.ArdentOutrage;
import com.jimmie.powers.ArdentSurge;
import com.jimmie.util.Utils;

public class Ardent extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArdentMantle ardentMantle;

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
		choices.add("Arcana");
		choices.add("Athletics");
		choices.add("Bluff");
		choices.add("Diplomacy");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Insight");
		choices.add("Intimidate");
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

	@Override
	public void makeClassChoicesBeforeAbilityScores(PlayerCharacter pc) {
		Utils.print("Setting role to Leader.");
		pc.setRole(Role.LEADER);
		
		Utils.print("Setting power Source to Psionic.");
		pc.setPowerSource(PowerSource.PSIONIC);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Chainmail");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.CHAINMAIL);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Military Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding bonus of +1 Fortitude, +1 Will");
		setFortitudeBonus(getFortitudeBonus() + 1);
		setWillBonus(getWillBonus() + 1);

		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Enlightened Ardent: You read your enemies' weaknesses and reveal them to your allies.");
		Utils.print("2. Euphoric Ardent: You can scarcely contain the emotions that strain against your will like a crashing tide.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Charisma your primary score and Wisdom your secondary score. Constitution is third.");
			Utils.print("Suggested Class Feature: Mantle of Clarity");
			Utils.print("Suggested Feat: Bolstering Mantle");
			Utils.print("Suggested Skills: Bluff, Diplomacy, Heal, Insight");
			Utils.print("Suggested At-Will Powers: Focusing Strike, Psionic Shield");
			Utils.print("Suggested Daily Power: Wormhole Plunge");
		} else {
			Utils.print("Make Charisma your primary score and Constitution your secondary score.  Wisdom is third.");
			Utils.print("Suggested Class Feature: Mantle of Elation");
			Utils.print("Suggested Feat: Heartening Surge");
			Utils.print("Suggested Skills: Athletics, Endurance, Intimidate, Streetwise");
			Utils.print("Suggested At-Will Powers: Demoralizing Strike, Ire Strike");
			Utils.print("Suggested Daily Power: Battleborn Acuity");
		}
		
		Utils.print("Choose between the following Ardent Mantles:");
		Utils.print("1. Mantle of Clarity.");
		Utils.print("2. Mantle of Elation.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setArdentMantle(ArdentMantle.MANTLE_OF_CLARITY);
			pc.addPower(new ArdentAlacrity());
		} else {
			setArdentMantle(ArdentMantle.MANTLE_OF_ELATION);
			pc.addPower(new ArdentOutrage());
		}
		
		pc.addPower(new ArdentSurge());
		
		// TODO: Ardent Surge, Psionic Augmentation
		Utils.print("NOTE: I have not yet coded Ardent Surge, Psionic Augmentation.");
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

	public ArdentMantle getArdentMantle() {
		return ardentMantle;
	}

	public void setArdentMantle(ArdentMantle ardentMantle) {
		this.ardentMantle = ardentMantle;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
