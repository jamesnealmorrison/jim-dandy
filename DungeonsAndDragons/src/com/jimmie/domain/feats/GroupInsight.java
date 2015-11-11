package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.HalfElf;
import com.jimmie.domain.creatures.PlayerCharacter;

public class GroupInsight extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.GROUP_INSIGHT;
	}

	@Override
	public String getName() {
		return "Group Insight";
	}

	@Override
	public String getBenefit() {
		return "Grant allies +1 to Insight and initiative";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (HalfElf.class.isInstance(pc.getRace())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
