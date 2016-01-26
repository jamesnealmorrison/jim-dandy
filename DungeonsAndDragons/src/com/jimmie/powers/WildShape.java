package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class WildShape extends AttackPower {
	@Override
	public void initializeForStartOfTurn() {
		super.initializeForStartOfTurn();
		timesUsed = 0;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.PERSONAL;
	}

	@Override
	public String getName() {
		return "Wild Shape";
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
		return ActionType.MINOR;
	}

	@Override
	public int getRangeNumber1() {
		return 0;
	}

	@Override
	public int getRangeNumber2() {
		return 0;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.POLYMORPH);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		// Only once per round.
		if (timesUsed == 0) {
			if (Druid.class.isAssignableFrom(user.getDndClass().getClass())) {
				Druid druid = (Druid) user.getDndClass();
				if (druid.isInBeastForm()) {
					druid.setInBeastForm(false);
					Utils.print("Changing to humanoid form.");
					Utils.print("You get to shift 1 square when changing back to humanoid form.");
					user.shift(1, true);
				} else {
					druid.setInBeastForm(true);
					Utils.print("Changing to beast form.");
					Encounter.setDebug(true);
				}
			}
			timesUsed++;
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
		classes.add(Druid.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		if (timesUsed > 0) {
			return false;
		}
		return true;
	}

}
