package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class WeaponExpertise extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.WEAPON_EXPERTISE;
	}

	@Override
	public String getName() {
		return "Weapon Expertise";
	}

	@Override
	public String getBenefit() {
		return "+1 to attacks with weapon group";
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
