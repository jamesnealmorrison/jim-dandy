package com.jimmie.util.aspects;

import java.util.List;

import com.jimmie.domain.Mark;
import com.jimmie.domain.MarkType;
import com.jimmie.domain.MovementType;
import com.jimmie.domain.creatures.Character;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.monsters.KoboldDragonshield;
import com.jimmie.encounters.Encounter;
import com.jimmie.powers.Power;
import com.jimmie.util.Utils;

public aspect MovementAspect {
	public pointcut moveCreature(String direction, Encounter encounter, MovementType movementType) : execution(* com.jimmie.domain.creatures.*.moveCreature(..))
	&& args(direction, encounter, movementType);

	void around(String direction, Encounter encounter, MovementType movementType) : moveCreature(direction, encounter, movementType) {
		Utils.print("In advice: direction = " + direction + ". encounter = " + encounter + ". movementType = " + movementType);
		Object o = thisJoinPoint.getThis();
		Creature creature = null;
		if (Creature.class.isAssignableFrom(o.getClass())) {
			creature = (Creature) o;

			if (movementType == MovementType.SHIFTING) {
				Mark mark = creature.getMark();
				if (creature.isMarked() && (mark.getTypeOfMark() == MarkType.COMBAT_CHALLENGE)) {

					Utils.print("UH OH!  " + mark.getMarker().getName() + " gets to make a basic melee attack against me because they marked me with Combat Challenge.");
					Utils.print("Make sure to pick me (" + creature.getName() + ") when it asks who to attack.");
					/* Should be able to cast the marker to a character. */
					if (Character.class.isInstance(mark.getMarker())) {
						Power basicMeleeAttack = mark.getMarker().getBasicMeleeAttack();
						basicMeleeAttack.process(encounter, mark.getMarker());
					}
				}
			}

			
			/* Before moving, see if it triggers an opportunity attack. */
			if (movementType != MovementType.SHIFTING) {
				List<Creature> adjacentEnemies = encounter.getAdjacentEnemies(creature);
				if (adjacentEnemies != null) {
					for (Creature adjacentEnemy : adjacentEnemies) {
						/* I may be invisible to them, though.  Check. */
						if (creature.isInvisibleTo(adjacentEnemy)) {
							Utils.print("This would have provoked an opportunity attack from " + adjacentEnemy.getName() + " but I am invisible to them now. ");
						} else {
							Utils.print(adjacentEnemy.getName() + " gets to perform an opportunity attack on " + creature.getName() + " because " + creature.getName() + " left a square adjacent to " + adjacentEnemy.getName() + " without shifting.");
							adjacentEnemy.performOpportunityAttack(creature, encounter);
						}
					}
				}
			}

			/* Did this trigger a Dragonshield Tactics by shifting away from a dragonshield? */
			/* Start by getting a list of adjacent kobold dragonshields from before the move. */
			List<Creature> adjacentDragonshieldsBeforeMove = encounter.getSpecificTypeOfAdjacentEnemies(creature, KoboldDragonshield.class);

			/* Also need a list of creatures that are adjacent before the move. */
			List<Creature> adjacentCreaturesBeforeMove = encounter.getAllAdjacentCreatures(creature);

			proceed(direction, encounter, movementType);
			Utils.print("Should have moved");

			/* Get a list of adjacentCreatures after the move. */
			List<Creature> adjacentCreaturesAfterMove = encounter.getAllAdjacentCreatures(creature);

			/* Also need a list of adjacent dragonshields after the move. */
			List<Creature> adjacentDragonshieldsAfterMove = encounter.getSpecificTypeOfAdjacentEnemies(creature, KoboldDragonshield.class);

			/* If the move was a shift, see which dragonshields are no longer adjacent.  In other words,
			 * did they shift away from any dragonshields?
			 */
			if (movementType.equals(MovementType.SHIFTING)) {
				if (adjacentDragonshieldsBeforeMove != null) {
					for (Creature adjacentDragonshieldBeforeMove : adjacentDragonshieldsBeforeMove) {
						if ((adjacentCreaturesAfterMove == null) || (!adjacentCreaturesAfterMove.contains(adjacentDragonshieldBeforeMove))) {
							Utils.print(creature.getName() + " moved away from " + adjacentDragonshieldBeforeMove.getName() + ", so they may be able to use Dragonshield Tactics to shift.");
							if (!((KoboldDragonshield) adjacentDragonshieldBeforeMove).isUsedDragonshieldTactics()) {
								((KoboldDragonshield) adjacentDragonshieldBeforeMove).useDragonshieldTactics(encounter);
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
							((KoboldDragonshield) adjacentDragonshieldAfterMove).useDragonshieldTactics(encounter);
						}
					}
				}
			}
		}
	
	}
}
