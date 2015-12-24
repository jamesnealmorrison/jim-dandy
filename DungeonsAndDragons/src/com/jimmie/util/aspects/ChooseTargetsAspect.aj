package com.jimmie.util.aspects;

import java.util.List;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.items.weapons.Weapon;
import com.jimmie.domain.items.weapons.WeaponProperty;
import com.jimmie.encounters.Encounter;
import com.jimmie.powers.DivineChallenge;
import com.jimmie.powers.Power;
import com.jimmie.powers.WardensFury;
import com.jimmie.powers.WardensGrasp;
import com.jimmie.util.Utils;
import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.Mark;
import com.jimmie.domain.MarkType;
import com.jimmie.domain.RunicState;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;

public aspect ChooseTargetsAspect {
	public pointcut chooseTargets(TurnTaker attacker) : execution(List<AttackTarget> com.jimmie.encounters.Encounter.choose*(TurnTaker, ..))
	&& args(attacker, ..);

	after(TurnTaker attacker) returning(List<AttackTarget> targets) : chooseTargets(attacker) {
		Creature cAttacker = null;
		if (Creature.class.isAssignableFrom(attacker.getClass())) {
			cAttacker = (Creature) attacker;

			if (cAttacker.isMarked()) {
				for (Mark mark : cAttacker.getMarks()) {
					if (mark.getMarkType() == MarkType.NATURES_WRATH) {
						if (!targets.contains(mark.getMarker())) {
							int weaponReach = 1;
							Weapon weapon = mark.getMarker().getReadiedWeapon().getWeapon();
							if (weapon != null) {
								if (weapon.getWeaponProperties().contains(WeaponProperty.REACH)) {
									weaponReach = 2;
								}
							}
							
							List<Creature> adjacentCreatures = Encounter.getEncounter().getMonstersWithinRangeOf(mark.getMarker().getCurrentPosition(), weaponReach);
							
							// Warden's have both Warden's Fury and Warden's Grasp.  To me it looks like Warden's Fury is for when the enemy is adjacent and Warden's Grasp
							// is for when they are not adjacent.  I certainly don't think you should be allowed to do both at once.  So I'm implementing it like that.
							
							// If the attacker is adjacent to the attacker (at least within range of their weapon), use warden's fury.
							if ((adjacentCreatures != null) && (adjacentCreatures.contains(attacker))) {

								// Warden's Fury
								Utils.print(mark.getMarker().getName() + " gets to make a Warden's Fury attack against " + attacker.getName() + " because they are making an attack that doesn't include " + mark.getMarker().getName());
								Utils.print("You're going to be asked which character to attack.  Make sure you pick " + attacker.getName());
								for (Power power : mark.getMarker().getPowers()) {
									if (WardensFury.class.isAssignableFrom(power.getClass())) {
										power.process(mark.getMarker());
										// Check if the critter got killed.
										if (cAttacker.getCurrentHitPoints() <= 0) {
											// Remove all the targets so the attack doesn't happen.
											Utils.print(cAttacker.getName() + " is dead.  Removing targets so they can't attack.");
											targets.removeAll(targets);
										}
										break;
									}
								}
							} else {
								List<Creature> creaturesWithinBurstRange = Encounter.getEncounter().getMonstersWithinRangeOf(mark.getMarker().getCurrentPosition(), 5);
								if ((creaturesWithinBurstRange != null) && (creaturesWithinBurstRange.contains(attacker))) {
									// Warden's Grasp
									Utils.print(mark.getMarker().getName() + " gets to use Warden's Grasp attack against " + attacker.getName() + " because they are making an attack that doesn't include " + mark.getMarker().getName());
									Utils.print("You're going to be asked which character to attack.  Make sure you pick " + attacker.getName());
									for (Power power : mark.getMarker().getPowers()) {
										if (WardensGrasp.class.isAssignableFrom(power.getClass())) {
											power.process(mark.getMarker());
											break;
										}
									}
								}
							}
						}
					} else if (mark.getMarkType() == MarkType.DIVINE_CHALLENGE) {
						if (!targets.contains(mark.getMarker())) {
							Utils.print(attacker.getName() + " will take a -2 penalty to this attack roll because it's attack did not include " + mark.getMarker().getName() + ".");
							cAttacker.setTemporaryEffect(-2, mark.getMarker().getCurrentTurn(), DurationType.SPECIAL, mark.getMarker(), TemporaryEffectType.ATTACK_ROLL_MODIFIER, TemporaryEffectReason.DIVINE_CHALLENGE);
							
							// Find the Divine Challenge power and see if this is the first time this attacker has attacked "not me"
							for (Power power : mark.getMarker().getPowers()) {
								if (DivineChallenge.class.isAssignableFrom(power.getClass())) {
									DivineChallenge divineChallenge = (DivineChallenge) power;
									if (!divineChallenge.hasTakenOneTimePenalty()) {
										int damage = 3 + mark.getMarker().getAbilityModifier(AbilityType.CHARISMA);
										Utils.print(attacker.getName() + " takes a " + damage + " damage because of Divine Challenge.");
										cAttacker.hurt(damage, DamageType.RADIANT, true, mark.getMarker());
										divineChallenge.setTakenOneTimePenalty(true);
									}
								}
							}
						}
					}
				}
			} // Attacker is marked.
			
			// Look for Runepriest rune of destruction
			// First, loop through the targets.
			for (AttackTarget target : targets) {
				if (Creature.class.isAssignableFrom(target.getClass())) {
					Creature cTarget = (Creature) target;
					// See if a runepriest is next to the target. (but the rune priest has to be an enemy of the target, otherwise I'd be granting bonuses to the monsters)
					List<Creature> targetAdjacentEnemies = Encounter.getEncounter().getAdjacentEnemies(cTarget);
					if (targetAdjacentEnemies != null) {
						for (Creature targetAdjacentEnemy : targetAdjacentEnemies) {
							if (DndCharacter.class.isAssignableFrom(targetAdjacentEnemy.getClass())) {
								DndCharacter charTargetAdjacentEnemy = (DndCharacter) targetAdjacentEnemy;
								if (Runepriest.class.isAssignableFrom(charTargetAdjacentEnemy.getDndClass().getClass())) {
									Runepriest runepriest = (Runepriest) charTargetAdjacentEnemy.getDndClass();
									if (runepriest.getRunicState() == RunicState.RUNE_OF_DESTRUCTION) {
										Utils.print(cTarget.getName() + " is standing next to a Runepriest in the Rune of Destruction state.");
										Utils.print(attacker.getName() + " will get a +1 bonus to the attack roll.");
										cAttacker.setTemporaryEffect(1, cAttacker.getCurrentTurn(), DurationType.IMMEDIATE, cAttacker, TemporaryEffectType.ATTACK_ROLL_MODIFIER, TemporaryEffectReason.RUNE_OF_DESTRUCTION);
									}
								}
							}
						}
					}
				}
			}
			
		}
	}
}
