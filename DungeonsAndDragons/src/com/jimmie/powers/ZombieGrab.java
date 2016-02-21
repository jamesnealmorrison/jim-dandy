package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class ZombieGrab extends AttackPower {

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
		return "Zombie Grab";
	}

	@Override
	public int getLevel() {
		return 0;
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
		return 1;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.NONE);
		return effectTypes;
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
	public boolean process(Creature user) {
		List<AttackTarget> targets = Encounter.getEncounter().chooseMeleeTargetInRange(user, 1);

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);

			int targetReflex = target.getReflex(user);
			Utils.print("Your target has a Reflex of " + targetReflex);

			int roll = user.attackRoll(4 + user.getOtherAttackModifier(target));

			if (roll >= targetReflex) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());
				
				if (Creature.class.isAssignableFrom(target.getClass())) {
					Creature cTarget = (Creature) target;
					cTarget.setTemporaryCondition(user, DurationType.SPECIAL, CreatureConditionType.IMMOBILIZED, TemporaryEffectReason.GRABBED, cTarget.getCurrentTurn());
					// Save a pointer to the grabbed creature.
					user.setGrabbedCreature(cTarget);
				}
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
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		return true;
	}

}
