package com.jimmie.domain.feats;

import com.jimmie.domain.classes.CommandingPresence;
import com.jimmie.domain.classes.Warlord;
import com.jimmie.domain.creatures.PlayerCharacter;

public class TacticalAssault extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.TACTICAL_ASSAULT;
	}

	@Override
	public String getName() {
		return "Tactical Assault";
	}

	@Override
	public String getBenefit() {
		return "Ally gains bonus to damage equal to your Int modifier";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Warlord.class.isInstance(pc.getDndClass())) {
			Warlord warlord = (Warlord) pc.getDndClass();
			if (warlord.getCommandingPresence() == CommandingPresence.TACTICAL_PRESENCE) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
