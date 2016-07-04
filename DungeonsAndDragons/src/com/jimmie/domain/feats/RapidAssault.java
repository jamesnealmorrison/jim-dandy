package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class RapidAssault extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RAPID_ASSAULT;
	}

	@Override
	public String getName() {
		return "Rapid Assault";
	}

	@Override
	public String getBenefit() {
		return "+2 or more damage on your first attack during an encounter";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
