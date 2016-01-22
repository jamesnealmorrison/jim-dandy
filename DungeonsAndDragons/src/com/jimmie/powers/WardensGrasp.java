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
import com.jimmie.domain.MovementType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class WardensGrasp extends AttackPower {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.CLOSE_BURST;
	}

	@Override
	public String getName() {
		return "Warden's Grasp";
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
		return AccessoryType.NONE;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.NONE);
		return damageTypes;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.IMMEDIATE_REACTION;
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
		List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 5, 0);

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);

			Utils.print("You get to slide " + target.getName() + " 1 square.");
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

			Utils.print("Setting them to slow");
			if (Creature.class.isAssignableFrom(target.getClass())) {
				Creature creature = (Creature) target;
				creature.setTemporaryCondition(creature, DurationType.START_OF_NEXT_TURN, CreatureConditionType.SLOWED, TemporaryEffectReason.WARDENS_GRASP, creature.getCurrentTurn());
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
		classes.add(Warden.class);
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
