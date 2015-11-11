package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class IounsPoise extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IOUNS_POISE;
	}

	@Override
	public String getName() {
		return "Ioun's Poise";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Ioun's Poise";
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
