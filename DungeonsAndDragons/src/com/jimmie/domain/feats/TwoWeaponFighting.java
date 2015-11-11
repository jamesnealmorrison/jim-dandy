package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class TwoWeaponFighting extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.TWO_WEAPON_FIGHTING;
	}

	@Override
	public String getName() {
		return "Two Weapon Fighting";
	}

	@Override
	public String getBenefit() {
		return "+1 damage while holding a melee weapon in each hand";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getDexterity() >= 13) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
