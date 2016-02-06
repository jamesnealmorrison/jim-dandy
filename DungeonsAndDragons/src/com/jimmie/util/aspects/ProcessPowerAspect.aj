package com.jimmie.util.aspects;

import com.jimmie.domain.creatures.Creature;
import com.jimmie.powers.Power;

public aspect ProcessPowerAspect {
	public pointcut process(Creature user, Power power) : execution(boolean com.jimmie.powers.Power+.process(Creature))
	&& args(user) && target(power);
	
	before(Creature user, Power power) : process(user, power) {
		power.setUser(user);
	}
}
