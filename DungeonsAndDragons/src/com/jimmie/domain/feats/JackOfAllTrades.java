package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class JackOfAllTrades extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.JACK_OF_ALL_TRADES;
	}

	@Override
	public String getName() {
		return "Jack of All Trades";
	}

	@Override
	public String getBenefit() {
		return "+2 to untrained skill checks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getIntelligence() >= 13) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
