package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Goliath;
import com.jimmie.domain.creatures.PlayerCharacter;

public class GoliathGreatweaponProwess extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.GOLIATH_GREATWEAPON_PROWESS;
	}

	@Override
	public String getName() {
		return "Goliath Greatweapon Prowess";
	}

	@Override
	public String getBenefit() {
		return "Gain proficiency, +2 damage with two-handed melee weapons";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Goliath.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
