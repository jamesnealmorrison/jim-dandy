package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Seeker;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedInevitableShot extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_INEVITABLE_SHOT;
	}

	@Override
	public String getName() {
		return "Improved Inevitable Shot";
	}

	@Override
	public String getBenefit() {
		return "Inevitable shot attack ignores cover and concealment";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Seeker.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
