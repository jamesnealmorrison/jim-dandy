package com.jimmie.powers;

import com.jimmie.domain.AttackType;

public abstract class AttackPower extends Power {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public abstract AttackType getAttackType();
	public boolean isMeleeAttack() {
		if ((getAttackType() == AttackType.MELEE_1) || (getAttackType() == AttackType.MELEE_TOUCH) || (getAttackType() == AttackType.MELEE_WEAPON)
				|| (getAttackType() == AttackType.MELEE_OR_RANGED_WEAPON)) {
			return true;
		}
		return false;
	}
}
