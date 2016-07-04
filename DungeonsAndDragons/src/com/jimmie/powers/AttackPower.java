package com.jimmie.powers;

import com.jimmie.domain.AttackType;

public abstract class AttackPower extends Power {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public abstract AttackType getAttackType();
}
