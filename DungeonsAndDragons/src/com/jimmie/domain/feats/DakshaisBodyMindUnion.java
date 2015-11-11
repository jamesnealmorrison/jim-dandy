package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DakshaisBodyMindUnion extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DAKSHAIS_BODY_MIND_UNION;
	}

	@Override
	public String getName() {
		return "Dakshai's Body Mind Union";
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
