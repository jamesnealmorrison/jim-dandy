package com.jimmie.domain.items.weapons;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.DiceType;

public class ShortSword extends Weapon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getDamageBonus() {
		return 0;
	}

	@Override
	public int getDamageRolls() {
		return 1;
	}

	@Override
	public DiceType getDamageDice() {
		return DiceType.SIX_SIDED;
	}

	@Override
	public int getProficiencyBonus() {
		return 3;
	}

	@Override
	public boolean isOneHandedWeapon() {
		return true;
	}

	@Override
	public boolean isTwoHandedWeapon() {
		return false;
	}

	@Override
	public int getNormalRange() {
		return 1;
	}

	@Override
	public int getLongRange() {
		return 0;
	}

	@Override
	public int getPrice() {
		return 10;
	}

	@Override
	public int getWeight() {
		return 2;
	}

	@Override
	public WeaponType getWeaponType() {
		return WeaponType.SHORTSWORD;
	}

	@Override
	public List<WeaponGroup> getWeaponGroups() {
		List<WeaponGroup> groups = new ArrayList<WeaponGroup>();
		groups.add(WeaponGroup.LIGHT_BLADE);
		return groups;
	}

	@Override
	public List<WeaponProperty> getWeaponProperties() {
		List<WeaponProperty> props = new ArrayList<WeaponProperty>();
		props.add(WeaponProperty.OFF_HAND);
		return props;
	}

	@Override
	public WeaponCategory getWeaponCategory() {
		return WeaponCategory.MILITARY_MELEE;
	}
}
