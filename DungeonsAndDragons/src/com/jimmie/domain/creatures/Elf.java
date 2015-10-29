package com.jimmie.domain.creatures;

public class Elf extends Race {
	private boolean usedElvenAccuracy;

	public boolean isUsedElvenAccuracy() {
		return usedElvenAccuracy;
	}

	public void setUsedElvenAccuracy(boolean usedElvenAccuracy) {
		this.usedElvenAccuracy = usedElvenAccuracy;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getRacialDamageBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initializeForEncounter() {
		usedElvenAccuracy = false;		
	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processAfterHurtEffects(Creature creature) {
		// TODO Auto-generated method stub
		
	}
}
