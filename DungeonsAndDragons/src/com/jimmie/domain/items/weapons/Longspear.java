package com.jimmie.domain.items.weapons;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.DiceType;
import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;

public class Longspear extends Weapon {

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
		return 2;
	}

	@Override
	public DiceType getDamageDice() {
		return DiceType.SIX_SIDED;
	}

	@Override
	public int getProficiencyBonus() {
		return 2;
	}

	@Override
	public WeaponHandType getHandType() {
		return WeaponHandType.TWO_HANDED;
	}

	@Override
	public int getNormalRange() {
		return 0;
	}

	@Override
	public int getLongRange() {
		return 0;
	}

	@Override
	public Price getPrice() {
		return new Price(10, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 9;
	}

	@Override
	public WeaponType getWeaponType() {
		return WeaponType.LONGSPEAR;
	}

	@Override
	public List<WeaponGroup> getWeaponGroups() {
		List<WeaponGroup> groups = new ArrayList<WeaponGroup>();
		groups.add(WeaponGroup.POLEARM);
		groups.add(WeaponGroup.SPEAR);
		return groups;
	}

	@Override
	public List<WeaponProperty> getWeaponProperties() {
		List<WeaponProperty> props = new ArrayList<WeaponProperty>();
		props.add(WeaponProperty.REACH);
		return props;
	}

	@Override
	public WeaponCategory getWeaponCategory() {
		return WeaponCategory.MILITARY_MELEE;
	}

	@Override
	public String getName() {
		return "Longspear";
	}
}
