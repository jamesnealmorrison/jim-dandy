package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class AvandrasRescue extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.AVANDRAS_RESCUE;
	}

	@Override
	public String getName() {
		return "Avandra's Rescue";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Avandra's rescue";
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
