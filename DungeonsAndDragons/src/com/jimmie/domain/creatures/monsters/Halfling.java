package com.jimmie.domain.creatures.monsters;

public abstract class Halfling extends Monster {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean usedSecondChance = false;
	
	public boolean isUsedSecondChance() {
		return usedSecondChance;
	}
	public void setUsedSecondChance(boolean usedSecondChance) {
		this.usedSecondChance = usedSecondChance;
	}

}
