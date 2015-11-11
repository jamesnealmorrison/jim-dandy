package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class PelorsRadiance extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PELORS_RADIANCE;
	}

	@Override
	public String getName() {
		return "Pelor's Radiance";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Pelor's radiance";
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
