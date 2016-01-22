package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ExpertRitualist extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.EXPERT_RITUALIST;
	}

	@Override
	public String getName() {
		return "Expert Ritualist";
	}

	@Override
	public String getBenefit() {
		return "+2 bonus to ritual skill checks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.hasFeat(FeatType.RITUAL_CASTER)) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
