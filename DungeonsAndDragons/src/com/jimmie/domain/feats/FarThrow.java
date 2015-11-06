package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class FarThrow extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.FAR_THROW;
	}

	@Override
	public String getName() {
		return "Far Throw";
	}

	@Override
	public String getBenefit() {
		return "Increase thrown weapon range by 2 squares";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getStrength() >= 13) {
			return true;
		}		
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
