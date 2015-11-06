package com.jimmie.domain.feats;

import com.jimmie.domain.classes.CommandingPresence;
import com.jimmie.domain.classes.Warlord;
import com.jimmie.domain.creatures.PlayerCharacter;

public class InspiredRecovery extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.INSPIRED_RECOVERY;
	}

	@Override
	public String getName() {
		return "Inspired Recovery";
	}

	@Override
	public String getBenefit() {
		return "Grant ally saving throw with Cha modifier bonus";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Warlord.class.isInstance(pc.getDndClass())) {
			Warlord warlord = (Warlord) pc.getDndClass();
			if (warlord.getCommandingPresence() == CommandingPresence.INSPIRING_PRESENCE) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
