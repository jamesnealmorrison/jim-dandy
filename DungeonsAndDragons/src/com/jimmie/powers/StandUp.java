package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryCondition;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.PowerSource;

public class StandUp extends Power {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Stand up";
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
		return 0;
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
		// They are using this power to remove the prone effect.
		for (TemporaryEffect effect : user.getTemporaryEffects()) {
			if (TemporaryCondition.class.isAssignableFrom(effect.getClass())) {
				TemporaryCondition condition = (TemporaryCondition) effect;
				if (condition.getConditionType() == CreatureConditionType.PRONE) {
					user.getTemporaryEffects().remove(condition);
					break;
				}
			}
		}
		return true;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		if (user.isProne()) {
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
		return null;
	}

}
