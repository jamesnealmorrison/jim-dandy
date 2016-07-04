package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DeadlyDraw extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DEADLY_DRAW;
	}

	@Override
	public String getName() {
		return "Deadly Draw";
	}

	@Override
	public String getBenefit() {
		return "Combat advantage against enemy you pull or slide adjacent";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
