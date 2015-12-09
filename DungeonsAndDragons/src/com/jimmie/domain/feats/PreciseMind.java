package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PreciseMind extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PRECISE_MIND;
	}

	@Override
	public String getName() {
		return "Precise Mind";
	}

	@Override
	public String getBenefit() {
		return "+1 attack with unaugmented psionic at-wills after you hit with augmented psionic attack power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Psion.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
