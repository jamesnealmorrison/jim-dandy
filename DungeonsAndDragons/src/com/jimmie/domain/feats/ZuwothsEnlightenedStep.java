package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Githzerai;

public class ZuwothsEnlightenedStep extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ZUWOTHS_ENLIGHTENED_STEP;
	}

	@Override
	public String getName() {
		return "Zuwoth's Enlightened Step";
	}

	@Override
	public String getBenefit() {
		return "You don't provoke from enemies that have not yet acted";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Githzerai.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
