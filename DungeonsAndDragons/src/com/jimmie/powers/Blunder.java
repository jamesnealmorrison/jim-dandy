package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.MovementType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class Blunder extends AttackPower {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.RANGED_NUMBER;
	}

	@Override
	public String getName() {
		return "Blunder";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.ENCOUNTER;
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.ARCANE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.NORMAL);
		return damageTypes;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.STANDARD;
	}

	@Override
	public int getRangeNumber1() {
		return 5;
	}

	@Override
	public int getRangeNumber2() {
		return 0;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.CHARM);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		if (timesUsed == 0) {
			timesUsed++;

			DndCharacter c = null;
			if (DndCharacter.class.isAssignableFrom(user.getClass())) {
				c = (DndCharacter) user;
			}

			List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 5, 5);

			if ((targets != null) && !(targets.isEmpty())) {
				AttackTarget target = targets.get(0);
				int targetWill = target.getWill(user);
				Utils.print("Your target has a Will of " + targetWill);

				int attackRoll = user.attackRoll(AbilityType.CHARISMA, AccessoryType.IMPLEMENT, targets);

				if (attackRoll >= targetWill) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());

					int damageRolls = 1;
					DiceType damageDiceType = DiceType.SIX_SIDED;

					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user), DamageType.NORMAL, true, user);

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
							target.moveCreature(direction, MovementType.SLIDE);
							distanceLeft--;
						} else if ("Attack".equalsIgnoreCase(choice)) {
							/* Pick which ally will make the attack. */
							Utils.print("Please pick which ally will attack.");
							Creature attacker = Encounter.getEncounter().chooseAnyAlly(user);

							attacker.setTemporaryEffect(2, user.getCurrentTurn(), DurationType.IMMEDIATE, user, TemporaryEffectType.ATCK_ROLL_MOD, TemporaryEffectReason.BLUNDER);
							Utils.print("Make sure to pick me (" + target.getName() + ") when it asks who to attack.");
							/* Should be able to cast the marker to a character. */
							if (DndCharacter.class.isInstance(attacker)) {
								Power power = ((DndCharacter) (attacker)).getBasicMeleeAttack();
								power.process(attacker);
							}
						} else if ("Stop".equalsIgnoreCase(choice)) {
							break;
						}
					}

				} else {
					Utils.print("You missed " + target.getName());
					// Some targets have powers/effects that happen when they are missed.
					target.miss(user);
				}
			}
			return true;
		} else {
			Utils.print("Sorry, but " + user.getName() + " has already used Blunder in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			user.setUsedStandardAction(false);
		}
		return false;
	}

	@Override
	public boolean isBasicAttack() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getRacesThatCanUsePower() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getClassesThatCanUsePower() {
		List<Class> classes = new ArrayList<Class>();
		classes.add(Bard.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		// Has it been used during this encounter already?
		if (timesUsed > 0) {
			return false;
		}
		return true;
	}

}
