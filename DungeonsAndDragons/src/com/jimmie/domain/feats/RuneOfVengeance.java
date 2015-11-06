package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class RuneOfVengeance extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RUNE_OF_VENGEANCE;
	}

	@Override
	public String getName() {
		return "Rune Of Vengeance";
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
