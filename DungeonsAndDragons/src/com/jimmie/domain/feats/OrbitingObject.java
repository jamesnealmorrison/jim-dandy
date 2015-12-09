package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.classes.PsionDiscipline;
import com.jimmie.domain.creatures.PlayerCharacter;

public class OrbitingObject extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ORBITING_OBJECT;
	}

	@Override
	public String getName() {
		return "Orbiting Object";
	}

	@Override
	public String getBenefit() {
		return "Sustain far hand for free if target is adjacent or in your space";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Psion.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Psion) pc.getDndClass()).getDisciplineFocus() == PsionDiscipline.TELEKINESIS_FOCUS) {
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
