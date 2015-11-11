package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Ranger;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PreciseHunter extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PRECISE_HUNTER;
	}

	@Override
	public String getName() {
		return "Precise Hunter";
	}

	@Override
	public String getBenefit() {
		return "Allies gain +1 attack against target hit by critical hit";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getWisdom() >= 15) && (Ranger.class.isInstance(pc.getDndClass()))) {
			/* The book says that Hunter's Quarry is a prerequisite, but ALL Rangers have it, so I'm not checking for it. */
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
