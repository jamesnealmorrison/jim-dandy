package com.jimmie.util.aspects;

import java.util.Iterator;
import java.util.List;

import com.jimmie.domain.TemporaryAidAnotherBonus;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.Zone;
import com.jimmie.domain.ZoneType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Deva;
import com.jimmie.domain.creatures.monsters.KoboldMinion;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public aspect DefenseAspect {
	public pointcut getDefense(Creature defender, Creature attacker) : (execution(int com.jimmie.domain.creatures.Creature+.getArmorClass(..)) ||
	execution(int com.jimmie.domain.creatures.Creature+.getWill(..)) || execution(int com.jimmie.domain.creatures.Creature+.getReflex(..)) ||
	execution(int com.jimmie.domain.creatures.Creature+.getFortitude(..)))
	&& args(attacker) && target(defender);

	public pointcut getReflex(Creature defender, Creature attacker) : execution(int com.jimmie.domain.creatures.Creature+.getReflex(..))
	&& args(attacker) && target(defender);
	
	public pointcut getWill(Creature defender, Creature attacker) : execution(int com.jimmie.domain.creatures.Creature+.getWill(..))
	&& args(attacker) && target(defender);

	public pointcut getFortitude(Creature defender, Creature attacker) : execution(int com.jimmie.domain.creatures.Creature+.getFortitude(..))
	&& args(attacker) && target(defender);

	public pointcut getArmorClass(Creature defender, Creature attacker) : execution(int com.jimmie.domain.creatures.Creature+.getArmorClass(..))
	&& args(attacker) && target(defender);
	
	int around(Creature defender, Creature attacker) : getReflex(defender, attacker) {
		int modifier = 0;
		
		/* See if there is a temporary modifier to the reflex. */
		if (defender.getTemporaryEffects() != null) {
			for (Iterator<TemporaryEffect> it = defender.getTemporaryEffects().iterator(); it.hasNext();) {
				TemporaryEffect tempEffect = it.next();
				if (tempEffect.getEffectType() == TemporaryEffectType.REF_MOD) {
					if (tempEffect.stillApplies()) {
						Utils.print(defender.getName() + " is supposed to get a modifier to reflex until " + tempEffect.getDuration());
						modifier = modifier + tempEffect.getModifier();
						Utils.print("Modifier still applies.");
						/* If it should be removed now, delete the modifier now. */
						if (tempEffect.shouldBeRemoved()) {
							Utils.print("Modifier will no longer apply.");
							it.remove();
						}
					} else {
						/* modifier is over.  Reset the modifier. */
						it.remove();
						Utils.print("Modifier no longer applies.  Resetting modifier.");
					}
				}
			}
		}
		int total = modifier + proceed(defender, attacker);
		return total;
	}

	int around(Creature defender, Creature attacker) : getFortitude(defender, attacker) {
		int modifier = 0;
		
		/* See if there is a temporary modifier to the reflex. */
		if (defender.getTemporaryEffects() != null) {
			for (Iterator<TemporaryEffect> it = defender.getTemporaryEffects().iterator(); it.hasNext();) {
				TemporaryEffect tempEffect = it.next();
				if (tempEffect.getEffectType() == TemporaryEffectType.FORT_MOD) {
					if (tempEffect.stillApplies()) {
						Utils.print(defender.getName() + " is supposed to get a modifier to fortitude until " + tempEffect.getDuration());
						modifier = modifier + tempEffect.getModifier();
						Utils.print("Modifier still applies.");
						/* If it should be removed now, delete the modifier now. */
						if (tempEffect.shouldBeRemoved()) {
							Utils.print("Modifier will no longer apply.");
							it.remove();
						}
					} else {
						/* modifier is over.  Reset the modifier. */
						it.remove();
						Utils.print("Modifier no longer applies.  Resetting modifier.");
					}
				}
			}
		}
		int total = modifier + proceed(defender, attacker);
		return total;
	}

	int around(Creature defender, Creature attacker) : getDefense(defender, attacker) {
		int modifier = 0;

		/* See if there is a temporary bonus to all defenses. */
		if (defender.getTemporaryEffects() != null) {
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
		}
		
		// Deva Astral Majesty
		if (defender.getRace() != null) {
			if (Deva.class.isAssignableFrom(defender.getRace().getClass())) {
				if (attacker != null) {
					if (attacker.isBloodied()) {
						Utils.print("As a Deva, you get a +1 bonus to this defense because " + attacker.getName() + " is bloodied.");
						modifier += 1;
					}
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
		
		// Kobold Minions have a -2 penalty to defenses if they are not adjacent to another minion.
		if (KoboldMinion.class.isAssignableFrom(defender.getClass())) {
			List<Creature> adjacentMonsters = Encounter.getEncounter().getAdjacentMonsters(defender.getCurrentPosition());
			if (adjacentMonsters != null) {
				boolean foundMinion = false;
				for (Creature monster : adjacentMonsters) {
					if ((monster != defender) && (KoboldMinion.class.isAssignableFrom(monster.getClass()))) {
						foundMinion = true;
						break;
					}
				}
				if (foundMinion == false) {
					Utils.print(defender.getName() + " is not adjacent to any other Kobold Minions.  So they take a -2 penalty to all defenses.");
					modifier -= 2;
				}
			}
		}
		
		int total = modifier + proceed(defender, attacker);
		return total;
		
	}

}
