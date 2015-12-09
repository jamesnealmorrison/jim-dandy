package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Minotaur;
import com.jimmie.domain.creatures.PlayerCharacter;

public class BloodiedFerocity extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BLOODIED_FEROCITY;
	}

	@Override
	public String getName() {
		return "Bloodied Ferocity";
	}

	@Override
	public String getBenefit() {
		return "Make free melee basic attack when first bloodied";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Minotaur.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
