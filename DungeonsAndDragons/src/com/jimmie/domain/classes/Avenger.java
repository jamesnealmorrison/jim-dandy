package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.DailyPower;
import com.jimmie.util.Dice;
import com.jimmie.util.EncounterPower;
import com.jimmie.util.MinorAction;
import com.jimmie.util.StandardAction;
import com.jimmie.util.Utils;

public class Avenger extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String BOND_OF_PURSUIT = "Bond of Pursuit";
	public static final String RADIANT_VENGEANCE = "Radiant Vengeance";
	public static final String ANGELIC_ALACRITY = "Angelic Alacrity";
	public static final String OATH_OF_ENMITY = "Oath of Enmity";
	public static final String ABJURE_UNDEAD = "Abjure Undead";
	public static final String DIVINE_GUIDANCE = "Divine Guidance";
	public static final String ASPECT_OF_MIGHT = "Aspect of Might";
	public static final String CENSURE_OF_PURSUIT = "Censure of Pursuit";
	private Object censure;
	private boolean usedAngelicAlacrity;
	private boolean usedOathOfEnmity;
	private boolean usedChannelDivinity;
	private boolean usedAspectOfMight;
	public boolean isUsedChannelDivinity() {
		return usedChannelDivinity;
	}

	public void setUsedChannelDivinity(boolean usedChannelDivinity) {
		this.usedChannelDivinity = usedChannelDivinity;
	}

	private AttackTarget oathOfEnmityTarget;
	private boolean aspectOfMightEncounterBonus;

	public boolean isAspectOfMightEncounterBonus() {
		return aspectOfMightEncounterBonus;
	}

	public void setAspectOfMightEncounterBonus(boolean aspectOfMightEncounterBonus) {
		this.aspectOfMightEncounterBonus = aspectOfMightEncounterBonus;
	}

	public AttackTarget getOathOfEnmityTarget() {
		return oathOfEnmityTarget;
	}

	public void setOathOfEnmityTarget(AttackTarget oathOfEnmityTarget) {
		this.oathOfEnmityTarget = oathOfEnmityTarget;
	}

	public Object getCensure() {
		return censure;
	}

	public void setCensure(Object censure) {
		this.censure = censure;
	}

	@Override
	public void initializeForEncounter() {
		usedAngelicAlacrity = false;
		usedOathOfEnmity = false;
		usedChannelDivinity = false;
		aspectOfMightEncounterBonus = false;
	}

	@StandardAction(menuName = BOND_OF_PURSUIT, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = false, divineTag = true, weaponTag = true, arcaneTag = false, primalTag = false, psionicTag = false)
	@AtWillPower
	public void bondOfPursuit(Encounter encounter) {
		AttackTarget target = encounter.chooseMeleeTarget(owner, owner.getReadiedWeapon().getNormalRange());
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());

		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter);
		
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
			
			if (this.aspectOfMightEncounterBonus == false) {
    		  target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			} else {
                target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus()+2, owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
				Utils.print("You got an aspect of might bonus of two to this damage roll.");
			}
			
			target.hitByBondOfPursuit(this.getOwner());
			
			
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@StandardAction(menuName = RADIANT_VENGEANCE, isBasicAttack = false, isMeleeAttack = false, isRangedAttack = true, martialTag = false, divineTag = true, weaponTag = false, arcaneTag = false, primalTag = false, psionicTag = false)
	@AtWillPower
	public void radiantVengeance(Encounter encounter) {
		AttackTarget target = encounter.chooseRangedTarget(owner, 10, 10);
			
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM) + owner.getOtherAttackModifier(targets, encounter);
		
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

			/* Book says at level 21 increase damage to 2[W]. */
			if (owner.getLevel() >= 21) {
				damageRolls = 2;
			}
			if (this.aspectOfMightEncounterBonus == false) {
				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, 0, owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			} else {
				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, 2, owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
				Utils.print("You got an aspect of might bonus of two to this damage roll.");
			}

			Utils.print(owner.getName() + " gets " + owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM) + " temporary HP");
			/* Only do this if they have < owner.getWisdomModifier() already.  Otherwise we are removing temp hit points they already had. */
			if (owner.getTemporaryHitPoints() < owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM)) {
			   owner.setTemporaryHitPoints(owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM));
			}
			
		} else {
			Utils.print("You missed " + target.getName());
		}
	}

	@StandardAction(menuName = ANGELIC_ALACRITY, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = false, divineTag = true, weaponTag = true, arcaneTag = false, primalTag = false, psionicTag = false)
	@EncounterPower
	public void angelicAlacrity(Encounter encounter) {
		if (!usedAngelicAlacrity) {
			usedAngelicAlacrity = true;
		int shiftDistance;
		/* In this attack, you can shift 2 squares first (or 1 + dex mod if Censure of Pursuit). */
		if (CENSURE_OF_PURSUIT.equals(censure)) {
			shiftDistance = 1 + owner.getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY);
		} else {
			shiftDistance = 2;
		}
		System.out.println("You may shift " + shiftDistance + " before the attack.");
		owner.shift(shiftDistance, true, encounter);
		
		AttackTarget target = encounter.chooseMeleeTarget(owner, owner.getReadiedWeapon().getNormalRange());
		
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter);
		
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

     		damageRolls = damageRolls * 2;

			if (this.aspectOfMightEncounterBonus == false) {
	     		target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			} else {
	     		target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus()+2, owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
				Utils.print("You got an aspect of might bonus of two to this damage roll.");
			}
			
		} else {
			Utils.print("You missed " + target.getName());
		}
		} else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Angelic Alacrity in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			owner.setUsedStandardAction(false);
		}
	}

	@MinorAction(menuName = OATH_OF_ENMITY)
	@EncounterPower
	public void oathOfEnmity(Encounter encounter) {
		if (!usedOathOfEnmity) {
			usedOathOfEnmity = true;

		/* This is supposed to be a close burst 10, but the ranged will work for it. */
		AttackTarget target = encounter.chooseRangedTarget(owner, 10, 10);
		
		oathOfEnmityTarget = target;

		} else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Oath of Enmity in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			owner.setUsedMinorAction(false);
		}
	}

	/* TODO: Not going to implement "Abjure undead" until we are facing undead enemies. */
	
	@StandardAction(menuName = ASPECT_OF_MIGHT, isBasicAttack = false, isMeleeAttack = true, isRangedAttack = false, martialTag = false, divineTag = true, weaponTag = true, arcaneTag = false, primalTag = false, psionicTag = false)
	@DailyPower
	public void aspectOfMight(Encounter encounter) {
		if (!usedAspectOfMight) {
			usedAspectOfMight = true;
		AttackTarget target = encounter.chooseMeleeTarget(owner, owner.getReadiedWeapon().getNormalRange());
		
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		targets.add(target);
		Dice d = new Dice(DiceType.TWENTY_SIDED);
		int diceRoll = d.attackRoll(owner, target, encounter, owner.getCurrentPosition());
		int roll = diceRoll + owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM) + owner.getWeaponProficiencyBonus() + owner.getOtherAttackModifier(targets, encounter);
		
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

     		damageRolls = damageRolls * 3;

     		target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, true);
			
		} else {
			Utils.print("You missed " + target.getName() + ".  But you still do half damage.");
			Utils.print("You successfully hit " + target.getName());

			/* See if this target was hit by Stirring Shout. */
			if (target.isHitByStirringShout()) {
				Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
				owner.heal(target.getStirringShoutCharismaModifier());
			}

			int damageRolls = owner.getReadiedWeapon().getDamageRolls();
			DiceType damageDiceType = owner.getReadiedWeapon().getDamageDice();

     		damageRolls = damageRolls * 3;

     		target.hurt(Utils.rollForHalfDamage(damageRolls, damageDiceType, owner.getReadiedWeapon().getDamageBonus(), owner.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), owner.getRace()), DamageType.NORMAL_DAMAGE, encounter, false);
			
		}
		   Utils.print("Hit or miss, you get a bonus to the end of the encounter to speed, damage and athletics.");
		   aspectOfMightEncounterBonus = true;
		} else {
			Utils.print("Sorry, but " + owner.getName() + " has already used Aspect of Might today.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			owner.setUsedStandardAction(false);
		}
	}
	
	@Override
	public void initializeForNewDay() {
		usedAspectOfMight = false;
	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Add automatic trained skill(s).
		trainedSkills.add("Religion");
		Utils.print("Automatically trained in Religion.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Intimidate");
		choices.add("Perception");
		choices.add("Stealth");
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

}
