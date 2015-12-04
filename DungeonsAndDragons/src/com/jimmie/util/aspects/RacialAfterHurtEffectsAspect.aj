package com.jimmie.util.aspects;

import com.jimmie.domain.DurationType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Gnome;
import com.jimmie.domain.creatures.HalfOrc;
import com.jimmie.domain.creatures.Race;
import com.jimmie.util.Utils;

public aspect RacialAfterHurtEffectsAspect {
	pointcut afterHurtEffects() : execution(* com.jimmie.domain.creatures.*.hurt(..));
	
	
	after() : afterHurtEffects() {
		Utils.print("I am processing the racial after hurt effects");
		Utils.print("This join point = " + thisJoinPoint);
		Object o = thisJoinPoint.getThis();
		Creature creature = null;
		Race race = null;
		if (Creature.class.isAssignableFrom(o.getClass())) {
			creature = (Creature) o;
			race = creature.getRace();
			if ((race != null) && (Gnome.class.isAssignableFrom(race.getClass()))) {
				Gnome gnome = (Gnome) race;
				if (gnome.isUsedFadeAway()) {
					Utils.print("As a gnome you can use your Fade Away power now to turn invisible for one turn.  Do you want to?");
					String choice = Utils.getYesOrNoInput();
					if ("Y".equalsIgnoreCase(choice)) {
						gnome.setUsedFadeAway(true);
						creature.setTemporaryInvisibility(creature, DurationType.END_OF_NEXT_TURN, null);

					}
				}
			}

			if ((race != null) && (HalfOrc.class.isAssignableFrom(race.getClass()))) {
				HalfOrc halfOrc = (HalfOrc) race;

				/* Have they used half orc resilience yet? */
				if (!halfOrc.isUsedHalfOrcResilience()) {
					if (creature.isBloodied()) {
						Utils.print("This is the first time " + creature.getName() + " has been bloodied during this encounter.  Half orc resilience gives them 5 temporary hit points.");
						creature.setTemporaryHitPoints(5);
						halfOrc.setUsedHalfOrcResilience(true);
					}
				}
			}
		}
	}
}
