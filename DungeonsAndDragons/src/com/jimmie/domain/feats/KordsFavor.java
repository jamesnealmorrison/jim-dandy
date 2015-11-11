package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class KordsFavor extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.KORDS_FAVOR;
	}

	@Override
	public String getName() {
		return "Kord's Favor";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Kord's favor";
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
