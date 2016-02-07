package com.jimmie.util.aspects;

import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryInvisibility;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.powers.AttackPower;
import com.jimmie.powers.Power;
import com.jimmie.util.Utils;

public aspect ProcessPowerAspect {
	public pointcut process(Creature user, Power power) : execution(boolean com.jimmie.powers.Power+.process(Creature))
	&& args(user) && target(power);
	
	before(Creature user, Power power) : process(user, power) {
		// This is a good place to see if it is a gnome that has used Fade Away.  They are supposed to stop being invisible when they make an attack.
		if (com.jimmie.domain.creatures.monsters.Gnome.class.isAssignableFrom(user.getClass())) {
			// Loop through temporary effects to find the fade away invisibility.
			for (TemporaryEffect tempEffect : user.getTemporaryEffects()) {
				if (TemporaryInvisibility.class.isAssignableFrom(tempEffect.getClass())) {
					if (AttackPower.class.isAssignableFrom(power.getClass())) {
						if (tempEffect.getReason() == TemporaryEffectReason.FADE_AWAY) {
							Utils.print(user.getName() + " is making an attack.  The Fade Away invisibility no longer applies.");
							user.getTemporaryEffects().remove(tempEffect);
							break;						
						}
					}
				}
			}
		}
		
		power.setUser(user);
	}
}
