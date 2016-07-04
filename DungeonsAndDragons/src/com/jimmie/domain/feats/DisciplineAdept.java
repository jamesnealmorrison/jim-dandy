package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DisciplineAdept extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DISCIPLINE_ADEPT;
	}

	@Override
	public String getName() {
		return "Discipline Adept";
	}

	@Override
	public String getBenefit() {
		return "Use each Discipline Focus power twice per encounter";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Psion.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
