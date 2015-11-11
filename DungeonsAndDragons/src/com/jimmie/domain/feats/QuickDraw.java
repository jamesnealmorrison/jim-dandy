package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class QuickDraw extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.QUICK_DRAW;
	}

	@Override
	public String getName() {
		return "Quick Draw";
	}

	@Override
	public String getBenefit() {
		return "Draw a weapon with attack action, +2 to initiative";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getDexterity() >= 13) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
