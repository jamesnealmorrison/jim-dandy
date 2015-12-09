package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Seeker;
import com.jimmie.domain.creatures.PlayerCharacter;

public class InescapableShot extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.INESCAPABLE_SHOT;
	}

	@Override
	public String getName() {
		return "Inescapable Shot";
	}

	@Override
	public String getBenefit() {
		return "Inevitable shot targets enemy within 10 squares of missed target";
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
