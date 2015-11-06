package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.HalfElf;
import com.jimmie.domain.creatures.Human;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ActionRecovery extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ACTION_RECOVERY;
	}

	@Override
	public String getName() {
		return "Action Recovery";
	}

	@Override
	public String getBenefit() {
		return "Gain extra saving throws by spending action point";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon feat.
		if (pc.getLevel() >= 10) {
			if ((Human.class.isInstance(pc.getRace())) || (HalfElf.class.isInstance(pc.getRace()))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
