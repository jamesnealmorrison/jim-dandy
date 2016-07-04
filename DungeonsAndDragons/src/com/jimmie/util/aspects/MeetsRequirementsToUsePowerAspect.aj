package com.jimmie.util.aspects;

import com.jimmie.domain.creatures.Creature;
import com.jimmie.powers.Power;
import com.jimmie.powers.StandUp;

public aspect MeetsRequirementsToUsePowerAspect {
	public pointcut meetsRequirements(Power power, Creature creature) : execution(boolean com.jimmie.powers.Power+.meetsRequirementsToUsePower(..))
	&& args(creature) && target(power);

	boolean around(Power power, Creature creature) : meetsRequirements(power, creature) {
		// If they are prone, they can't do much but stand up.
		if (creature.isProne()) {
			if (!StandUp.class.isAssignableFrom(power.getClass())) {
				return false;
			}
		}
		return proceed(power, creature);
	}

}
