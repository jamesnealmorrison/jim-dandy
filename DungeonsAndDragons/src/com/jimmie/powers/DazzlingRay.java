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
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class DazzlingRay extends AttackPower {
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
		return "Dazzling Ray";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.DAILY;
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
	public DamageType getDamageType() {
		return DamageType.RADIANT;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.STANDARD;
	}

	@Override
	public int getRangeNumber1() {
		return 10;
	}

	@Override
	public int getRangeNumber2() {
		return 0;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 10, 10, getAttackType());
		
		DndCharacter c = null;
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			c = (DndCharacter) user;
		}
		
		Sorcerer sorcerer = null;
		if (Sorcerer.class.isAssignableFrom(c.getDndClass().getClass())) {
			sorcerer = (Sorcerer) c.getDndClass();
		}

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);
			int targetWill = target.getWill(user);
			Utils.print("Your target has a will of " + targetWill);

			int attackRoll = user.attackRoll(AbilityType.CHARISMA, getAccessoryType(), target, user.getCurrentPosition(), getAttackType());

			// Check for unfettered power.
			if (sorcerer.getUnfetteredPower() == 1) {
				Utils.print("Because of your unfettered power, you push all creatures within 5 squares.");
				List<Creature> pushTargets = Encounter.getEncounter().getAllCreaturesInAreaBurst(user.getCurrentPosition(), 5);
				user.pushTargets(pushTargets, 1);
			}

			if (attackRoll >= targetWill) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				int damageRolls = 6;

				DiceType damageDiceType = DiceType.SIX_SIDED;

				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user), DamageType.RADIANT, true, user, getAttackType());

				if (sorcerer.getUnfetteredPower() == 20) {
					Utils.print("Because of your unfettered power, you get to slide " + target.getName() + " 1 square and knock them prone.");
					user.slideTargets(targets, 1);
					if (Creature.class.isAssignableFrom(target.getClass())) {
						Creature cTarget = (Creature) target;
						// TODO: I don't think I've implemented standing up from prone yet.
						cTarget.setTemporaryCondition(user, DurationType.SPECIAL, CreatureConditionType.PRONE, TemporaryEffectReason.DAZZLING_RAY, user.getCurrentTurn());
					}
				}
			} else {
				Utils.print("You missed " + target.getName() + ". Doing half damage.");
				target.hurt(Utils.rollForHalfDamage(6, DiceType.SIX_SIDED, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user), DamageType.RADIANT, false, user, getAttackType());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user, this);
			}
			
			if (sorcerer.getLastAttackRollEven()) {
				if (Creature.class.isAssignableFrom(target.getClass())) {
					int abilityModifier = user.getAbilityModifier(AbilityType.DEXTERITY);
					Utils.print("Because of your wild magic, the target takes a penalty to attack rolls against you of " + (-1*abilityModifier) + ". (Save ends)");
					Creature cTarget = (Creature) target;
					cTarget.setTargetedTemporaryEffect((-1*abilityModifier), user.getCurrentTurn(), DurationType.SAVE_ENDS, user, TemporaryEffectType.ATCK_ROLL_MOD, TemporaryEffectReason.DAZZLING_RAY, user);
				}
			}
		}
		return true;
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
		classes.add(Sorcerer.class);
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
