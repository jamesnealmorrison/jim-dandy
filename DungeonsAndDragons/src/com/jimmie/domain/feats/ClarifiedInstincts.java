package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Ardent;
import com.jimmie.domain.classes.ArdentMantle;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ClarifiedInstincts extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.CLARIFIED_INSTINCTS;
	}

	@Override
	public String getName() {
		return "Clarified Instincts";
	}

	@Override
	public String getBenefit() {
		return "Mantle's bonus to Insight and Perception equals Wis modifier";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Ardent.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Ardent) pc.getDndClass()).getArdentMantle() == ArdentMantle.MANTLE_OF_CLARITY) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
