package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Dragonborn;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DragonbornFrenzy extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DRAGONBORN_FRENZY;
	}

	@Override
	public String getName() {
		return "Dragonborn Frenzy";
	}

	@Override
	public String getBenefit() {
		return "+2 damage when bloodied";
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
