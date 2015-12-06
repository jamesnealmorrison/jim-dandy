package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class TimelyRespite extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.TIMELY_RESPITE;
	}

	@Override
	public String getName() {
		return "Timely Respite";
	}

	@Override
	public String getBenefit() {
		return "Second wind or total defense grants saving throw";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
