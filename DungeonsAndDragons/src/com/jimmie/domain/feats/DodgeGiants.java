package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Dwarf;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DodgeGiants extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DODGE_GIANTS;
	}

	@Override
	public String getName() {
		return "Dodge Giants";
	}

	@Override
	public String getBenefit() {
		return "+1 to AC and Reflex against attacks of Large or larger foes";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Dwarf.class.isInstance(pc.getRace())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
