package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DevotedPaladin extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DEVOTED_PALADIN;
	}

	@Override
	public String getName() {
		return "Devoted Paladin";
	}

	@Override
	public String getBenefit() {
		return "Gain 1 healing suge, add Charisma modifier to lay on hands";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Paladin.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
