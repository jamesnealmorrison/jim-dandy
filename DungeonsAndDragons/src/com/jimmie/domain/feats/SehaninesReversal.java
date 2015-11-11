package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class SehaninesReversal extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SEHANINES_REVERSAL;
	}

	@Override
	public String getName() {
		return "Sehanine's Reversal";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Sehanine's reversal";
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
