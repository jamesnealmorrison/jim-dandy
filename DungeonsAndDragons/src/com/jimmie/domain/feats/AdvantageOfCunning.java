package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.classes.BardicVirtue;
import com.jimmie.domain.creatures.PlayerCharacter;

public class AdvantageOfCunning extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ADVANTAGE_OF_CUNNING;
	}

	@Override
	public String getName() {
		return "Advantage of Cunning";
	}

	@Override
	public String getBenefit() {
		return "Slide enemy into ally's vacated space";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Bard.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Bard) pc.getDndClass()).getBardicVirtue() == BardicVirtue.VIRTUE_OF_CUNNING) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
