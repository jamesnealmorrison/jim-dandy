package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Ardent;
import com.jimmie.domain.creatures.PlayerCharacter;

public class BolsteringMantle extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BOLSTERING_MANTLE;
	}

	@Override
	public String getName() {
		return "Bolstering Mantle";
	}

	@Override
	public String getBenefit() {
		return "Spend healing surge, ally gains temporary hp or saving throw";
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
