package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Dragonborn;
import com.jimmie.domain.creatures.PlayerCharacter;

public class EnlargedDragonBreath extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ENLARGED_DRAGON_BREATH;
	}

	@Override
	public String getName() {
		return "Enlarged Dragon Breath";
	}

	@Override
	public String getBenefit() {
		return "Dragon breath becomes blast 5";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Dragonborn.class.isInstance(pc.getRace())) {
			// The book says that Dragon Breath is a prerequisite, but all dragonborns have it, so I'm not checking for it.
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
