package com.jimmie.util.aspects;

import java.util.Iterator;
import com.jimmie.domain.TemporaryAidAnotherBonus;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.Zone;
import com.jimmie.domain.ZoneType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Deva;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public aspect DefenseAspect {
	public pointcut getDefense(Creature defender, Creature attacker) : (execution(int com.jimmie.domain.creatures.Creature+.getArmorClass(..)) ||
	execution(int com.jimmie.domain.creatures.Creature+.getWill(..)) || execution(int com.jimmie.domain.creatures.Creature+.getReflex(..)) ||
	execution(int com.jimmie.domain.creatures.Creature+.getFortitude(..)))
	&& args(attacker) && target(defender);

	int around(Creature defender, Creature attacker) : getDefense(defender, attacker) {
		int modifier = 0;
		
		/* See if there is a temporary bonus to all defenses. */
		for (Iterator<TemporaryEffect> it = defender.getTemporaryEffects().iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();

			
			/* See if there is a temporary defense bonus due to the "Aid another" bonus. */
			if (TemporaryAidAnotherBonus.class.isAssignableFrom(tempEffect.getClass())) {
				TemporaryAidAnotherBonus temporaryAidAnotherBonus = (TemporaryAidAnotherBonus) tempEffect;
				if (temporaryAidAnotherBonus.getType() == TemporaryAidAnotherBonus.DEFENSE) {
					if (temporaryAidAnotherBonus.stillApplies() && (temporaryAidAnotherBonus.getTarget() == attacker)) {
						Utils.print(defender.getName() + " is supposed to get a bonus of " + temporaryAidAnotherBonus.getModifier() + " to defense against this attack by " + attacker.getName() + ".");
						modifier += temporaryAidAnotherBonus.getModifier();
						Utils.print("Bonus still applies.");
						temporaryAidAnotherBonus = null;
						Utils.print("One time bonus so bonus no longer applies.  Resetting bonus.");
					} else {
						/* Bonus is over.  Reset the bonus. */
						it.remove();
						Utils.print("Bonus no longer applies.  Resetting bonus.");
					}
				} 				
			}
		}
		
		// Deva Astral Majesty
		if (defender.getRace() != null) {
			if (Deva.class.isAssignableFrom(defender.getRace().getClass())) {
				if (attacker.isBloodied()) {
					Utils.print("As a Deva, you get a +1 bonus to this defense because " + attacker.getName() + " is bloodied.");
					modifier += 1;
				}
			}
		}
		
		// Are you in a "Rune of the undeniable dawn" zone?
		if (Encounter.getEncounter().getZones() != null) {
			for (Iterator<Zone> it = Encounter.getEncounter().getZones().iterator(); it.hasNext();) {
				Zone zone = it.next();
				if (zone.getZoneType() == ZoneType.RUNE_OF_THE_UNDENIABLE_DAWN) {
					if (zone.stillApplies()) {
						// See if the creature is still in the zone, and if they are an ally of the owner.
						if (Encounter.getEncounter().getAlliesWithinRangeOf(zone.getOwner(), zone.getZoneOrigin(), zone.getSize()).contains(defender)) {
							Utils.print(defender.getName() + " gets a +2 power bonus to all defenses while within the Rune of the Undeniable Dawn zone.");
							modifier += 2;
						}
					} else {
						Utils.print("Zone no longer applies.  Removing.");
						it.remove();
					}
				}
			}
		}
		
		return modifier + proceed(defender, attacker);
		
	}

}
