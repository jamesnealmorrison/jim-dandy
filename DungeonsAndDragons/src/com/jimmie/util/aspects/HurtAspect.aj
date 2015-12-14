package com.jimmie.util.aspects;

import java.util.Iterator;

import com.jimmie.domain.DamageType;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public aspect HurtAspect {
	public pointcut hurt(Creature hurtee, int damage, DamageType damageType, Encounter encounter, boolean hit, Object hurter) : execution(* com.jimmie.domain.creatures.Creature.hurt(..))
	&& args(damage, damageType, encounter, hit, hurter) && target(hurtee);

	void around(Creature hurtee, int damage, DamageType damageType, Encounter encounter, boolean hit, Object hurter) : hurt(hurtee, damage, damageType, encounter, hit, hurter) {
		Utils.print("Hurter = " + hurter);
		Utils.print("Hurtee = " + hurtee);

		if (Creature.class.isAssignableFrom(hurter.getClass())) {
			Creature c = (Creature) hurter; 

			// Is there a temporary damage modifier?
			for (Iterator<TemporaryEffect> it = c.getTemporaryEffects().iterator(); it.hasNext();) {
				TemporaryEffect tempEffect = it.next();
				if (tempEffect.getEffectType() == TemporaryEffectType.DAMAGE_MODIFIER) {
					if (tempEffect.stillApplies()) {
						Utils.print("There is a temporary damage modifier of " + tempEffect.getModifier());
						damage += tempEffect.getModifier();
						Utils.print("Doing " + damage + " points worth of damage.");
					} else {
						Utils.print("Temporary damage modifier no longer applies.  Removing it.");
						it.remove();
					}
				}
			}
		}
		
		proceed(hurtee, damage, damageType, encounter, hit, hurter);
		
		
	}

}
