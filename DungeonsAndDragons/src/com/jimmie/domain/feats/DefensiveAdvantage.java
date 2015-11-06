package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DefensiveAdvantage extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DEFENSIVE_ADVANTAGE;
	}

	@Override
	public String getName() {
		return "Defensive Advantage";
	}

	@Override
	public String getBenefit() {
		return "+2 AC when you have combat advantage against enemy";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if (pc.getDexterity() >= 17) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
