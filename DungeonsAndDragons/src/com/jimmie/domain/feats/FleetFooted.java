package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class FleetFooted extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.FLEET_FOOTED;
	}

	@Override
	public String getName() {
		return "Fleet Footed";
	}

	@Override
	public String getBenefit() {
		return "+1 to speed";
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
		// TODO Auto-generated method stub

	}

}
