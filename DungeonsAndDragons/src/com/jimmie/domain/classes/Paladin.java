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
import com.jimmie.powers.ChannelDivinityDivineMettle;
import com.jimmie.powers.ChannelDivinityDivineStrength;
import com.jimmie.powers.DivineChallenge;
import com.jimmie.powers.LayOnHands;
import com.jimmie.util.Utils;

public class Paladin extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		
		// Add automatic trained skill(s).
		trainedSkills.add("Religion");
		Utils.print("Automatically trained in Religion.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Diplomacy");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("History");
		choices.add("Insight");
		choices.add("Intimidate");
		
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
		
		Utils.print("Setting power Source to Divine.");
		pc.setPowerSource(PowerSource.DIVINE);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Chainmail, Scale, Plate, Light shield, Heavy shield");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.CHAINMAIL);
		pc.addArmorGroupProficiency(ArmorGroup.SCALE);
		pc.addArmorGroupProficiency(ArmorGroup.PLATE);
		pc.addArmorGroupProficiency(ArmorGroup.LIGHT_SHIELD);
		pc.addArmorGroupProficiency(ArmorGroup.HEAVY_SHIELD);
		
		Utils.print("Adding Weapon Proficiencies: Simple melee, simple ranged, military melee");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		
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
		Utils.print("1. Avenging Paladin: The best way to safeguard your allies is to destroy your enemies with divine power and overwhelming attack.");
		Utils.print("2. Protecting Paladin: You emphasize defense, guarding your allies, and healing and bolstering them with your powers.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Strength your primary score and Charisma your secondary score. Wisdom is third.  Consider a big two-handed weapon and choose powers with the highest damage.");
			Utils.print("Suggested Feat: Power Attack (Human feat: Human Perseverence)");
			Utils.print("Suggested Skills: Endurance, Heal, Intimidate, Religion");
			Utils.print("Suggested At-Will Powers: Holy Strike, Valiant Strike");
			Utils.print("Suggested Encounter Power: Radiant Smite");
			Utils.print("Suggested Daily Power: Paladin's Judgment");
		} else {
			Utils.print("Make Charisma your primary score and Strength your secondary score.  Wisdom is third. Choose powers that help allies and a few with damage.");
			Utils.print("Suggested Feat: Healing Hands (Human feat: Action Surge)");
			Utils.print("Suggested Skills: Diplomacy, Heal, Insight, Religion");
			Utils.print("Suggested At-Will Powers: Bolstering Strike, Enfeebling Strike");
			Utils.print("Suggested Encounter Power: Shielding Smite");
			Utils.print("Suggested Daily Power: Radiant Delirium");
		}
		
		pc.addPower(new ChannelDivinityDivineMettle());
		pc.addPower(new ChannelDivinityDivineStrength());
		pc.addPower(new DivineChallenge());
		pc.addPower(new LayOnHands());
		
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 15 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 10 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
