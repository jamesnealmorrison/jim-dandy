package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class EchoesOfThunder extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ECHOES_OF_THUNDER;
	}

	@Override
	public String getName() {
		return "Echoes of Thunder";
	}

	@Override
	public String getBenefit() {
		return "+1 damage after you hit with thunder power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
