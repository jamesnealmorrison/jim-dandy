package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class Linguist extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.LINGUIST;
	}

	@Override
	public String getName() {
		return "Linguist";
	}

	@Override
	public String getBenefit() {
		return "Learn three new languages";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getIntelligence() <= 13) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO make language choices

	}

}
