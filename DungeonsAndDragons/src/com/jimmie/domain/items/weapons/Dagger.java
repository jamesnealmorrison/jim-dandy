package com.jimmie.domain.items.weapons;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.DiceType;
import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;

public class Dagger extends Weapon {

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
		return DiceType.FOUR_SIDED;
	}

	@Override
	public int getProficiencyBonus() {
		return 3;
	}
	
	// The following method is only here as a way to implement daggers as "Implements".  
	public int getAttackBonus() {
		// Non-magical daggers shouldn't give an implement bonus.
		return 0;
	}

	// The following method is only here as a way to implement daggers as "Implements".  
	public int getImplementDamageBonus() {
		// Non-magical daggers shouldn't give an implement bonus.
		return 0;
	}

	@Override
	public int getNormalRange() {
		return 5;
	}

	@Override
	public int getLongRange() {
		return 10;
	}

	@Override
	public Price getPrice() {
		return new Price(1, CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 1;
	}

	@Override
	public WeaponType getWeaponType() {
		return WeaponType.DAGGER;
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
		props.add(WeaponProperty.LIGHT_THROWN);
		return props;
	}

	@Override
	public WeaponCategory getWeaponCategory() {
		return WeaponCategory.SIMPLE_MELEE;
	}

	@Override
	public WeaponHandType getHandType() {
		return WeaponHandType.ONE_HANDED;
	}

	@Override
	public String getName() {
		return "Dagger";
	}

}
