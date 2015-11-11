package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Rogue;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PressTheAdvantage extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PRESS_THE_ADVANTAGE;
	}

	@Override
	public String getName() {
		return "Press The Advantage";
	}

	@Override
	public String getBenefit() {
		return "Retain combat advantage with a critical hit";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getCharisma() >= 15) && (Rogue.class.isInstance(pc.getDndClass()))) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
