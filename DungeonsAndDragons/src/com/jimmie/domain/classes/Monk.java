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

public class Monk extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MonasticTradition monasticTradition;

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
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Diplomacy");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Insight");
		choices.add("Perception");
		choices.add("Religion");
		choices.add("Stealth");
		choices.add("Thievery");
		
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
		Utils.print("Setting role to Striker.");
		pc.setRole(Role.STRIKER);
		
		Utils.print("Setting power Source to Psionic.");
		pc.setPowerSource(PowerSource.PSIONIC);

		Utils.print("Adding Armor Proficiencies: Cloth");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		
		Utils.print("Adding Weapon Proficiencies: Club, Dagger, Unarmed Strike, Quarterstaff, Shuriken, Sling, Spear");
		pc.addWeaponTypeProficiency(WeaponType.CLUB);
		pc.addWeaponTypeProficiency(WeaponType.DAGGER);
		pc.addWeaponTypeProficiency(WeaponType.UNARMED_ATTACK);
		pc.addWeaponTypeProficiency(WeaponType.QUARTERSTAFF);
		pc.addWeaponTypeProficiency(WeaponType.SHURIKEN);
		pc.addWeaponTypeProficiency(WeaponType.SLING);
		pc.addWeaponTypeProficiency(WeaponType.SPEAR);
		
		Utils.print("Adding bonus of +1 Fortitude, +1 Reflex, +1 Will");
		setFortitudeBonus(getFortitudeBonus() + 1);
		setReflexBonus(getReflexBonus() + 1);
		setWillBonus(getWillBonus() + 1);

		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Centered Breath Monk: Anticipating your enemy's attacks, you move swiftly across the battlefield and excel at projecting your psionic energy outward to control your enemies and foil their plans.");
		Utils.print("2. Stone Fist Monk.  Your fighting style is aggressive, emphasizing powers that eliminate your enemy quickly.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Dexterity your primary score and Wisdom your secondary score. Strength is third.");
			Utils.print("Suggested Class Feature: Centered Breath");
			Utils.print("Suggested Feat: Pointed Step Style");
			Utils.print("Suggested Skills: Acrobatics, Athletics, Insight, Perception");
			Utils.print("Suggested At-Will Powers: Dancing Cobra, Five Storms");
			Utils.print("Suggested Encounter Power: Drunken Monkey");
			Utils.print("Suggested Daily Power: Masterful Spiral");
		} else {
			Utils.print("Make Dexterity your primary score and Strength your secondary score.  Wisdom is third.");
			Utils.print("Suggested Class Feature: Stone Fist");
			Utils.print("Suggested Feat: Crashing Tempest Style");
			Utils.print("Suggested Skills: Acrobatics, Athletics, Endurance, Perception");
			Utils.print("Suggested At-Will Powers: Crane's Wings, Dragon's Tail");
			Utils.print("Suggested Encounter Power: Awaken the Slumbering Hurt");
			Utils.print("Suggested Daily Power: Spinning Leopard Maneuver");
		}
		
		Utils.print("Choose between the following Monastic Traditions:");
		Utils.print("1. Centered Breath.");
		Utils.print("2. Stone Fist.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setMonasticTradition(MonasticTradition.CENTERED_BREATH);
		} else {
			setMonasticTradition(MonasticTradition.STONE_FIST);
		}
		
		// TODO: Barbarian Agility, Feral Might, Rage Strike, Rampage
		Utils.print("NOTE: I have not yet coded Barbarian Agility, Feral Might, Rage Strike, Rampage.");
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

	public MonasticTradition getMonasticTradition() {
		return monasticTradition;
	}

	public void setMonasticTradition(MonasticTradition monasticTradition) {
		this.monasticTradition = monasticTradition;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
