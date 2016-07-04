package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AmmunitionType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.creatures.AmmunitionUser;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class HalflingSlingerStoneRain extends AttackPower {

	int lastRechargeCheckRound = 0;
	
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
		return "Stone Rain";
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.RECHARGE_5_6;
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
		return DamageType.NORMAL;
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
	public boolean process(Creature user) {
		if (timesUsed == 0) {
			// Find the sling power.
			for (Power power : user.getPowers()) {
				if (HalflingSlingerSling.class.isAssignableFrom(power.getClass())) {
					user.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.IMMEDIATE, user, TemporaryEffectType.ATCK_ROLL_MOD, TemporaryEffectReason.STONE_RAIN);
					power.process(user);
					user.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.IMMEDIATE, user, TemporaryEffectType.ATCK_ROLL_MOD, TemporaryEffectReason.STONE_RAIN);
					power.process(user);
					user.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.IMMEDIATE, user, TemporaryEffectType.ATCK_ROLL_MOD, TemporaryEffectReason.STONE_RAIN);
					power.process(user);
					break;
				}
			}
			timesUsed++;
			lastRechargeCheckRound = user.getCurrentTurn();
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
		// Have to check rounds, though.
		if (AmmunitionUser.class.isAssignableFrom(user.getClass())) {
			AmmunitionUser ammoUser = (AmmunitionUser) user;
			if (ammoUser.roundsLeft(AmmunitionType.BULLETS) < 3) {
				return false;
			}
		}
		if (timesUsed == 0) {
			return true;
		}
		// See if it can be recharged. 
		if (user.getCurrentTurn() > lastRechargeCheckRound) {
			Utils.print("Rolling 1d6 to see if " + user.getName() + " Stone Rain power can be recharged.");
			Dice d = new Dice(DiceType.SIX_SIDED);
			if (d.roll(DiceRollType.RECHARGE_ROLL) >= 5) {
				Utils.print("Power is recharged.");
				timesUsed = 0;
				return true;
			} else {
				Utils.print("Power is not recharged.");
				lastRechargeCheckRound = user.getCurrentTurn();
			}
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
