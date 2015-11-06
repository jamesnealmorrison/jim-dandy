package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class MelorasTide extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.MELORAS_TIDE;
	}

	@Override
	public String getName() {
		return "Melora's Tide";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Melora's tide";
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
