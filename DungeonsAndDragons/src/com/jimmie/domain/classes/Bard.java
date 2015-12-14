package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.ImplementType;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.util.Utils;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.domain.items.weapons.WeaponType;
import com.jimmie.powers.MajesticWord;
import com.jimmie.powers.WordsOfFriendship;

public class Bard extends DndClass {
	/* TODO: Haven't implemented Words of friendship yet. */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BardicVirtue bardicVirtue;

	@Override
	public void initializeForEncounter() {
	}

	@Override
	public void initializeForNewDay() {
	}


	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Add automatic trained skill(s).
		trainedSkills.add("Arcana");
		Utils.print("Automatically trained in Arcana.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Bluff");
		choices.add("Diplomacy");
		choices.add("Dungeoneering");
		choices.add("Heal");
		choices.add("History");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Nature");
		choices.add("Perception");
		choices.add("Religion");
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
		
		Utils.print("Setting power Source to Arcane.");
		pc.setPowerSource(PowerSource.ARCANE);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Chainmail");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.CHAINMAIL);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Longsword, Scimitar, Short Sword, Simple Ranged, Military Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponTypeProficiency(WeaponType.LONGSWORD);
		pc.addWeaponTypeProficiency(WeaponType.SCIMITAR);
		pc.addWeaponTypeProficiency(WeaponType.SHORTSWORD);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_RANGED);
		
		Utils.print("Adding Implement Proficiencies: Wands");
		pc.addImplementProficiency(ImplementType.WAND);

		Utils.print("Adding bonus of +1 Reflex, +1 Will");
		setReflexBonus(getReflexBonus() + 1);
		setWillBonus(getWillBonus() + 1);

		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Cunning Bard. You seek to excape danger using your wits, tricking your foes and concocting cunning strategems.");
		Utils.print("2. Valorous Bard. You have courage in the face of overwhelming odds.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Charisma your primary score and Intelligence your secondary score. Constitution is third.");
			Utils.print("Suggested Class Feature: Virtue or Cunning");
			Utils.print("Suggested Feat: Advantage of Cunning");
			Utils.print("Suggested Skills: Arcana, Bluff, Intimidate, Perception, Streetwise");
			Utils.print("Suggested At-Will Powers: Misdirected Mark, Vicious Mockery");
			Utils.print("Suggested Encounter Power: Blunder");
			Utils.print("Suggested Daily Power: Stirring Shout");
		} else {
			Utils.print("Make Charisma your primary score and Constitution your secondary score.  Intelligence is third.");
			Utils.print("Suggested Class Feature: Virtue of Valor");
			Utils.print("Suggested Feat: Strength of Valor");
			Utils.print("Suggested Skills: Arcana, Athletics, Diplomacy, Intimidate, Perception");
			Utils.print("Suggested At-Will Powers: Guiding Strike, War Song Strike");
			Utils.print("Suggested Encounter Power: Shout of Triumph");
			Utils.print("Suggested Daily Power: Slayer's Song");
		}
		
		Utils.print("Choose between the following Bardic Virtues:");
		Utils.print("1. Virtue of Cunning.");
		Utils.print("2. Virtue of Valor.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setBardicVirtue(BardicVirtue.VIRTUE_OF_CUNNING);
		} else {
			setBardicVirtue(BardicVirtue.VIRTUE_OF_VALOR);
		}
		
		pc.addPower(new MajesticWord());
		pc.addPower(new WordsOfFriendship());
		
		// TODO: Bardic Training, Bardic Virtue, Multiclass Versatility, Skill Versatility, Song of Rest
		Utils.print("NOTE: I have not yet coded Bardic Training, Bardic Virtue, Multiclass Versatility, Skill Versatility, Song of Rest.");
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

	public BardicVirtue getBardicVirtue() {
		return bardicVirtue;
	}

	public void setBardicVirtue(BardicVirtue bardicVirtue) {
		this.bardicVirtue = bardicVirtue;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
