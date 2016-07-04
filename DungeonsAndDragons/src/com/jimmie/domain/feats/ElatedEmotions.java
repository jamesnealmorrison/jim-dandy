package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Ardent;
import com.jimmie.domain.classes.ArdentMantle;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ElatedEmotions extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ELATED_EMOTIONS;
	}

	@Override
	public String getName() {
		return "Elated Emotions";
	}

	@Override
	public String getBenefit() {
		return "Mantle's bonus to Diplomacy and Intimidate equals Con modifier";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Ardent.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Ardent) pc.getDndClass()).getArdentMantle() == ArdentMantle.MANTLE_OF_ELATION) {
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
