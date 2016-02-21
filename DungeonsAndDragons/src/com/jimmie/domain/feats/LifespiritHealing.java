package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.PlayerCharacter;

public class LifespiritHealing extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.LIFESPIRIT_HEALING;
	}

	@Override
	public String getName() {
		return "Lifespirit Healing";
	}

	@Override
	public String getBenefit() {
		return "Second ally can spend healing surge and make saving throw when you use second wind.";
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
