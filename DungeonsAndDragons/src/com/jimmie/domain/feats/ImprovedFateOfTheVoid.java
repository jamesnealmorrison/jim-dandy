package com.jimmie.domain.feats;

import com.jimmie.domain.classes.EldritchPact;
import com.jimmie.domain.classes.Warlock;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedFateOfTheVoid extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_FATE_OF_THE_VOID;
	}

	@Override
	public String getName() {
		return "Improved Fate of the Void";
	}

	@Override
	public String getBenefit() {
		return "Pact boon grants additional +1 bonus to die roll";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (((pc.getConstitution() >= 13) || (pc.getCharisma() >= 13)) && (Warlock.class.isInstance(pc.getDndClass()))) {
			Warlock warlock = (Warlock) pc.getDndClass();
			if (warlock.getEldritchPact() == EldritchPact.STAR_PACT) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
