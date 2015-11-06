package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Wizard;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ExpandedSpellbook extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.EXPANDED_SPELLBOOK;
	}

	@Override
	public String getName() {
		return "Expanded Spellbook";
	}

	@Override
	public String getBenefit() {
		return "Add additional daily spell to spellbook.";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((Wizard.class.isInstance(pc.getDndClass())) && (pc.getWisdom() >= 13)) {
			return true;
		}		
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Need to choose the spell.
	}

}
