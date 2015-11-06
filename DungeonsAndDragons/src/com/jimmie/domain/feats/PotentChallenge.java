package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Fighter;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PotentChallenge extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.POTENT_CHALLENGE;
	}

	@Override
	public String getName() {
		return "Potent Challenge";
	}

	@Override
	public String getBenefit() {
		return "Add Con modifier damage to target hit with opportunity attack";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getConstitution() >= 15) && (Fighter.class.isInstance(pc.getDndClass()))) {
			// The book says that Combat Challenge is a prerequisite, but all Fighters have it, so I'm not checking for it.
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
