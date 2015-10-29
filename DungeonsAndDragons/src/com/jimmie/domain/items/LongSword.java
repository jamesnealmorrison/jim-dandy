package com.jimmie.domain.items;

import com.jimmie.domain.DiceType;

public class LongSword extends Weapon {
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
		return DiceType.EIGHT_SIDED;
	}

	@Override
	public int getReach() {
		return 1;
	}

	@Override
	public String getWeaponId() {
		return "LongSword";
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
}
