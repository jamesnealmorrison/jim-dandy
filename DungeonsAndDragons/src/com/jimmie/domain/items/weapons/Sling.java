package com.jimmie.domain.items.weapons;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.DiceType;
import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;

public class Sling extends Weapon {

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
		return 2;
	}

	@Override
	public WeaponHandType getHandType() {
		return WeaponHandType.ONE_HANDED;
	}

	@Override
	public int getNormalRange() {
		return 10;
	}

	@Override
	public int getLongRange() {
		return 20;
	}

	@Override
	public Price getPrice() {
		return new Price(1, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public WeaponType getWeaponType() {
		return WeaponType.SLING;
	}

	@Override
	public List<WeaponGroup> getWeaponGroups() {
		List<WeaponGroup> groups = new ArrayList<WeaponGroup>();
		groups.add(WeaponGroup.SLING);
		return groups;
	}

	@Override
	public List<WeaponProperty> getWeaponProperties() {
		List<WeaponProperty> props = new ArrayList<WeaponProperty>();
		props.add(WeaponProperty.LOAD_FREE);
		return props;
	}

	@Override
	public WeaponCategory getWeaponCategory() {
		return WeaponCategory.SIMPLE_RANGED;
	}

	@Override
	public String getName() {
		return "Sling";
	}
}
