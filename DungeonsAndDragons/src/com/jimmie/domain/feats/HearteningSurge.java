package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Ardent;
import com.jimmie.domain.creatures.PlayerCharacter;

public class HearteningSurge extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HEARTENING_SURGE;
	}

	@Override
	public String getName() {
		return "Heartening Surge";
	}

	@Override
	public String getBenefit() {
		return "Increase ardent surge bonus to defenses or attacks by 1";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Ardent.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
