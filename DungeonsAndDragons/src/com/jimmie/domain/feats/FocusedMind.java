package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class FocusedMind extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.FOCUSED_MIND;
	}

	@Override
	public String getName() {
		return "Focused Mind";
	}

	@Override
	public String getBenefit() {
		return "+4 to saving throws against dazing and stunning effects";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
