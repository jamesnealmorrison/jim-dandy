package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.ImplementType;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.powers.ChannelDivinityArmorOfWrath;
import com.jimmie.powers.ChannelDivinityPreserversRebuke;
import com.jimmie.powers.ChannelDivinityRebukeUndead;
import com.jimmie.util.Utils;

public class Invoker extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DivineCovenant divineCovenant;

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
		trainedSkills.add("Religion");
		Utils.print("Automatically trained in Religion.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Arcana");
		choices.add("Diplomacy");
		choices.add("Endurance");
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
		Utils.print("Setting role to Controller.");
		pc.setRole(Role.CONTROLLER);
		
		Utils.print("Setting power Source to Divine.");
		pc.setPowerSource(PowerSource.DIVINE);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Chainmail");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.CHAINMAIL);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding Implement Proficiencies: Rods, Staffs");
		pc.addImplementProficiency(ImplementType.ROD);
		pc.addImplementProficiency(ImplementType.STAFF);

		Utils.print("Adding bonus of +1 Fortitude, +1 Reflex +1 Will");
		setFortitudeBonus(getFortitudeBonus() + 1);
		setReflexBonus(getReflexBonus() + 1);
		setWillBonus(getWillBonus() + 1);

		Utils.print("Setting hit points per level gained = 4");
		pc.setHitPointsPerLevelGained(4);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Preserving Invoker: You defend your allies, combining defensive magic with prayers that hinder or prevent your enemies' attacks.");
		Utils.print("2. Wrathful Invoker: You are an instrument of divine wrath, smiting those who have the temerity to dout the reach of your god's power.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Wisdom your primary score and Intelligence your secondary score.");
			Utils.print("Suggested Class Feature: Covenant of Preservation");
			Utils.print("Suggested Feat: Insightful Preservation");
			Utils.print("Suggested Skills: Arcana, Diplomacy, History, Religion");
			Utils.print("Suggested At-Will Powers: Sun Strike, Vanguard's Lightning");
			Utils.print("Suggested Encounter Power: Blades of Astral Fire");
			Utils.print("Suggested Daily Power: Binding Invocation of Chains");
		} else {
			Utils.print("Make Wisdom your primary score and Constitution your secondary score.");
			Utils.print("Suggested Class Feature: Covenant of Wrath");
			Utils.print("Suggested Feat: Invoker Defense");
			Utils.print("Suggested Skills: Endurance, Insight, Intimidate, Religion");
			Utils.print("Suggested At-Will Powers: Avenging Light, Grasping Shards");
			Utils.print("Suggested Encounter Power: Thunder of Judgment");
			Utils.print("Suggested Daily Power: Purging Flame");
		}
		
		Utils.print("Choose between the following Divine Covenants:");
		Utils.print("1. Covenant of Preservation.");
		Utils.print("2. Covenant of Wrath.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setDivineCovenant(DivineCovenant.PRESERVATION);
			pc.addPower(new ChannelDivinityPreserversRebuke());
		} else {
			setDivineCovenant(DivineCovenant.WRATH);
			pc.addPower(new ChannelDivinityArmorOfWrath());
		}
		
		pc.addPower(new ChannelDivinityRebukeUndead());
		
		// TODO: Divine Covenant, Ritual Casting
		Utils.print("NOTE: I have not yet coded Divine Covenant, Ritual Casting.");
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

	public DivineCovenant getDivineCovenant() {
		return divineCovenant;
	}

	public void setDivineCovenant(DivineCovenant divineCovenant) {
		this.divineCovenant = divineCovenant;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
