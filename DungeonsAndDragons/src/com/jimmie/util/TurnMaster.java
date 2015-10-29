package com.jimmie.util;

import java.util.LinkedList;

import com.jimmie.domain.TurnTaker;

public class TurnMaster {
	private static LinkedList<TurnTaker> participantList = new LinkedList<TurnTaker>();
	
	private TurnMaster() {
		
	}
	
	public static void addParticipant(TurnTaker newParticipant) {
		newParticipant.initializeForEncounter();
		
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
			/* Pop the next element off the list. */
			nextParticipant = participantList.pop();
			/* Got to add it back to the end also. */
			participantList.add(nextParticipant);
		}
		return nextParticipant;
	}

	public static void removeParticipant(TurnTaker participant) {
		participantList.remove(participant);
	}
}
