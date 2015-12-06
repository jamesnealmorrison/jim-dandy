package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.classes.AvengersCensure;
import com.jimmie.domain.creatures.PlayerCharacter;

public class InvigoratingPursuit extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.INVIGORATING_PURSUIT;
	}

	@Override
	public String getName() {
		return "Invigorating Pursuit";
	}

	@Override
	public String getBenefit() {
		return "Gain +2 AC and damage when you charge oath of enmity target";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Avenger.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Avenger) pc.getDndClass()).getCensure() == AvengersCensure.CENSURE_OF_PURSUIT) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
