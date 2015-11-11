package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Fighter;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DistractingShield extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DISTRACTING_SHIELD;
	}

	@Override
	public String getName() {
		return "Distracting Shield";
	}

	@Override
	public String getBenefit() {
		return "Target hit by opportunity attack takes -2 to attack rolls";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((Fighter.class.isInstance(pc.getDndClass())) && (pc.getWisdom() >= 13)) {
			// The book says Combat Challenge is a prerequisite, but all fighters have it, so I'm not checking for it.
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
