package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Fighter;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ShieldPush extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SHIELD_PUSH;
	}

	@Override
	public String getName() {
		return "Shield Push";
	}

	@Override
	public String getBenefit() {
		return "Push 1 square to target hit by Combat Challenge attack";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Fighter.class.isInstance(pc.getDndClass())) {
			// The book says Combat Challenge is a prerequisite, but all fighters have it, so I'm not checking for it.
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
