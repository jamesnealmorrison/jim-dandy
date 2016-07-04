package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.util.Utils;

public class GoblinWarriorMobileRangedAttack extends AttackPower {

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
		return "Mobile Ranged Attack";
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
		return DamageType.NORMAL;
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
		int distanceLeft = user.getSpeed()/2;
		boolean keepMoving = true;
		boolean madeRangedAttack = false;
		
		Utils.print("You get to shift up to " + distanceLeft + " squares and make a basic ranged attack at any point along the way.");
		if (madeRangedAttack == false) {
			Utils.print("Do you want to make the attack now?");
			if ("Y".equalsIgnoreCase(Utils.getYesOrNoInput())) {
				madeRangedAttack = true;
				Power power = user.getBasicRangedAttack();
				if (power != null) {
					power.process(user);
				}
			}
		}
		while ((distanceLeft > 0) && (keepMoving)) {
			user.shift(1, true);
			distanceLeft--;
			if (madeRangedAttack == false) {
				Utils.print("Do you want to make the attack now?");
				if ("Y".equalsIgnoreCase(Utils.getYesOrNoInput())) {
					madeRangedAttack = true;
					Power power = user.getBasicRangedAttack();
					if (power != null) {
						power.process(user);
					}
				}
			}
			if (distanceLeft > 0) {
				Utils.print("Continue moving?");
				if ("N".equalsIgnoreCase(Utils.getYesOrNoInput())) {
					keepMoving = false;
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
}
