package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.powers.BattleResilience;
import com.jimmie.powers.BattlemindsDemand;
import com.jimmie.powers.BlurredStep;
import com.jimmie.powers.MindSpike;
import com.jimmie.powers.SpeedOfThought;
import com.jimmie.util.Utils;

public class Battlemind extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PsionicStudy psionicStudy;

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
		choices.add("Athletics");
		choices.add("Bluff");
		choices.add("Diplomacy");
		choices.add("Endurance");
		choices.add("Heal");
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
		
		Utils.print("Setting power Source to Psionic.");
		pc.setPowerSource(PowerSource.PSIONIC);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Chainmail, Scale, Light Shield, Heavy Shield");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.CHAINMAIL);
		pc.addArmorGroupProficiency(ArmorGroup.SCALE);
		pc.addArmorGroupProficiency(ArmorGroup.LIGHT_SHIELD);
		pc.addArmorGroupProficiency(ArmorGroup.HEAVY_SHIELD);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Military Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding bonus of +2 Will");
		setWillBonus(getWillBonus() + 2);

		Utils.print("Setting hit points per level gained = 6");
		pc.setHitPointsPerLevelGained(6);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Resilient Battlemind: You use your psionic power to transform your body, altering it to take on the qualities of iron or stone.");
		Utils.print("2. Quick Battlemind:  You use your psionic power to make yourself faster, increasing your speed and agility in combat.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Constitution your primary score and Wisdom your secondary score. Charisma is third.");
			Utils.print("Suggested Class Feature: Battle Resilience");
			Utils.print("Suggested Feat: Decptive Mind");
			Utils.print("Suggested Skills: Arcana, Endurance, Intimidate");
			Utils.print("Suggested At-Will Powers: Iron Fist, Bull's Strength");
			Utils.print("Suggested Daily Power: Aspect of Elevated Harmony");
		} else {
			Utils.print("Make Constitution your primary score and Charisma your secondary score.  Wisdom is third.");
			Utils.print("Suggested Class Feature: Speed of Thought");
			Utils.print("Suggested Feat: Improved Speed of Thought");
			Utils.print("Suggested Skills: Arcana, Athletics, Insight");
			Utils.print("Suggested At-Will Powers: Demon Dance, Whirling Defense");
			Utils.print("Suggested Daily Power: Steel Unity Strike");
		}
		
		Utils.print("Choose between the following Psionic Studies:");
		Utils.print("1. Battle Resilience.");
		Utils.print("2. Speed of Thought.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setPsionicStudy(PsionicStudy.BATTLE_RESILIENCE);
			pc.addPower(new BattleResilience());
		} else {
			setPsionicStudy(PsionicStudy.SPEED_OF_THOUGHT);
			pc.addPower(new SpeedOfThought());
		}
		
		pc.addPower(new BattlemindsDemand());
		pc.addPower(new BlurredStep());
		pc.addPower(new MindSpike());
		
		// TODO: Psionic Augmentation, Psionic Defense, Psionic Study
		Utils.print("NOTE: I have not yet coded Psionic Augmentation, Psionic Defense, Psionic Study.");
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

	public PsionicStudy getPsionicStudy() {
		return psionicStudy;
	}

	public void setPsionicStudy(PsionicStudy psionicStudy) {
		this.psionicStudy = psionicStudy;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
