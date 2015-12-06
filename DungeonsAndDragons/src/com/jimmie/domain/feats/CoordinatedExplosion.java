package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class CoordinatedExplosion extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.COORDINATED_EXPLOSION;
	}

	@Override
	public String getName() {
		return "Coordinated Explosion";
	}

	@Override
	public String getBenefit() {
		return "+1 to attack rolls with blast or burst if ally is in area";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {

	}

}
