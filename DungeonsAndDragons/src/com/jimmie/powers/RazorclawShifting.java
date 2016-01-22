package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Shifter;
import com.jimmie.domain.creatures.PowerSource;

import com.jimmie.util.Utils;

public class RazorclawShifting extends AttackPower {
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
		return "Razorclaw Shifting";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.ENCOUNTER;
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
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		if (timesUsed == 0) {
			timesUsed++;
			
			Utils.print(user.getName() + " will gain a +2 bonus to speed and +1 bonus to AC and Reflex until the end of the encounter.");
			user.setTemporaryEffect(2, user.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, user, TemporaryEffectType.SPEED_MODIFIER, TemporaryEffectReason.RAZORCLAW_SHIFTING);
			user.setTemporaryEffect(1, user.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, user, TemporaryEffectType.ARMOR_CLASS_MODIFIER, TemporaryEffectReason.RAZORCLAW_SHIFTING);
			user.setTemporaryEffect(1, user.getCurrentTurn(), DurationType.END_OF_NEXT_EXTENDED_REST, user, TemporaryEffectType.REFLEX_MODIFIER, TemporaryEffectReason.RAZORCLAW_SHIFTING);
		} else {
			Utils.print("Sorry, but " + user.getName() + " has already used Angelic Alacrity in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			user.setUsedStandardAction(false);
			return false;
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
		List<Class> races = new ArrayList<Class>();
		races.add(Shifter.class);
		return races;
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
		// Has it been used during this encounter already?
		if (timesUsed > 0) {
			return false;
		}
		
		// Must be bloodied
		if (user.isBloodied()) {
			return true;
		}
		
		return false;
	}

}
