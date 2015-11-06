package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Halfling;
import com.jimmie.domain.creatures.PlayerCharacter;

public class LostInTheCrowd extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.LOST_IN_THE_CROWD;
	}

	@Override
	public String getName() {
		return "Lost in the Crowd";
	}

	@Override
	public String getBenefit() {
		return "+2 to AC when adjacent to at least two larger enemies";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Halfling.class.isInstance(pc.getRace())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
