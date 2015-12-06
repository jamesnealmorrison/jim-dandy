package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Invoker;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ResonatingCovenant extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RESONATING_COVENANT;
	}

	@Override
	public String getName() {
		return "Resonating Covenant";
	}

	@Override
	public String getBenefit() {
		return "+1 attack with at-will after using encounter or daily power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Invoker.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
