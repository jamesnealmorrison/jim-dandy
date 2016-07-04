package com.jimmie.domain.creatures.monsters;

public abstract class Gnome extends Monster {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// TODO: Reactive Stealth, shadow skulk
	private boolean usedFadeAway = false;
	public boolean isUsedFadeAway() {
		return usedFadeAway;
	}
	public void setUsedFadeAway(boolean usedFadeAway) {
		this.usedFadeAway = usedFadeAway;
	}

}
