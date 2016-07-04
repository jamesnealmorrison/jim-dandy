package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.PlayerCharacter;

public class SuddenRoots extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SUDDEN_ROOTS;
	}

	@Override
	public String getName() {
		return "Sudden Roots";
	}

	@Override
	public String getBenefit() {
		return "Enemy hit by opportuniy attack is slowed";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Warden.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
