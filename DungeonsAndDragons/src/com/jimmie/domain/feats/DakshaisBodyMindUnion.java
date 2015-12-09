package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Githzerai;

public class DakshaisBodyMindUnion extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DAKSHAIS_BODY_MIND_UNION;
	}

	@Override
	public String getName() {
		return "Dakshai's Body Mind Union";
	}

	@Override
	public String getBenefit() {
		return "Expend iron mind to gain saving throw with +5 bonus";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Githzerai.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
