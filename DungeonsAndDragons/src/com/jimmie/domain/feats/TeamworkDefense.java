package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class TeamworkDefense extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.TEAMWORK_DEFENSE;
	}

	@Override
	public String getName() {
		return "Teamwork Defense";
	}

	@Override
	public String getBenefit() {
		return "+1 AC when adjacent to ally who also has this feat";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
