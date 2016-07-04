package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class LowCrawl extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.LOW_CRAWL;
	}

	@Override
	public String getName() {
		return "Low Crawl";
	}

	@Override
	public String getBenefit() {
		return "You can shift while prone";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
