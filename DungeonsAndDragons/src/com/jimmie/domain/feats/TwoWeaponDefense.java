package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class TwoWeaponDefense extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.TWO_WEAPON_DEFENSE;
	}

	@Override
	public String getName() {
		return "Two Weapon Defense";
	}

	@Override
	public String getBenefit() {
		return "+1 to AC and Reflex while holding a weapon in each hand";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getDexterity() >= 13) {
			if (pc.hasFeat(FeatType.TWO_WEAPON_FIGHTING)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
