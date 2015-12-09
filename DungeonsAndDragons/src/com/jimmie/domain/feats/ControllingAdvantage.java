package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ControllingAdvantage extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.CONTROLLING_ADVANTAGE;
	}

	@Override
	public String getName() {
		return "Controlling Advantage";
	}

	@Override
	public String getBenefit() {
		return "Add 1 square to forced movement if you have combat advantage";
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
