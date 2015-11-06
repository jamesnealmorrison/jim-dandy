package com.jimmie.domain.feats;

import com.jimmie.domain.SkillType;
import com.jimmie.domain.creatures.PlayerCharacter;

public class EscapeArtist extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ESCAPE_ARTIST;
	}

	@Override
	public String getName() {
		return "Escape Artist";
	}

	@Override
	public String getBenefit() {
		return "Escape a grab as minor action, +2 to Acrobatics";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getSkill(SkillType.ACROBATICS).isTrained()) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
