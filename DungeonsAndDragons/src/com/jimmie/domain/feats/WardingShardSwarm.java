package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Shardmind;

public class WardingShardSwarm extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.WARDING_SHARD_SWARM;
	}

	@Override
	public String getName() {
		return "Warding Shard Swarm";
	}

	@Override
	public String getBenefit() {
		return "+2 defenses when you use shard swarm";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Shardmind.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
