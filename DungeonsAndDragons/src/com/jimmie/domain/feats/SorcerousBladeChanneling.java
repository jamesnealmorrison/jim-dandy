package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.creatures.PlayerCharacter;

public class SorcerousBladeChanneling extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SORCEROUS_BLADE_CHANNELING;
	}

	@Override
	public String getName() {
		return "Sorcerous Blade Channeling";
	}

	@Override
	public String getBenefit() {
		return "Use dagger to make ranged attacks as melee attacks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Sorcerer.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
