package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Ranger;
import com.jimmie.domain.creatures.PlayerCharacter;

public class AgileHunter extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.AGILE_HUNTER;
	}

	@Override
	public String getName() {
		return "Agile Hunter";
	}

	@Override
	public String getBenefit() {
		return "Shift as a free action after scoring a critical hit.";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getDexterity() >= 15) && (Ranger.class.isInstance(pc.getDndClass()))) {
			/* The book says that Hunter's Quarry is a prerequisite, but ALL Rangers have it, so I'm not checking for it. */
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
