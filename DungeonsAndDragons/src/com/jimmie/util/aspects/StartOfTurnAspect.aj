package com.jimmie.util.aspects;

import java.util.Iterator;

import com.jimmie.domain.ActionType;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.TemporaryOngoingDamage;
import com.jimmie.domain.Zone;
import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public aspect StartOfTurnAspect {
	public pointcut startOfTurn(Creature creature) : execution(* com.jimmie.domain.creatures.*.startOfTurn(..))
	&& target(creature);
	
	before(Creature creature) : startOfTurn(creature) {
		if (creature.getDndClass() != null) {
			// Warden Font Of Life:
			if (Warden.class.isAssignableFrom(creature.getDndClass().getClass())) {
				Utils.print("As a warden, with Font of Life, you can make one saving throw at the start of your turn.");
				creature.performSavingThrows(1, 0);
			}
			// Initialize Sorcerer's First Attack Roll
			if (Sorcerer.class.isAssignableFrom(creature.getDndClass().getClass())) {
				((Sorcerer) creature.getDndClass()).setFirstAttackRoll(0);
				((Sorcerer) creature.getDndClass()).setUnfetteredPower(0);
			}
		}
		
	}
	
	after(Creature creature) : startOfTurn(creature) {
		// See if this is a Runepriest that has a "Rune of the Undeniable Dawn" zone that they might want to sustain with a minor action.
		if (creature.getDndClass() != null) {
			if (Runepriest.class.isAssignableFrom(creature.getDndClass().getClass())) {
				// See if there is a zone.
				if (Encounter.getEncounter().getZones() != null) {
					for (Zone zone : Encounter.getEncounter().getZones()) {
						if ((zone.getOwner() == creature) && (zone.stillApplies())) {
							Utils.print(creature.getName() + " has a Rune of the Undeniable Dawn zone.  Would you like to maintain it for another turn with a minor action?");
							Utils.print("Your choice (Y or N):");
							if (Utils.getYesOrNoInput().equalsIgnoreCase("Y")) {
								creature.useAction(ActionType.MINOR);
								zone.setStartTurn(creature.getCurrentTurn());
							}
						}
					}
				}
			}
		}
		
		// Is the creature taking ongoing damage.
		for (Iterator<TemporaryEffect> it = creature.getTemporaryEffects().iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();
			if (TemporaryOngoingDamage.class.isAssignableFrom(tempEffect.getClass())) {
				TemporaryOngoingDamage damage = (TemporaryOngoingDamage) tempEffect;
				if (damage.stillApplies()) {
					Utils.print(creature.getName() + " is taking on going " + damage.getModifier() + " " + damage.getDamageType() + " damage.");
					creature.hurt(damage.getModifier(), damage.getDamageType(), true, damage.getSource());
				}
			}
		}
	}
}
