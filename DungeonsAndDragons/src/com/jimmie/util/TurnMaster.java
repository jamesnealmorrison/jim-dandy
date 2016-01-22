package com.jimmie.util;

import java.util.LinkedList;

import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.Monster;
import com.jimmie.encounters.Encounter;

public class TurnMaster {
	private static LinkedList<TurnTaker> participantList = new LinkedList<TurnTaker>();
	
	private TurnMaster() {
		
	}
	
	public static void addParticipant(TurnTaker newParticipant) {
		/* If this is the first participant, just add it. */
		if (participantList.isEmpty()) {
			participantList.add(newParticipant);
		} else {
			boolean added = false;
			/* Else, go through the list and order it based on its initiative. */
			for (TurnTaker participant : participantList) {
				if (newParticipant.getInitiativeRoll() > participant.getInitiativeRoll()) {
					participantList.add(participantList.indexOf(participant), newParticipant);
					added = true;
					break;
				}
			}
			/* If it wasn't added, then it was the smallest initiative.  Add it to the end. */
			if (!added) {
				participantList.add(newParticipant);
			}
		}
	}
	
	public static TurnTaker getNextParticipant() {
		TurnTaker nextParticipant = null;
		if (!participantList.isEmpty()) {
			boolean activeParticipantFound = false;
			while (!activeParticipantFound) {
				// Pop the next element off the list. */
				nextParticipant = participantList.pop();

				// See if this type of creature is active yet.
				if (Monster.class.isAssignableFrom(nextParticipant.getClass())) {
					if (!Encounter.areMonstersActive()) {
						// Skip this monster, but you have to put them back on the list.
						participantList.add(nextParticipant);
						continue;
					} else {
						activeParticipantFound = true;
					}
				}
				if (DndCharacter.class.isAssignableFrom(nextParticipant.getClass())) {
					if (!Encounter.areCharactersActive()) {
						// Skip this character, but you have to put them back on the list.
						participantList.add(nextParticipant);
						continue;
					} else {
						activeParticipantFound = true;
					}
				}
			}
			
			// Got to add it back to the end also. */
			participantList.add(nextParticipant);
		}
		return nextParticipant;
	}

	public static void removeParticipant(TurnTaker participant) {
		participantList.remove(participant);
	}
}
