package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Goliath;
import com.jimmie.domain.creatures.PlayerCharacter;

public class MarkingsOfTheVictor extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.MARKINGS_OF_THE_VICTOR;
	}

	@Override
	public String getName() {
		return "Markings of the Victor";
	}

	@Override
	public String getBenefit() {
		return "Roll twice for first attack roll each encounter";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Goliath.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
