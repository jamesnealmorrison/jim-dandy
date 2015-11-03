package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.DailyPower;
import com.jimmie.util.Dice;
import com.jimmie.util.EncounterPower;
import com.jimmie.util.MinorAction;
import com.jimmie.util.StandardAction;
import com.jimmie.util.Utils;
import com.jimmie.domain.creatures.Character;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.domain.items.weapons.WeaponType;

public class Bard extends DndClass {
	/* TODO: Haven't implemented Words of friendship yet. */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MISDIRECTED_MARK = "Misdirected Mark";
	public static final String VICIOUS_MOCKERY = "Vicious Mockery";
	public static final String BLUNDER = "Blunder";
	public static final String MAJESTIC_WORD = "Majectic Word";
	public static final String WORDS_OF_FRIENDSHIP = "Words of Friendship";
	public static final String STIRRING_SHOUT = "Stirring Shout";
	private boolean usedBlunder;
	private int majesticWordUses;
	private boolean usedStirringShout;
	private BardicVirtue bardicVirtue;

	@Override
	public void initializeForEncounter() {
		usedBlunder = false;
		majesticWordUses = 0;
	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub
		usedStirringShout = false;
	}

	@StandardAction(menuName = MISDIRECTED_MARK, isBasicAttack = false, isMeleeAttack = false, isRangedAttack = true, martialTag = false, divineTag = false, primalTag = false, arcaneTag = true, weaponTag = false, psionicTag = false)
	@AtWillPower
	public void misdirectedMark(Encounter encounter) {
		AttackTarget target = encounter.chooseRangedTarget(owner, 10, 10);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA) + owner.getImplementAttackBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetReflex = target.getReflex(owner);
		Utils.print("Your target has a reflex of " + targetReflex);
		
		if (roll >= targetReflex) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = 1;
			DiceType damageDiceType = DiceType.EIGHT_SIDED;

