package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.creatures.PlayerCharacter;

public class BardicKnowledge extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BARDIC_KNOWLEDGE;
	}

	@Override
	public String getName() {
		return "Bardic Knowledge";
	}

	@Override
	public String getBenefit() {
		return "+2 bonus to several skill checks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Bard.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
