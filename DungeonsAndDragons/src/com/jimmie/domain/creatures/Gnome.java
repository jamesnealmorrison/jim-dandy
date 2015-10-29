package com.jimmie.domain.creatures;

import com.jimmie.domain.DurationType;
import com.jimmie.util.Utils;

public class Gnome extends Race {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FADE_AWAY = "Fade Away";
	private boolean usedFadeAway;

	@Override
	public int getRacialDamageBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initializeForEncounter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processAfterHurtEffects(Creature creature) {
		if (!usedFadeAway) {
			Utils.print("As a gnome you can use your Fade Away power now to turn invisible for one turn.  Do you want to?");
			String choice = Utils.getYesOrNoInput();
			if ("Y".equalsIgnoreCase(choice)) {
				usedFadeAway = true;
				owner.setTemporaryInvisibility(owner, DurationType.END_OF_NEXT_TURN, null);

			}
		}
		
	}
}
