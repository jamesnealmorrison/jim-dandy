package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.classes.AvengersCensure;
import com.jimmie.domain.creatures.PlayerCharacter;

public class GuaranteedRetribution extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.GUARANTEED_RETRIBUTION;
	}

	@Override
	public String getName() {
		return "Guaranteed Retribution";
	}

	@Override
	public String getBenefit() {
		return "+1 to next attack roll against oath of enmity target when another enemy hits you";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Avenger.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Avenger) pc.getDndClass()).getCensure() == AvengersCensure.CENSURE_OF_RETRIBUTION) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
