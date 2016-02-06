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
import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class RadiantDelirium extends AttackPower {
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
		return "Radiant Delirium";
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
		return PowerSource.DIVINE;
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
		return 5;
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
		List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 5, 5);

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);
			int targetReflex = target.getReflex(user);
			Utils.print("Your target has a reflex of " + targetReflex);

			int attackRoll = user.attackRoll(AbilityType.CHARISMA, getAccessoryType(), target);

			if (attackRoll >= targetReflex) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				int damageRolls = 3;
				DiceType damageDiceType = DiceType.EIGHT_SIDED;

				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user), DamageType.RADIANT, true, user);
				
				if (Creature.class.isAssignableFrom(target.getClass())) {
					Utils.print(target.getName() + " is dazed until the end of my next turn.");
					Creature c = (Creature) target;
					c.setTemporaryCondition(user, DurationType.END_OF_NEXT_TURN, CreatureConditionType.DAZED, TemporaryEffectReason.RADIANT_DELIRIUM, user.getCurrentTurn());
					
					Utils.print(target.getName() + " will take a -2 penalty to AC (save ends).");
					c.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.SAVE_ENDS, user, TemporaryEffectType.AC_MOD, TemporaryEffectReason.RADIANT_DELIRIUM);
				}

				
			} else {
				Utils.print("You missed " + target.getName());

				int damageRolls = 3;
				DiceType damageDiceType = DiceType.EIGHT_SIDED;

				target.hurt(Utils.rollForHalfDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user), DamageType.RADIANT, true, user);
				
				if (Creature.class.isAssignableFrom(target.getClass())) {
					Utils.print(target.getName() + " is dazed until the end of my next turn.");
					Creature c = (Creature) target;
					c.setTemporaryCondition(user, DurationType.END_OF_NEXT_TURN, CreatureConditionType.DAZED, TemporaryEffectReason.RADIANT_DELIRIUM, user.getCurrentTurn());
				}
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user, this);
			}
			return true;
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
		classes.add(Paladin.class);
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
