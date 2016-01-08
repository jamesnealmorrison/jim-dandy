package com.jimmie.util.aspects;

import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Deva;
import com.jimmie.powers.MemoryOfAThousandLifetimes;
import com.jimmie.powers.Power;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public aspect SavingThrowAspect {
	public pointcut savingThrow(Creature creature) : execution(int com.jimmie.domain.creatures.Creature.rollForSavingThrow(..))
	&& this(creature);
	
	int around(Creature creature) : savingThrow(creature) {
		
		int result = proceed(creature);
		
		// Deva Memory of a Thousand Lifetimes
		if (creature.getRace() != null) {
			if (Deva.class.isAssignableFrom(creature.getRace().getClass())) {
				// Look for the "Memory of a Thousand Lifetimes" power
				for (Power power : creature.getPowers()) {
					if (MemoryOfAThousandLifetimes.class.isAssignableFrom(power.getClass())) {
						if (power.getTimesUsed() == 0) {
							Utils.print("You are a Deva with the Memory of a Thousand Lifetimes power.  Would you like to add 1d6 to this roll?");
							Utils.print("Your choice (Y or N):");
							if ("Y".equalsIgnoreCase(Utils.getYesOrNoInput())) {
								Dice d = new Dice(DiceType.SIX_SIDED);
								result += d.roll(DiceRollType.SAVING_THROW_ROLL_MODIFICATION);
								power.setTimesUsed(power.getTimesUsed()+1);
							}
						}
					}
				}
			}
		}
		
		return result;
	}

}
