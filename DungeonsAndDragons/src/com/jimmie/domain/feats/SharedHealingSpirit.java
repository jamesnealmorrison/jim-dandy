package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Shaman;
import com.jimmie.domain.creatures.PlayerCharacter;

public class SharedHealingSpirit extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SHARED_HEALING_SPIRIT;
	}

	@Override
	public String getName() {
		return "Shared Healing Spirit";
	}

	@Override
	public String getBenefit() {
		return "Change recipient of additional hit points";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Shaman.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
