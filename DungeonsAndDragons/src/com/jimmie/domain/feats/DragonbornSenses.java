package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Dragonborn;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DragonbornSenses extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DRAGONBORN_SENSES;
	}

	@Override
	public String getName() {
		return "Dragonborn Senses";
	}

	@Override
	public String getBenefit() {
		return "Low-light vision, +1 to Perception";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Dragonborn.class.isInstance(pc.getRace())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
