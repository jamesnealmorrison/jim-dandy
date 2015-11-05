package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.HalfElf;
import com.jimmie.domain.creatures.Human;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ActionSurge extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ACTION_SURGE;
	}

	@Override
	public String getName() {
		return "Action Surge";
	}

	@Override
	public String getBenefit() {
		return "+2 to attacks when you spend an action point";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((Human.class.isInstance(pc.getRace())) || (HalfElf.class.isInstance(pc.getRace()))) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// no choices for this feat.
	}

}
