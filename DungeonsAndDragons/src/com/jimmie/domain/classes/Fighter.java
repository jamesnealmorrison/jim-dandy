package com.jimmie.domain.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.util.Utils;

public class Fighter extends DndClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WeaponTalent weaponTalent;

	@Override
	public void initializeForEncounter() {
	}

	@Override
	public void initializeForNewDay(DndCharacter dndCharacter) {
	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Add automatic trained skill(s).
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Intimidate");
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

	@Override
	public void makeClassChoicesBeforeAbilityScores(PlayerCharacter pc) {
		Utils.print("Setting role to Defender.");
		pc.setRole(Role.DEFENDER);
		
		Utils.print("Setting power Source to Martial.");
		pc.setPowerSource(PowerSource.MARTIAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Chainmail, Scale, Light shield, Heavy shield");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.CHAINMAIL);
		pc.addArmorGroupProficiency(ArmorGroup.SCALE);
		pc.addArmorGroupProficiency(ArmorGroup.LIGHT_SHIELD);
		pc.addArmorGroupProficiency(ArmorGroup.HEAVY_SHIELD);
		
		Utils.print("Adding Weapon Proficiencies: Simple melee, simple ranged, military melee, military ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_RANGED);
		
		Utils.print("Adding bonus of +2 Fortitude");
		setFortitudeBonus(getFortitudeBonus() + 2);
		
		Utils.print("Setting hit points per level gained = 6");
		pc.setHitPointsPerLevelGained(6);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Great Weapon Fighter: You're interested in dealing out the most damage you can.  You prefer big two-handed weapons.");
		Utils.print("2. Guardian Fighter: You fight smarter.  You're willing to trade offense for superior defenses and better ability to control the battlefield.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Strength your primary score and Constitution your secondary score.  Choose powers that work with two handed weapons.");
			Utils.print("Suggested Feat: Power Attack (Human feat: Action Surge)");
			Utils.print("Suggested Skills: Athletics, Endurance, Intimidate");
			Utils.print("Suggested At-Will Powers: Cleave, Reaping Strike");
			Utils.print("Suggested Encounter Power: Spinning Sweep");
			Utils.print("Suggested Daily Power: Brute Strike");
		} else {
			Utils.print("Make Strength your primary score and Dexterity or Wisdom your secondary score.  Choose powers that take advantage of your one handed weapon and shield.");
			Utils.print("Suggested Feat: Weapon Focus (Human feat: Human Perseverance)");
			Utils.print("Suggested Skills: Heal, Intimidate, Streetwise");
			Utils.print("Suggested At-Will Powers: Sure Strike, Tide of Iron");
			Utils.print("Suggested Encounter Power: Covering Attack");
			Utils.print("Suggested Daily Power: Comeback Strike");
		}
		
		Utils.print("Choose between the following Fighter Weapon Talents:");
		Utils.print("1. One Handed Weapons.");
		Utils.print("2. Two Handed Weapons.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setWeaponTalent(WeaponTalent.ONE_HANDED_WEAPONS);
		} else {
			setWeaponTalent(WeaponTalent.TWO_HANDED_WEAPONS);
		}

		// TODO: Combat Superiority
		Utils.print("NOTE: Looks like I may have implemented Combat Challenge and Fighter Weapon Talent already.  I'm not sure about Combat Superiority.");
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

	public WeaponTalent getWeaponTalent() {
		return weaponTalent;
	}

	public void setWeaponTalent(WeaponTalent weaponTalent) {
		this.weaponTalent = weaponTalent;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
