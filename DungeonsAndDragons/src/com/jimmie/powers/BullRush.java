package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class BullRush extends AttackPower {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.MELEE_NUMBER;
	}

	@Override
	public String getName() {
		return "Bull Rush";
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
		return PowerSource.NONE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.NONE;
	}

	@Override
	public DamageType getDamageType() {
		return DamageType.NONE;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.STANDARD;
	}

	@Override
	public int getRangeNumber1() {
		return 1;
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
		List<AttackTarget> targets = Encounter.getEncounter().chooseMeleeTargetInRange(user, 1);

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);

			if (Creature.class.isAssignableFrom(target.getClass())) {
				Creature cTarget = (Creature) target;
				if (cTarget.isSmallerThan(user) || cTarget.isSameSizeAs(user) || cTarget.isOneSizeBiggerThan(user)) {


					int targetFortitude = target.getFortitude(user);
					Utils.print("Your target has a Fortitude of " + targetFortitude);

					int attackRoll = user.attackRoll(AbilityType.STRENGTH, getAccessoryType(), targets);

					if (attackRoll >= targetFortitude) {
						/* A HIT! */
						Utils.print("You successfully hit " + target.getName());

						/* Push the target. */
						String pushDirection = Encounter.getEncounter().getPushDirection(user.getCurrentPosition(), target.getCurrentPosition());
						target.push(pushDirection);
						user.shift(1, pushDirection, true);
					} else {
						Utils.print("You missed " + target.getName());
						// Some targets have powers/effects that happen when they are missed.
						target.miss(user, this);
					}
				} else {
					Utils.print(target.getName() + " is too big.  You can't bull rush them.");
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
		return null;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		// Is there an adjacent enemy?
		List<Creature> adjEnemies = Encounter.getEncounter().getAdjacentEnemies(user);
		if ((adjEnemies != null) && (!adjEnemies.isEmpty())) {
			return true;
		}
		return false;
	}
}
