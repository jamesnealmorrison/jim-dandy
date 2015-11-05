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
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.DailyPower;
import com.jimmie.util.Dice;
import com.jimmie.util.EncounterPower;
import com.jimmie.util.MinorAction;
import com.jimmie.util.StandardAction;
import com.jimmie.util.Utils;

public class Warden extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String STRENGTH_OF_STONE = "Strength of Stone";
	public static final String EARTH_SHIELD_STRIKE = "Earth Shield Strike";
	public static final String WARDENS_FURY = "Warden's Fury";
	public static final String THUNDER_RAM_ASSAULT = "Thunder Ram Assault";
	public static final String WARDENS_GRASP = "Warden's Grasp";
	public static final String FORM_OF_THE_WILLOW_SENTINEL = "Form of the Willow Sentinel";
	public static final String EARTH_STRENGTH = "Earth Strength";
	private boolean usedThunderRamAssault;
	private boolean usedFormOfTheWillowSentinel;
	private boolean usedFormOfTheWillowSentinelAttack;
	private boolean usingFormOfTheWillowSentinel;
	private GuardianMight guardianMight;

	@Override
	public void initializeForEncounter() {
		usedThunderRamAssault = false;
		usedFormOfTheWillowSentinel = false;
	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub
		
	}


	@StandardAction(menuName = STRENGTH_OF_STONE, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = false, divineTag = false, weaponTag = true, arcaneTag = false, primalTag = true, psionicTag = false)
	@AtWillPower
	public void strengthOfStone(Encounter encounter) {
		AttackTarget target = encounter.chooseMeleeTarget(owner, owner.getReadiedWeapon().getNormalRange());
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(owner);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

			/* Book says at level 21 increase damage to 2[W]. */
			if (owner.getLevel() >= 21) {
				damageRolls = damageRolls * 2;
			}
			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			
			Utils.print(owner.getName() + " gets 3 temporary HP");
			/* Only do this if they have < 3 already.  Otherwise we are removing temp hit points they already had. */
			if (owner.getTemporaryHitPoints() < 3) {
			   owner.setTemporaryHitPoints(3);
			}
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@StandardAction(menuName = EARTH_SHIELD_STRIKE, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = false, divineTag = false, weaponTag = true, arcaneTag = false, primalTag = true, psionicTag = false)
	@AtWillPower
	public void earthShieldStrike(Encounter encounter) {
		AttackTarget target = encounter.chooseMeleeTarget(owner, owner.getReadiedWeapon().getNormalRange());
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(owner);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

			/* Book says at level 21 increase damage to 2[W]. */
			if (owner.getLevel() >= 21) {
				damageRolls = damageRolls * 2;
			}
			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			
			/* I get an AC bonus of +1 until the end of my next turn. */
			Utils.print("Adding a +1 bonus to AC until the end of my next turn.");
			owner.setTemporaryArmorClassBonus(1, owner.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, owner);
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	/* This does not have annotation.  It gets called directly. */
	public void wardensFury(Encounter encounter, AttackTarget target) {
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetFortitude = target.getFortitude(encounter, owner);
		Utils.print("Your target has an Fortitude of " + targetFortitude);
		
		if (roll >= targetFortitude) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

			/* Book says at level 21 increase damage to 2[W]. */
			if (owner.getLevel() >= 21) {
				damageRolls = damageRolls * 2;
			}
			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			
			Utils.print(target.getName() + " grants combat advantage to " + owner.getName() + " and allies until the end of " + owner.getName() + "'s next turn.");
			target.grantCombatAdvantageViaWardensFury(owner);
			Utils.print("This is not completely implemented yet, though.");
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	/* This does not have annotation.  It gets called directly. */
	public int formOfTheWillowSentinelAttack(Encounter encounter, AttackTarget target) {
		setUsedFormOfTheWillowSentinelAttack(true);
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(owner);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			
			Utils.print(target.getName() + " gets a -4 penalty to the attack roll.");
			return -4;
		} else {
			Utils.print("You missed " + target.getName() + ".  Doing half damage.");
			int damageRolls = owner.getReadiedWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

			target.hurt(Utils.rollForHalfDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, false);
			
			Utils.print(target.getName() + " gets a -2 penalty to the attack roll.");
			return -2;
		}
	}

	@StandardAction(menuName = THUNDER_RAM_ASSAULT, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = false, divineTag = false, weaponTag = true, arcaneTag = false, primalTag = true, psionicTag = false)
	@EncounterPower
	public void thunderRamAssault(Encounter encounter) {
		if (!usedThunderRamAssault) {
			usedThunderRamAssault = true;
		AttackTarget target = encounter.chooseMeleeTarget(owner, owner.getReadiedWeapon().getNormalRange());
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter);
		
		Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
		
		int targetArmorClass = target.getArmorClass(owner);
		Utils.print("Your target has an AC of " + targetArmorClass);
		
		if (roll >= targetArmorClass) {
			/* A HIT! */
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

			/* TODO: Supposed to be THUNDER damage.  Haven't implemented that yet. */
			target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), owner.getRace()), DamageType.THUNDER_DAMAGE, encounter, true);
			
			/* If you chose the Earth Strength build, you can push the primary target. */
			if (getMyOptions().contains(Warden.EARTH_STRENGTH)) {
				String pushDirection = encounter.getPushDirection(owner.getCurrentPosition(), target.getCurrentPosition());
				for (int i = 0; i < owner.getAbilityModifierPlusHalfLevel(AbilityType.CONSTITUTION); i++) {
				    target.push(pushDirection);
				}
			}
			
			int lowerLeftX = 0;
			int lowerLeftY = 0;
			
			Utils.print("Please enter the lower left X coordinate of the blast. No validation is done here, so do it right!");
			lowerLeftX = Utils.getValidIntInputInRange(1, 50);
			
			Utils.print("Please enter the lower left Y coordinate of the blast. No validation is done here, so do it right!");
			lowerLeftY = Utils.getValidIntInputInRange(1, 50);
			
			List<Creature> blastTargets = encounter.getAllCreaturesInBlast(lowerLeftX, lowerLeftY, 3);
			
     		Dice secondaryDice = new Dice(DiceType.SIX_SIDED);
     		int secondaryDamage = secondaryDice.basicRoll();
			
			for (Creature secondaryTarget : blastTargets) {
				List<AttackTarget> secondaryTargets = new ArrayList<AttackTarget>();
				secondaryTargets.add(secondaryTarget);
				d = new Dice(DiceType.TWENTY_SIDED);
				diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
				roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(secondaryTargets, encounter);
				
				Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);
				
				int secondaryTargetFortitude = secondaryTarget.getFortitude(encounter, owner);
				Utils.print("Your secondary target has an fortitude of " + secondaryTargetFortitude);
				
				if (roll >= secondaryTargetFortitude) {
					/* A HIT! */
					Utils.print("You successfully hit " + secondaryTarget.getName());

					/* See if this target was hit by Stirring Shout. */
					if (secondaryTarget.isHitByStirringShout()) {
						Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
						owner.heal(target.getStirringShoutCharismaModifier());
					}

			
					secondaryTarget.hurt(secondaryDamage, DamageType.THUNDER_DAMAGE, encounter, true);
					
					/* If you chose the Earth Strength build, you can push the primary target. */
					String pushDirection = encounter.getPushDirection(owner.getCurrentPosition(), secondaryTarget.getCurrentPosition());
				    secondaryTarget.push(pushDirection);
				} else {
				    Utils.print("Sorry.  You missed " + secondaryTarget.getName());
				}
			}
			
		} else {
			Utils.print("You missed " + target.getName());
		}
		}else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Thunder Ram Assault in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			owner.setUsedStandardAction(false);
		}
	}

	@MinorAction(menuName = FORM_OF_THE_WILLOW_SENTINEL)
	@DailyPower
	public void formOfTheWillowSentinel(Encounter encounter) {
		if (!usedFormOfTheWillowSentinel) {
			usedFormOfTheWillowSentinel = true;

			setUsingFormOfTheWillowSentinel(true);
			
		}else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Form of the Willow Sentinel today.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			owner.setUsedMinorAction(false);
		}
	}

	public boolean isUsingFormOfTheWillowSentinel() {
		return usingFormOfTheWillowSentinel;
	}

	public void setUsingFormOfTheWillowSentinel(boolean usingFormOfTheWillowSentinel) {
		this.usingFormOfTheWillowSentinel = usingFormOfTheWillowSentinel;
	}

	public boolean isUsedFormOfTheWillowSentinelAttack() {
		return usedFormOfTheWillowSentinelAttack;
	}

	public void setUsedFormOfTheWillowSentinelAttack(
			boolean usedFormOfTheWillowSentinelAttack) {
		this.usedFormOfTheWillowSentinelAttack = usedFormOfTheWillowSentinelAttack;
	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Add automatic trained skill(s).
		trainedSkills.add("Nature");
		Utils.print("Automatically trained in Nature.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Athletics");
		choices.add("Dungeoneering");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Intimidate");
		choices.add("Perception");
		
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
		
		Utils.print("Setting power Source to Primal.");
		pc.setPowerSource(PowerSource.PRIMAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Light Shield, Heavy Shield");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.LIGHT_SHIELD);
		pc.addArmorGroupProficiency(ArmorGroup.HEAVY_SHIELD);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Military Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding bonus of +1 Fortitude, +1 Will");
		if (pc.getFortitudeMisc1() == 0) {
			pc.setFortitudeMisc1(1);
		} else {
			pc.setFortitudeMisc2(pc.getFortitudeMisc2() + 1);
		}

		if (pc.getWillMisc1() == 0) {
			pc.setWillMisc1(1);
		} else {
			pc.setWillMisc2(pc.getWillMisc2() + 1);
		}

		Utils.print("Setting hit points per level gained = 7");
		pc.setHitPointsPerLevelGained(7);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Earth Warden: You draw primal power through a link with the land, and the power of the earth strengthens and sustains you.");
		Utils.print("2. Wild Warden: Your blood is a medium of primal power; nature's vigor pulses in your veins.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Strength your primary score and Constitution your secondary score.");
			Utils.print("Suggested Class Feature: Earthstrength");
			Utils.print("Suggested Feat: Crushing Earthstrength");
			Utils.print("Suggested Skills: Athletics, Heal, Nature, Perception");
			Utils.print("Suggested At-Will Powers: Earth Shield Strike, Strength of Stone");
			Utils.print("Suggested Encounter Power: Thunder Ram Assault");
			Utils.print("Suggested Daily Power: Form of the Willow Sentinel");
		} else {
			Utils.print("Make Strength your primary score and Wisdom your secondary score.");
			Utils.print("Suggested Class Feature: Wildblood");
			Utils.print("Suggested Feat: Wildblood Cunning");
			Utils.print("Suggested Skills: Athletics, Intimidate, Nature, Perception");
			Utils.print("Suggested At-Will Powers: Thorn Strike, Weight of Earth");
			Utils.print("Suggested Encounter Power: Wildblood Frenzy");
			Utils.print("Suggested Daily Power: Form of the Relentless Panther");
		}
		
		Utils.print("Choose between the following Guardian Mights:");
		Utils.print("1. Earthstrength.");
		Utils.print("2. Wildblood.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setGuardianMight(GuardianMight.EARTHSTRENGTH);
		} else {
			setGuardianMight(GuardianMight.WILDBLOOD);
		}
		
		// TODO: Font of Life, Guardian Might, Nature's Wrath 
		Utils.print("NOTE: I have not yet coded Font of Life, Guardian Might, Nature's Wrath.");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 17 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 9 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public GuardianMight getGuardianMight() {
		return guardianMight;
	}

	public void setGuardianMight(GuardianMight guardianMight) {
		this.guardianMight = guardianMight;
	}
}
