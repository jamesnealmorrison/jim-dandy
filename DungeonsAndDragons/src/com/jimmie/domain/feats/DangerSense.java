package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DangerSense extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DANGER_SENSE;
	}

	@Override
	public String getName() {
		return "Danger Sense";
	}

	@Override
	public String getBenefit() {
		return "Roll twice for initiative, use the higher result";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
