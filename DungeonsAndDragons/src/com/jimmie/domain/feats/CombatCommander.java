package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Warlord;
import com.jimmie.domain.creatures.PlayerCharacter;

public class CombatCommander extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.COMBAT_COMMANDER;
	}

	@Override
	public String getName() {
		return "Combat Commander";
	}

	@Override
	public String getBenefit() {
		return "Bonus to Combat Leader equals Cha or Int modifier";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if ((Warlord.class.isInstance(pc.getDndClass()))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
