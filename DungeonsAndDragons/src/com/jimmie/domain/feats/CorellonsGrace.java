package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class CorellonsGrace extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.CORELLONS_GRACE;
	}

	@Override
	public String getName() {
		return "Corellon's Grace";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Corellon's Grace";
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
