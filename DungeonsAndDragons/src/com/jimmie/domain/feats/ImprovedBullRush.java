package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedBullRush extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_BULL_RUSH;
	}

	@Override
	public String getName() {
		return "Improved Bull Rush";
	}

	@Override
	public String getBenefit() {
		return "+4 to bull rush attacks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getStrength() >= 13) && (pc.getConstitution() >= 13)) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
