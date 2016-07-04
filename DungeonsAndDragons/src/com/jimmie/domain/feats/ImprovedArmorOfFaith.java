package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedArmorOfFaith extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_ARMOR_OF_FAITH;
	}

	@Override
	public String getName() {
		return "Improved Armor of Faith";
	}

	@Override
	public String getBenefit() {
		return "Increases armor of faith bonus to AC";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Avenger.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
