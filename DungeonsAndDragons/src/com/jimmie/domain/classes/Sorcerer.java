package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.ImplementType;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class Sorcerer extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SorcererSpellSource spellSource;
	private int firstAttackRoll;
	private int unfetteredPower;
	private boolean lastAttackRollEven;

	public boolean getLastAttackRollEven() {
		return lastAttackRollEven;
	}

	public void setLastAttackRollEven(boolean lastAttackRollEven) {
		this.lastAttackRollEven = lastAttackRollEven;
	}

	public int getUnfetteredPower() {
		return unfetteredPower;
	}

	public void setUnfetteredPower(int unfetteredPower) {
		this.unfetteredPower = unfetteredPower;
	}

	public int getFirstAttackRoll() {
		return firstAttackRoll;
	}

	public void setFirstAttackRoll(int firstAttackRoll) {
		this.firstAttackRoll = firstAttackRoll;
	}

	@Override
	public void initializeForEncounter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeForNewDay(DndCharacter dndCharacter) {
		if (this.getSpellSource() == SorcererSpellSource.WILD_MAGIC) {
			// Wild Soul
			Dice d = new Dice(DiceType.TEN_SIDED);
			int roll = d.roll(DiceRollType.WILD_SOUL);
			switch (roll) {
			case 1 :
				Utils.print("You will have 5 damage resistance to ACID until the end of your next extended rest.");
				dndCharacter.setTemporaryDamageResistance(5, dndCharacter.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, dndCharacter, TemporaryEffectType.DMG_RESIST, TemporaryEffectReason.WILD_MAGIC, DamageType.ACID);
				break;
			case 2 :
				Utils.print("You will have 5 damage resistance to COLD until the end of your next extended rest.");
				dndCharacter.setTemporaryDamageResistance(5, dndCharacter.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, dndCharacter, TemporaryEffectType.DMG_RESIST, TemporaryEffectReason.WILD_MAGIC, DamageType.COLD);
				break;
			case 3 :
				Utils.print("You will have 5 damage resistance to FIRE until the end of your next extended rest.");
				dndCharacter.setTemporaryDamageResistance(5, dndCharacter.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, dndCharacter, TemporaryEffectType.DMG_RESIST, TemporaryEffectReason.WILD_MAGIC, DamageType.FIRE);
				break;
			case 4 :
				Utils.print("You will have 5 damage resistance to FORCE until the end of your next extended rest.");
				dndCharacter.setTemporaryDamageResistance(5, dndCharacter.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, dndCharacter, TemporaryEffectType.DMG_RESIST, TemporaryEffectReason.WILD_MAGIC, DamageType.FORCE);
				break;
			case 5 :
				Utils.print("You will have 5 damage resistance to LIGHTNING until the end of your next extended rest.");
				dndCharacter.setTemporaryDamageResistance(5, dndCharacter.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, dndCharacter, TemporaryEffectType.DMG_RESIST, TemporaryEffectReason.WILD_MAGIC, DamageType.LIGHTNING);
				break;
			case 6 :
				Utils.print("You will have 5 damage resistance to NECROTIC until the end of your next extended rest.");
				dndCharacter.setTemporaryDamageResistance(5, dndCharacter.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, dndCharacter, TemporaryEffectType.DMG_RESIST, TemporaryEffectReason.WILD_MAGIC, DamageType.NECROTIC);
				break;
			case 7 :
				Utils.print("You will have 5 damage resistance to POISON until the end of your next extended rest.");
				dndCharacter.setTemporaryDamageResistance(5, dndCharacter.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, dndCharacter, TemporaryEffectType.DMG_RESIST, TemporaryEffectReason.WILD_MAGIC, DamageType.POISON);
				break;
			case 8 :
				Utils.print("You will have 5 damage resistance to PSYCHIC until the end of your next extended rest.");
				dndCharacter.setTemporaryDamageResistance(5, dndCharacter.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, dndCharacter, TemporaryEffectType.DMG_RESIST, TemporaryEffectReason.WILD_MAGIC, DamageType.PSYCHIC);
				break;
			case 9 :
				Utils.print("You will have 5 damage resistance to RADIANT until the end of your next extended rest.");
				dndCharacter.setTemporaryDamageResistance(5, dndCharacter.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, dndCharacter, TemporaryEffectType.DMG_RESIST, TemporaryEffectReason.WILD_MAGIC, DamageType.RADIANT);
				break;
			case 10 :
				Utils.print("You will have 5 damage resistance to THUNDER until the end of your next extended rest.");
				dndCharacter.setTemporaryDamageResistance(5, dndCharacter.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, dndCharacter, TemporaryEffectType.DMG_RESIST, TemporaryEffectReason.WILD_MAGIC, DamageType.THUNDER);
				break;
			}
		}
	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Add automatic trained skill(s).
		trainedSkills.add("Arcana");
		Utils.print("Automatically trained in Arcana.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Athletics");
		choices.add("Bluff");
		choices.add("Diplomacy");
		choices.add("Dungeoneering");
		choices.add("Endurance");
		choices.add("History");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Nature");
		
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
		Utils.print("Setting role to Striker.");
		pc.setRole(Role.STRIKER);
		
		Utils.print("Setting power Source to Arcane.");
		pc.setPowerSource(PowerSource.ARCANE);

		Utils.print("Adding Armor Proficiencies: Cloth");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding Implement Proficiencies: Daggers, Staffs");
		pc.addImplementProficiency(ImplementType.DAGGER);
		pc.addImplementProficiency(ImplementType.STAFF);

		Utils.print("Adding bonus of +2 Will");
		setWillBonus(getWillBonus() + 2);

		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Chaos Sorcerer: You can be reckless and hard to control.");
		Utils.print("2. Dragon Sorcerer: You command the ancient arcane power that flows through dragons.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Charisma your primary score and Dexterity your secondary score.");
			Utils.print("Suggested Class Feature: Wild Magic");
			Utils.print("Suggested Feat: Arcane Spellfury");
			Utils.print("Suggested Skills: Arcana, Bluff, Endurance, Insight");
			Utils.print("Suggested At-Will Powers: Chaos Bolt, Storm Walk");
			Utils.print("Suggested Encounter Power: Bedeviling Burst");
			Utils.print("Suggested Daily Power: Dazzling Ray");
		} else {
			Utils.print("Make Charisma your primary score and Strength your secondary score.");
			Utils.print("Suggested Class Feature: Dragon Magic");
			Utils.print("Suggested Feat: Implement Expertise");
			Utils.print("Suggested Skills: Arcana, Athletics, History, Intimidate");
			Utils.print("Suggested At-Will Powers: Burning Spray, Dragonfrost");
			Utils.print("Suggested Encounter Power: Tempest Breath");
			Utils.print("Suggested Daily Power: Lightning Breath");
		}
		
		Utils.print("Choose between the following Spell Source:");
		Utils.print("1. Dragon Magic");
		Utils.print("2. Wild Magic.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setSpellSource(SorcererSpellSource.DRAGON_MAGIC);
		} else {
			setSpellSource(SorcererSpellSource.WILD_MAGIC);
		}
		
		// TODO: Spell Source
		Utils.print("NOTE: I have not yet coded Spell Source.");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 12 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 68 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public SorcererSpellSource getSpellSource() {
		return spellSource;
	}

	public void setSpellSource(SorcererSpellSource spellSource) {
		this.spellSource = spellSource;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

}
