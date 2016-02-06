package com.jimmie.util.aspects;

import java.util.List;

import com.jimmie.domain.DurationType;
import com.jimmie.domain.Mark;
import com.jimmie.domain.MarkType;
import com.jimmie.domain.MovementType;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.monsters.KoboldDragonshield;
import com.jimmie.encounters.Encounter;
import com.jimmie.powers.Power;
import com.jimmie.util.Utils;

public aspect MovementAspect {
	public pointcut moveCreature(String direction, MovementType movementType) : execution(* com.jimmie.domain.creatures.*.moveCreature(..))
	&& args(direction, movementType);

	void around(String direction, MovementType movementType) : moveCreature(direction, movementType) {
		Object o = thisJoinPoint.getThis();
		Creature creature = null;
		if (Creature.class.isAssignableFrom(o.getClass())) {
			creature = (Creature) o;

			// See if the creature is immobilized
			if (creature.isImmobilized()) {
				Utils.print(creature.getName() + " is immobilized.  They can not move.");
				return;
			}
			
			// Page 290 of the Player Handbook says pushing, pulling and sliding also does not invoke opportunity attacks.
			if ((movementType == MovementType.SHIFTING) || (movementType == MovementType.SLIDE) || (movementType == MovementType.PUSH) ||
					(movementType == MovementType.PULL)) {
				if (creature.isMarked()) {

					for (Mark mark : creature.getMarks()) {
						if (mark.getMarkType() == MarkType.COMBAT_CHALLENGE) {
							Utils.print("UH OH!  " + mark.getMarker().getName() + " gets to make a basic melee attack against me because they marked me with Combat Challenge.");
							Utils.print("Make sure to pick me (" + creature.getName() + ") when it asks who to attack.");
							/* Should be able to cast the marker to a character. */
							if (DndCharacter.class.isInstance(mark.getMarker())) {
								Power basicMeleeAttack = mark.getMarker().getBasicMeleeAttack();
								basicMeleeAttack.process(mark.getMarker());
							}
						}
						
					}
				}
			}

			
			/* Before moving, see if it triggers an opportunity attack. */
			if (movementType != MovementType.SHIFTING) {
				List<Creature> adjacentEnemies = Encounter.getEncounter().getAdjacentEnemies(creature);
				if (adjacentEnemies != null) {
					for (Creature adjacentEnemy : adjacentEnemies) {
						/* I may be invisible to them, though.  Check. */
						if (creature.isInvisibleTo(adjacentEnemy)) {
							Utils.print("This would have provoked an opportunity attack from " + adjacentEnemy.getName() + " but I am invisible to them now. ");
						} else {
							Utils.print(adjacentEnemy.getName() + " gets to perform an opportunity attack on " + creature.getName() + " because " + creature.getName() + " left a square adjacent to " + adjacentEnemy.getName() + " without shifting.");
							// Check for Halfling Nimble Reaction
							if (com.jimmie.domain.creatures.monsters.Halfling.class.isAssignableFrom(creature.getClass())) {
								Utils.print(creature.getName() + " gets a +2 AC bonus against opportunity attacks.");
								creature.setTemporaryEffect(2, creature.getCurrentTurn(), DurationType.IMMEDIATE, creature, TemporaryEffectType.AC_MOD, TemporaryEffectReason.NIMBLE_REACTION);
							}
							adjacentEnemy.performOpportunityAttack(creature);
						}
					}
				}
			}

			/* Did this trigger a Dragonshield Tactics by shifting away from a dragonshield? */
			/* Start by getting a list of adjacent kobold dragonshields from before the move. */
			List<Creature> adjacentDragonshieldsBeforeMove = Encounter.getEncounter().getSpecificTypeOfAdjacentEnemies(creature, KoboldDragonshield.class);

			/* Also need a list of creatures that are adjacent before the move. */
			List<Creature> adjacentCreaturesBeforeMove = Encounter.getEncounter().getAllAdjacentCreatures(creature);

			proceed(direction, movementType);

			/* Get a list of adjacentCreatures after the move. */
			List<Creature> adjacentCreaturesAfterMove = Encounter.getEncounter().getAllAdjacentCreatures(creature);

			/* Also need a list of adjacent dragonshields after the move. */
			List<Creature> adjacentDragonshieldsAfterMove = Encounter.getEncounter().getSpecificTypeOfAdjacentEnemies(creature, KoboldDragonshield.class);

			/* If the move was a shift, see which dragonshields are no longer adjacent.  In other words,
			 * did they shift away from any dragonshields?
			 */
			if (movementType.equals(MovementType.SHIFTING)) {
				if (adjacentDragonshieldsBeforeMove != null) {
					for (Creature adjacentDragonshieldBeforeMove : adjacentDragonshieldsBeforeMove) {
						if ((adjacentCreaturesAfterMove == null) || (!adjacentCreaturesAfterMove.contains(adjacentDragonshieldBeforeMove))) {
							Utils.print(creature.getName() + " moved away from " + adjacentDragonshieldBeforeMove.getName() + ", so they may be able to use Dragonshield Tactics to shift.");
							if (!((KoboldDragonshield) adjacentDragonshieldBeforeMove).isUsedDragonshieldTactics()) {
								((KoboldDragonshield) adjacentDragonshieldBeforeMove).useDragonshieldTactics();
							}
						}
					}
				}
			}

			/* Now check for dragonshields who weren't adjacent before the move, but are now (i.e. did they
			 * move next to a dragonshield?
			 */
			if (adjacentDragonshieldsAfterMove != null) {
				for (Creature adjacentDragonshieldAfterMove : adjacentDragonshieldsAfterMove) {
					if ((adjacentCreaturesBeforeMove == null) || (!adjacentCreaturesBeforeMove.contains(adjacentDragonshieldAfterMove))) {
						Utils.print(creature.getName() + " moved next to " + adjacentDragonshieldAfterMove.getName() + ", so they may be able to use Dragonshield Tactics to shift.");
						if (!((KoboldDragonshield) adjacentDragonshieldAfterMove).isUsedDragonshieldTactics()) {
							((KoboldDragonshield) adjacentDragonshieldAfterMove).useDragonshieldTactics();
						}
					}
				}
			}
		}
	
	}
}
