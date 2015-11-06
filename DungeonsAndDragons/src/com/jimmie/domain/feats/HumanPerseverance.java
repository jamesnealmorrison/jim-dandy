package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.HalfElf;
import com.jimmie.domain.creatures.Human;
import com.jimmie.domain.creatures.PlayerCharacter;

public class HumanPerseverance extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HUMAN_PERSEVERANCE;
	}

	@Override
	public String getName() {
		return "Human Perseverance";
	}

	@Override
	public String getBenefit() {
		return "+1 to saving throws";
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
	}

}
