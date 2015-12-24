package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.ImplementType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.util.Utils;

public class Warlock extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EldritchPact eldritchPact;

	@Override
	public void initializeForEncounter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeForNewDay(DndCharacter dndCharacter) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Arcana");
		choices.add("Bluff");
		choices.add("History");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Religion");
		choices.add("Streetwise");
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
		
		Utils.print("Setting power Source to Arcane.");
		pc.setPowerSource(PowerSource.ARCANE);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding Implement Proficiencies: Rods, Wands");
		pc.addImplementProficiency(ImplementType.ROD);
		pc.addImplementProficiency(ImplementType.WAND);

		Utils.print("Adding bonus of +1 Reflex, +1 Will");
		setReflexBonus(getReflexBonus() + 1);
		setWillBonus(getWillBonus() + 1);
		
		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Deceptive Warlock: You prefer spells that deal a little less damage, but that inflict a variety of penalties on your foes.");
		Utils.print("2. Scourge Warlock: No subtlety here - you want to deal damage.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Charisma your primary score and Intelligence your secondary score.  Constitution is third.");
			Utils.print("Choose fey pact or the star pact for your class feature.");
			Utils.print("Suggested Feat: Improved Misty Step (Human feat: Human Perseverence)");
			Utils.print("Suggested Skills: Arcana, Bluff, Insight, Thievery");
			Utils.print("Suggested At-Will Powers: Eldritch Blast, Eyebite");
			Utils.print("Suggested Encounter Power: Witchfire");
			Utils.print("Suggested Daily Power: Curse of the Dark Dream");
		} else {
			Utils.print("Make Constitution your primary score and Intelligence your secondary score. Charisma is third.");
			Utils.print("Choose Internal Pact or the Star Pact as your class feature.");
			Utils.print("Suggested Feat: Improved Dark One's Blessing (Human feat: Action Surge)");
			Utils.print("Suggested Skills: Arcana, History, Intimidate, Streetwise");
			Utils.print("Suggested At-Will Powers: Eldritch Blast, Hellish Rebuke");
			Utils.print("Suggested Encounter Power: Vampiric Embrace");
			Utils.print("Suggested Daily Power: Flames of Phlegethos");
		}
		
		Utils.print("Choose between the following Eldritch Pacts:");
		Utils.print("1. Fey Pact. You know the Eyebite at will spell. And Misty Step pact boon.");
		Utils.print("2. Infernal Pact. You know the Hellish Rebuke at will spell.  And the Dark One's Blessing pact boon.");
		Utils.print("3. Star Pact. You know the Dire Radiance at will spell.  And the Fate of the Void pact boon.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 3);
		if (choice == 1) {
			setEldritchPact(EldritchPact.FEY_PACT);
		} else if (choice == 2) {
			setEldritchPact(EldritchPact.INFERNAL_PACT);
		} else {
			setEldritchPact(EldritchPact.STAR_PACT);
		}
		
		// TODO: Eldritch Blast, Eldritch Pact, Prime Shot, Warlock's Curse
		Utils.print("NOTE: I have not yet coded Eldritch Blast, Eldritch Pact, Prime Shot, Warlock's Curse");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 12 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 6 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public EldritchPact getEldritchPact() {
		return eldritchPact;
	}

	public void setEldritchPact(EldritchPact eldritchPact) {
		this.eldritchPact = eldritchPact;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
