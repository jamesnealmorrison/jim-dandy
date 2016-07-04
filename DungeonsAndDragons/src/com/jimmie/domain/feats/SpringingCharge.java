package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Minotaur;

public class SpringingCharge extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SPRINGING_CHARGE;
	}

	@Override
	public String getName() {
		return "Springing Charge";
	}

	@Override
	public String getBenefit() {
		return "Make second charge attack after critical on first charge attack";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Minotaur.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
