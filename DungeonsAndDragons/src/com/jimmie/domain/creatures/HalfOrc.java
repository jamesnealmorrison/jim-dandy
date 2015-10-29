package com.jimmie.domain.creatures;

import com.jimmie.domain.DiceType;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class HalfOrc extends Race {

	@Override
	public void initializeForEncounter() {
		usedFuriousAssault = false;
		usedHalfOrcResilience = false;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FURIOUS_ASSAULT = "FuriousAssault";
	public static final String HALF_ORC_RESILIENCE = "HalfOrcResilience";
	private boolean usedFuriousAssault;
	private boolean usedHalfOrcResilience;

	@Override
	public int getRacialDamageBonus() {
		if (getMyPowers().contains(FURIOUS_ASSAULT)) {
			/* Can they use furious assault? */
			if (!usedFuriousAssault) {
				/* Do they want to? */
				Utils.print("You have the ability to use the Half Orc free Encounter power Furious Assault to add 1d8 damage. Do you want to?");
				String choice = Utils.getYesOrNoInput();
				if ("Y".equalsIgnoreCase(choice)) {
					usedFuriousAssault = true;
					Dice dice = new Dice(DiceType.EIGHT_SIDED);
					return dice.basicRoll();
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}
		return 0;
	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processAfterHurtEffects(Creature owner) {
		if (getMyPowers().contains(HALF_ORC_RESILIENCE)) {
			/* Have they used half orc resilience yet? */
			if (!usedHalfOrcResilience) {
				if (owner.isBloodied()) {
					Utils.print("This is the first time " + owner.getName() + " has been bloodied during this encounter.  Half orc resilience gives them 5 temporary hit points.");
					owner.setTemporaryHitPoints(5);
					usedHalfOrcResilience = true;
				}
			}
		}	
	}
}
