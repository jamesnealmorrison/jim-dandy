package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class UnarmoredAgility extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.UNARMORED_AGILITY;
	}

	@Override
	public String getName() {
		return "Unarmored Agility";
	}

	@Override
	public String getBenefit() {
		return "+2 AC while wearing cloth armor or no armor";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
