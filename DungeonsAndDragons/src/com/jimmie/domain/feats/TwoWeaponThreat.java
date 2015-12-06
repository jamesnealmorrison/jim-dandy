package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class TwoWeaponThreat extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.TWO_WEAPON_THREAT;
	}

	@Override
	public String getName() {
		return "Two Weapon Threat";
	}

	@Override
	public String getBenefit() {
		return "+3 damage on opportunity attacks with two melee weapons";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getDexterity() >= 13) {
			if (pc.getFeats() != null) {
				for (Feat feat : pc.getFeats()) {
					if (feat.getType() == FeatType.TWO_WEAPON_FIGHTING) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
