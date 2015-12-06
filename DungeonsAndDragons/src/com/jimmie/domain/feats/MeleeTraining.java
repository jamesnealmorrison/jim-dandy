package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class MeleeTraining extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.MELEE_TRAINING;
	}

	@Override
	public String getName() {
		return "Melee Training";
	}

	@Override
	public String getBenefit() {
		return "Change ability used for melee basic weapon attacks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
