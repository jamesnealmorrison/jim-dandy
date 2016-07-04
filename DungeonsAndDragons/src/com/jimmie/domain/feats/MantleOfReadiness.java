package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Ardent;
import com.jimmie.domain.creatures.PlayerCharacter;

public class MantleOfReadiness extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.MANTLE_OF_READINESS;
	}

	@Override
	public String getName() {
		return "Mantle Of Readiness";
	}

	@Override
	public String getBenefit() {
		return "You and nearby allies gain +2 speed at start of combat";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Ardent.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
