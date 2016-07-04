package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class HammeringIron extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HAMMERING_IRON;
	}

	@Override
	public String getName() {
		return "Hammering Iron";
	}

	@Override
	public String getBenefit() {
		return "Push target after opportunity attack when using hammer";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
