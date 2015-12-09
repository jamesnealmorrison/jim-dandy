package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class CascadingRush extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.CASCADING_RUSH;
	}

	@Override
	public String getName() {
		return "Cascading Rush";
	}

	@Override
	public String getBenefit() {
		return "Push adjacent enemy before or after bull rush";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
