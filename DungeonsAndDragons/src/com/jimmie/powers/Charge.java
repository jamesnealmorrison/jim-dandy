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
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.util.Utils;

public class Charge extends AttackPower {
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
		return "Charge";
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
		return 1;  // Not really sure what to put here.
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
		Utils.print("I can't really automate all of this attack.  According to the book, here are the requirements you must meet:");
		Utils.print("You must move at least 2 squares from your starting position, and you must move directly to the nearest square from which you can attck the enemy.");
		Utils.print("You can't charge if the nearest square is occupied.");
		Utils.print("Do you still want to charge (Y or N)? (If No, you won't be deducted a standard action.");
		if ("Y".equalsIgnoreCase(Utils.getYesOrNoInput())) {
			boolean tempUsedMoveAction = user.isUsedMoveAction();
			// Temporarily set usedMoveAction to false so they can use a move action.
			user.setUsedMoveAction(false);
			user.useMoveAction();
			// Now set it back to what it was.
			user.setUsedMoveAction(tempUsedMoveAction);
			
			Utils.print("Now do you want to do a bull rush or a basic melee attack?");
			Utils.print("1. Bull Rush");
			Utils.print("2. Basic Melee Attack");
			Utils.print("Your choice?");
			user.setTemporaryEffect(1, user.getCurrentTurn(), DurationType.IMMEDIATE, user, TemporaryEffectType.ATCK_ROLL_MOD, TemporaryEffectReason.CHARGE);
			if (1 == Utils.getValidIntInputInRange(1, 2)) {
				// Find the user's bull rush power.
				for (Power power : user.getPowers()) {
					if (BullRush.class.isAssignableFrom(power.getClass())) {
						power.process(user);
					}
				}
			} else {
				Power power = user.getBasicMeleeAttack();
				if (power != null) {
					power.process(user);
				}
			}
			return true;
		} else {
			return false;
		}
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
