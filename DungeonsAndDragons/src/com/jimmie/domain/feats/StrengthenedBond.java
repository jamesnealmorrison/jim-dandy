package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Seeker;
import com.jimmie.domain.creatures.PlayerCharacter;

public class StrengthenedBond extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.STRENGTHENED_BOND;
	}

	@Override
	public String getName() {
		return "Strengthened Bond";
	}

	@Override
	public String getBenefit() {
		return "Regain Seeker's Bond power when first bloodied";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Seeker.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
