package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class OathOfEnmity extends AttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AttackTarget oathOfEnmityTarget;

	@Override
	public AttackType getAttackType() {
		return AttackType.CLOSE_BURST;
	}

	@Override
	public String getName() {
		return "Oath Of Enmity";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.ENCOUNTER_SPECIAL;
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.DIVINE;
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
		return ActionType.MINOR;
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
	public void process(Encounter encounter, Creature user) {
		// The book says that you regain access to this power when the target dies (that's why it's "encounter special").
		if ((oathOfEnmityTarget == null) || (oathOfEnmityTarget.getCurrentHitPoints() <= 0)) {
			timesUsed = 0;
			oathOfEnmityTarget = null;
		}
		if (timesUsed == 0) {
			timesUsed++;

		/* This is supposed to be a close burst 10, but the ranged will work for it. */
		AttackTarget target = encounter.chooseRangedTarget(user, 10, 10);
		
		oathOfEnmityTarget = target;

		} else {
			Utils.print("Sorry, but " + user.getName() + " has already used Oath of Enmity in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			user.setUsedMinorAction(false);
		}
	}

	public AttackTarget getOathOfEnmityTarget() {
		return oathOfEnmityTarget;
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
		classes.add(Avenger.class);
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
