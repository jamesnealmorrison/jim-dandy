package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ArmorOfBahamut extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ARMOR_OF_BAHAMUT;
	}

	@Override
	public String getName() {
		return "Armor of Bahamut";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke armor of Bahamut.";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
