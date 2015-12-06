package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.PlayerCharacter;

public class RevitalizingFontOfLife extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.REVITALIZING_FONT_OF_LIFE;
	}

	@Override
	public String getName() {
		return "Revitalizing Font Of Life";
	}

	@Override
	public String getBenefit() {
		return "Successful Font Of Life saving throw grants +2 bonus to other saving throws";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Warden.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
