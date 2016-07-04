package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Battlemind;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PursuingStep extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PURSUING_STEP;
	}

	@Override
	public String getName() {
		return "Pursuing Step";
	}

	@Override
	public String getBenefit() {
		return "Triggering enemy grants combat advantage to you if adjacent";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Battlemind.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
