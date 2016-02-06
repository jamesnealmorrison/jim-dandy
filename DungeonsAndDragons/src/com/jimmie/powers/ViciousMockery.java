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
import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class ViciousMockery extends AttackPower {
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
		return "Vicious Mockery";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.AT_WILL;
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
		return DamageType.PSYCHIC;
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
		effectTypes.add(EffectType.CHARM);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		DndCharacter c = null;
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			c = (DndCharacter) user;
		}

		List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 10, 10);

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);
			int targetWill = target.getWill(user);
			Utils.print("Your target has a Will of " + targetWill);

			int attackRoll = user.attackRoll(AbilityType.CHARISMA, getAccessoryType(), target);

			if (attackRoll >= targetWill) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				int damageRolls = 1;
				DiceType damageDiceType = DiceType.SIX_SIDED;

				/* Book says at level 21 increase damage to 2d8. */
				if (user.getLevel() >= 21) {
					damageRolls = damageRolls * 2;
				}
				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, c.getImplementDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user), DamageType.NORMAL, true, user);

				/* The target takes a -2 penalty to attack rolls until the end of my next turn. */
				c.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.ATCK_ROLL_MOD, TemporaryEffectReason.VICIOUS_MOCKERY);
				Utils.print(target.getName() + " will take a -2 attack roll penalty until the end of " + user.getName() + "'s next turn.");
			} else {
				Utils.print("You missed " + target.getName());
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
		classes.add(Bard.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		return true;
	}

}
