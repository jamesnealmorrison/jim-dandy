package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Battlemind;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PunishingSpike extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PUNISHING_SPIKE;
	}

	@Override
	public String getName() {
		return "Punishing Spike";
	}

	@Override
	public String getBenefit() {
		return "Mind spike imposes -2 penalty to target's next saving throw";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Battlemind.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
