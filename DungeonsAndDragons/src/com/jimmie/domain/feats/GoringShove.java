package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Minotaur;

public class GoringShove extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.GORING_SHOVE;
	}

	@Override
	public String getName() {
		return "Goring Shove";
	}

	@Override
	public String getBenefit() {
		return "Push target 1 square after goring charge";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Minotaur.class.isAssignableFrom(pc.getRace().getClass())) {
			if (pc.getStrength() >= 15) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
