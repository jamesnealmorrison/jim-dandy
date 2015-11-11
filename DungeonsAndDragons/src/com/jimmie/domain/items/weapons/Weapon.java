package com.jimmie.domain.items.weapons;

import java.io.Serializable;
import java.util.List;

import com.jimmie.domain.DiceType;
import com.jimmie.domain.items.Price;

public abstract class Weapon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract int getDamageBonus();

	public abstract int getDamageRolls();

	public abstract DiceType getDamageDice();

	public abstract int getProficiencyBonus();

	public abstract WeaponHandType getHandType();
	
	public abstract int getNormalRange();
	
	public abstract int getLongRange();
	
	public abstract Price getPrice();
	
	public abstract int getWeight();
	
	public abstract WeaponType getWeaponType();

	public abstract List<WeaponGroup> getWeaponGroups();
	
	public abstract List<WeaponProperty> getWeaponProperties();
	
	public abstract WeaponCategory getWeaponCategory();
}
