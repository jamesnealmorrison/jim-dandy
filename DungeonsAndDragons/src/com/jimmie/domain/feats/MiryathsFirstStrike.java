package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class MiryathsFirstStrike extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.MIRYATHS_FIRST_STRIKE;
	}

	@Override
	public String getName() {
		return "Miryath's First Strike";
	}

	@Override
	public String getBenefit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
