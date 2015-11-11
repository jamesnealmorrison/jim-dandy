package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class RavenQueensBlessing extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RAVEN_QUEENS_BLESSING;
	}

	@Override
	public String getName() {
		return "Raven Queen's Blessing";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Raven Queen's blessing";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
