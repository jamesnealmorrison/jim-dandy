package com.jimmie.domain.items.weapons;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.DiceType;
import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;

public class Unarmed extends Weapon {
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
		return 0;
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
		return new Price(0, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public WeaponType getWeaponType() {
		return WeaponType.UNARMED_ATTACK;
	}

	@Override
	public List<WeaponGroup> getWeaponGroups() {
		List<WeaponGroup> groups = new ArrayList<WeaponGroup>();
		groups.add(WeaponGroup.UNARMED);
		return groups;
	}

	@Override
	public List<WeaponProperty> getWeaponProperties() {
		List<WeaponProperty> props = new ArrayList<WeaponProperty>();
		return props;
	}

	@Override
	public WeaponCategory getWeaponCategory() {
		return WeaponCategory.IMPROVISED_MELEE;
	}

	@Override
	public WeaponHandType getHandType() {
		return WeaponHandType.ONE_HANDED;
	}
}
