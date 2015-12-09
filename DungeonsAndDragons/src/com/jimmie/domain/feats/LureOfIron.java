package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Battlemind;
import com.jimmie.domain.creatures.PlayerCharacter;

public class LureOfIron extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.LURE_OF_IRON;
	}

	@Override
	public String getName() {
		return "Lure of Iron";
	}

	@Override
	public String getBenefit() {
		return "Slide target of mind spike";
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
