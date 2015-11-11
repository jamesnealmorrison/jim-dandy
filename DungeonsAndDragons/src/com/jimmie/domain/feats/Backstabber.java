package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Rogue;
import com.jimmie.domain.creatures.PlayerCharacter;

public class Backstabber extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BACKSTABBER;
	}

	@Override
	public String getName() {
		return "Backstabber";
	}

	@Override
	public String getBenefit() {
		return "Sneak Attack dice increase to d8s";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Rogue.class.isInstance(pc.getDndClass())) {
			// The book says Sneak Attack is a prerequisite, but all rogues have it, so I'm not checking for it.
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
