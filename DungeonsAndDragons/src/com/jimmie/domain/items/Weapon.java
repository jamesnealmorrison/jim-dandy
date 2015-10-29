package com.jimmie.domain.items;

import java.io.Serializable;

import com.jimmie.domain.DiceType;

public abstract class Weapon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract int getDamageBonus();

	public abstract int getDamageRolls();

	public abstract DiceType getDamageDice();

	public abstract int getReach();

	public abstract String getWeaponId();

	public abstract int getProficiencyBonus();

	public abstract boolean isOneHandedWeapon();

	public abstract boolean isTwoHandedWeapon();
}
