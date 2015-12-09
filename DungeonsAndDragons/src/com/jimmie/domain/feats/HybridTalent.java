package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class HybridTalent extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HYBRID_TALENT;
	}

	@Override
	public String getName() {
		return "Hybrid Talent";
	}

	@Override
	public String getBenefit() {
		return "Gain a hybrid talent option for one of your hybrid class entries";
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
