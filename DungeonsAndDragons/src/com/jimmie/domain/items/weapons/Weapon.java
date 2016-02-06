package com.jimmie.domain.items.weapons;

import java.io.Serializable;
import java.util.List;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.items.Price;
import com.jimmie.powers.Power;

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
	
	public abstract String getName();

	public boolean isMeleeWeapon() {
		if ((WeaponCategory.IMPROVISED_MELEE == getWeaponCategory()) || (WeaponCategory.MILITARY_MELEE == getWeaponCategory()) ||
				(WeaponCategory.SIMPLE_MELEE == getWeaponCategory()) || (WeaponCategory.SUPERIOR_MELEE == getWeaponCategory())) {
			return true;
		}
		return false;
	}

	public boolean isRangedWeapon() {
		if ((WeaponCategory.IMPROVISED_RANGED == getWeaponCategory()) || (WeaponCategory.MILITARY_RANGED == getWeaponCategory()) ||
				(WeaponCategory.SIMPLE_RANGED == getWeaponCategory()) || (WeaponCategory.SUPERIOR_RANGED == getWeaponCategory())) {
			return true;
		}
		if ((getWeaponProperties().contains(WeaponProperty.HEAVY_THROWN)) || (getWeaponProperties().contains(WeaponProperty.LIGHT_THROWN))) {
			return true;
		}
		return false;
	}

	public List<Power> getPowers() {
		return null;
	}
}
