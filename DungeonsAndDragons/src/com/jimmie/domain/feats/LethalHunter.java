package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Ranger;
import com.jimmie.domain.creatures.PlayerCharacter;

public class LethalHunter extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.LETHAL_HUNTER;
	}

	@Override
	public String getName() {
		return "Leathal Hunter";
	}

	@Override
	public String getBenefit() {
		return "Hunter's Quarry damage dice increase to d8s";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Ranger.class.isInstance(pc.getDndClass())) {
			// The book says that Hunter's Quarry is a prerequisite, but all Rangers have it, so I'm not looking for it.
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
