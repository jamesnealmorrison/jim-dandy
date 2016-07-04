package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Battlemind;
import com.jimmie.domain.classes.PsionicStudy;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedSpeedOfThought extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_SPEED_OF_THOUGHT;
	}

	@Override
	public String getName() {
		return "Improved Speed Of Thought";
	}

	@Override
	public String getBenefit() {
		return "Your speed of thought movement increases by 2 squares";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Battlemind.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Battlemind) pc.getDndClass()).getPsionicStudy() == PsionicStudy.SPEED_OF_THOUGHT) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
