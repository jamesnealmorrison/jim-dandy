package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.util.Utils;

public class TotalDefense extends Power {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Total Defense";
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
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.NONE);
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
		Utils.print(user.getName() + " now has a +2 bonus to all defenses until the start of the nex turn.");
		user.setTemporaryEffect(2, user.getCurrentTurn(), DurationType.START_OF_NEXT_TURN, user, TemporaryEffectType.ARMOR_CLASS_MODIFIER, TemporaryEffectReason.TOTAL_DEFENSE);
		user.setTemporaryEffect(2, user.getCurrentTurn(), DurationType.START_OF_NEXT_TURN, user, TemporaryEffectType.FORTITUDE_MODIFIER, TemporaryEffectReason.TOTAL_DEFENSE);
		user.setTemporaryEffect(2, user.getCurrentTurn(), DurationType.START_OF_NEXT_TURN, user, TemporaryEffectType.WILL_MODIFIER, TemporaryEffectReason.TOTAL_DEFENSE);
		user.setTemporaryEffect(2, user.getCurrentTurn(), DurationType.START_OF_NEXT_TURN, user, TemporaryEffectType.REFLEX_MODIFIER, TemporaryEffectReason.TOTAL_DEFENSE);
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
		return true;
	}
}