			/* Book says at level 21 increase damage to 2d8. */
			if (owner.getLevel() >= 21) {
				damageRolls = damageRolls * 2;
			}
			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getImplementDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);

			/* The target is marked by an ally within 5 squares. */
			Creature misdirectedMarker = encounter.chooseAllyWithinRangeOf(owner, owner.getCurrentPosition(), 5);
			target.markByMisdirectedMark(this.owner, misdirectedMarker, DurationType.END_OF_NEXT_TURN);
			Utils.print(target.getName() + " is now marked by " + misdirectedMarker.getName() + " until the end of my next turn because I have Combat Challenge.");
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@StandardAction(menuName = VICIOUS_MOCKERY, isBasicAttack = false, isMeleeAttack = false, isRangedAttack = true, martialTag = false, divineTag = false, primalTag = false, arcaneTag = true, weaponTag = false, psionicTag = false)
	@AtWillPower
	public void viciousMockery(Encounter encounter) {
		AttackTarget target = encounter.chooseRangedTarget(owner, 10, 10);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA) + owner.getImplementAttackBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetWill = target.getWill(owner);
		Utils.print("Your target has a Will of " + targetWill);
		
		if (roll >= targetWill) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = 1;
			DiceType damageDiceType = DiceType.SIX_SIDED;

			/* Book says at level 21 increase damage to 2d8. */
			if (owner.getLevel() >= 21) {
				damageRolls = damageRolls * 2;
			}
			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getImplementDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);

			/* The target takes a -2 penalty to attack rolls until the end of my next turn. */
			target.setTemporaryAttackRollModifier(owner, DurationType.END_OF_NEXT_TURN, -2);
			Utils.print(target.getName() + " will take a -2 attack roll penalty until the end of " + owner.getName() + "'s next turn.");
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@StandardAction(menuName = BLUNDER, isBasicAttack = false, isMeleeAttack = false, isRangedAttack = true, martialTag = false, divineTag = false, primalTag = false, arcaneTag = true, weaponTag = false, psionicTag = false)
	@EncounterPower
	public void blunder(Encounter encounter) {
		if (!usedBlunder) {
			usedBlunder = true;
		AttackTarget target = encounter.chooseRangedTarget(owner, 5, 5);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA) + owner.getImplementAttackBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetWill = target.getWill(owner);
		Utils.print("Your target has a Will of " + targetWill);
		
		if (roll >= targetWill) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = 1;
			DiceType damageDiceType = DiceType.SIX_SIDED;

			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getImplementDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);

			/* I get to slide the target 2 squares and allow an ally to do a basic attack against them as a free action with a +2 power bonus. */
			Utils.print("You now get to slide " + target.getName() + " 2 squares and allow someone a free attack with a +2 bonus.");
			int distanceLeft = 2;
			boolean attackMade = false;
			
			while ((distanceLeft > 0) || (attackMade == false)) {
				Utils.print("What do you want to do?");
				List<String> validChoices = new ArrayList<String>();
				if (distanceLeft > 0) {
					Utils.print("Slide");
					validChoices.add("Slide");
				}
				if (attackMade == false) {
					Utils.print("Attack");
					validChoices.add("Attack");
				}
				Utils.print("Stop");
				validChoices.add("Stop");
				String choice = Utils.getValidInput(validChoices);
				if ("Slide".equalsIgnoreCase(choice)) {
					Utils.print("What direction do you want to slide them (N, E, S, W, NE, NW, SE, SW)?");
					List<String> validDirections = new ArrayList<String>();

					validDirections.add("N");
					validDirections.add("E");
					validDirections.add("S");
					validDirections.add("W");
					validDirections.add("NE");
					validDirections.add("NW");
					validDirections.add("SE");
					validDirections.add("SW");

					Utils.print("Your choice?");
					String direction = Utils.getValidInput(validDirections);
					target.move(direction, encounter);
					distanceLeft--;
				} else if ("Attack".equalsIgnoreCase(choice)) {
					/* Pick which ally will make the attack. */
					Utils.print("Please pick which ally will attack.");
					Creature attacker = encounter.chooseAnyAlly(owner);
					
					attacker.setTemporaryAttackRollModifier(owner, DurationType.IMMEDIATE, 2);
					Utils.print("Make sure to pick me (" + target.getName() + ") when it asks who to attack.");
					/* Should be able to cast the marker to a character. */
					if (Character.class.isInstance(attacker)) {
						((Character) (attacker)).basicMeleeAttack(encounter);
					}
				} else if ("Stop".equalsIgnoreCase(choice)) {
					break;
				}
			}

		} else {
			Utils.print("You missed " + target.getName());
		}
		} else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Blunder in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			owner.setUsedStandardAction(false);
		}
	}

	@MinorAction(menuName = MAJESTIC_WORD)
	@EncounterPower
	public void majesticWord(Encounter encounter) {
		if (majesticWordUses < 2) {
			majesticWordUses++;
			Utils.print("Remember you can only uses this once per round, even if you can use it twice in an encounter.  But I didn't code that so you are on the honor system.");
			int range = 0;
			
			if (owner.getLevel() < 11) {
				range = 5;
			} else if (owner.getLevel() < 21) {
				range = 10;
			} else {
				range = 15;
			}

			Character target = (Character) encounter.chooseAllyWithinRangeOf(owner, owner.getCurrentPosition(), range);
			
			target.useHealingSurge();
			int extraRolls = 0;
			
			int extraHitPoints = owner.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA);
			if (owner.getLevel() < 6) {
				/* Don't add anything else. */
				extraRolls = 0;
			} else if (owner.getLevel() < 11) {
				extraRolls = 1;
			} else if (owner.getLevel() < 16) {
				extraRolls = 2;
			} else if (owner.getLevel() < 21) {
				extraRolls = 3;
			} else if (owner.getLevel() < 26) {
				extraRolls = 4;
			} else {
				extraRolls = 5;
			}

			for (int i = 0; i < extraRolls; i++) {
			    Dice d = new Dice(DiceType.SIX_SIDED);
     			extraHitPoints = extraHitPoints + d.basicRoll();
			}		
			
			target.heal(extraHitPoints);
			Utils.print("Gave them an extra " + extraHitPoints + " extra hit points.");
			
			Utils.print("You can also slide " + target.getName() + ".  Do you want to?");
			
			String choice = Utils.getYesOrNoInput();
			
			if ("Y".equalsIgnoreCase(choice)) {
				Utils.print("What direction do you want to slide them (N, E, S, W, NE, NW, SE, SW)?");
				List<String> validDirections = new ArrayList<String>();

				validDirections.add("N");
				validDirections.add("E");
				validDirections.add("S");
				validDirections.add("W");
				validDirections.add("NE");
				validDirections.add("NW");
				validDirections.add("SE");
				validDirections.add("SW");

				Utils.print("Your choice?");
				String direction = Utils.getValidInput(validDirections);
				target.move(direction, encounter);
			}
			
		} else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Majestic Word twice in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			owner.setUsedMinorAction(false);
		}
	}

	@StandardAction(menuName = STIRRING_SHOUT, isBasicAttack = false, isMeleeAttack = false, isRangedAttack = true, martialTag = false, divineTag = false, primalTag = false, arcaneTag = true, weaponTag = false, psionicTag = false)
	@DailyPower
	public void stirringShout(Encounter encounter) {
		if (!usedStirringShout) {
			usedStirringShout = true;
		AttackTarget target = encounter.chooseRangedTarget(owner, 10, 10);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA) + owner.getImplementAttackBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetWill = target.getWill(owner);
		Utils.print("Your target has a Will of " + targetWill);
		
		if (roll >= targetWill) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = 2;
			DiceType damageDiceType = DiceType.SIX_SIDED;

			/* Book says at level 21 increase damage to 2d8. */
			if (owner.getLevel() >= 21) {
				damageRolls = damageRolls * 2;
			}
			/* TODO: Supposed to be psychic damage.  Haven't implemented that yet. */
			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getImplementDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), owner.getRace()), DamageType.PSYCHIC_DAMAGE, encounter, true);

			target.hitByStirringShout(owner.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA));
		} else {
			Utils.print("You missed " + target.getName());
		}
		} else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Stirring Shout in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			owner.setUsedStandardAction(false);			
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
		
		Utils.print("Adding bonus of +1 Reflex, +1 Will");
		if (pc.getReflexMisc1() == 0) {
			pc.setReflexMisc1(1);
		} else {
			pc.setReflexMisc2(pc.getReflexMisc2() + 1);
		}

		if (pc.getWillMisc1() == 0) {
			pc.setWillMisc1(1);
		} else {
			pc.setWillMisc2(pc.getWillMisc2() + 1);
		}

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
		
		// TODO: Bardic Training, Bardic Virtue, Majestic Word, Multiclass Versatility, Skill Versatility, Song of Rest, Words of Friendship
		Utils.print("NOTE: I have not yet coded Bardic Training, Bardic Virtue, Majestic Word, Multiclass Versatility, Skill Versatility, Song of Rest, Words of Friendship.");
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
}
