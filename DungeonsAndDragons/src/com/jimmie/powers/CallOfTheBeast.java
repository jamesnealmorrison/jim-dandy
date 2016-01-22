package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.Position;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class CallOfTheBeast extends AttackPower {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.AREA_BURST;
	}

	@Override
	public String getName() {
		return "Call Of The Beast";
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
		return PowerSource.PRIMAL;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.PSYCHIC);
		return damageTypes;
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
		return 10;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.CHARM);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		timesUsed++;

		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		Encounter.showCoordinateSystem(true);
		
		Utils.print("Please enter the X coordinate of the burst (within range of 10). No validation is done here, so do it right!");
		int x = Utils.getValidIntInputInRange(1, 50);

		Utils.print("Please enter the Y coordinate of the burst (within range of 10). No validation is done here, so do it right!");
		int y = Utils.getValidIntInputInRange(1, 50);
		Encounter.showCoordinateSystem(false);

		/* Got to do this weird conversion between creatures and attack targets. */
		List<Creature> creatureTargets = Encounter.getEncounter().getAllCreaturesInAreaBurst(new Position(x, y), 1);
		for (Creature creature : creatureTargets) {
			targets.add(creature);
		}

		for (AttackTarget target : targets) {
			int targetWill = target.getWill(user);
			Utils.print("Your target, " + target.getName() + ", has a Will of " + targetWill);

			int attackRoll = user.attackRoll(AbilityType.WISDOM, getAccessoryType(), targets);

			if (attackRoll >= targetWill) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());
				if (Creature.class.isAssignableFrom(target.getClass())) {
					Creature cTarget = (Creature) target;
					cTarget.setTemporaryEffect(5+user.getAbilityModifier(AbilityType.WISDOM),user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.CALL_OF_THE_BEAST_EFFECT, TemporaryEffectReason.CALL_OF_THE_BEAST);
				}
			} else {
				Utils.print("Sorry.  You missed " + target.getName());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user);
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
		classes.add(Druid.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		if (user.getDndClass() != null) {
			if (Druid.class.isAssignableFrom(user.getDndClass().getClass())) {
				// A druid must be in humanoid form to use this.
				Druid druid = (Druid) user.getDndClass();
				if (druid.isInBeastForm()) {
					return false;
				}
			}
		}
		
		return true;
	}

}
