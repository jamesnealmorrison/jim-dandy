package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class UnfailingVigor extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.UNFAILING_VIGOR;
	}

	@Override
	public String getName() {
		return "Unfailing Vigor";
	}

	@Override
	public String getBenefit() {
		return "Spend a healing surge if you roll 18-20 on death saving throw";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
