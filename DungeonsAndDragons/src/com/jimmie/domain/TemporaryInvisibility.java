package com.jimmie.domain;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.creatures.Creature;

public class TemporaryInvisibility {
	Creature owner;
	DurationType duration;
	int startTurn;
	List<Creature> targets;
	
	public List<Creature> getTargets() {
		return targets;
	}
	public void setTargets(List<Creature> targets) {
		this.targets = targets;
	}
	public Creature getOwner() {
		return owner;
	}
	public void setOwner(Creature owner) {
		this.owner = owner;
	}
	public DurationType getDuration() {
		return duration;
	}
	public void setDuration(DurationType duration) {
		this.duration = duration;
	}
	public int getStartTurn() {
		return startTurn;
	}
	public void setStartTurn(int startTurn) {
		this.startTurn = startTurn;
	}

	public boolean stillApplies() {
		/* Start of next turn duration is checked in the creature's start method, so if the duration type is START_OF_NEXT_TURN,
		 * it must still apply.  Simply return true. */
		if (duration == DurationType.START_OF_NEXT_TURN) {
			return true;
		}
		if (duration == DurationType.END_OF_NEXT_TURN) {
			if (owner.getCurrentTurn() <= startTurn) {
				/* still applies. */
				return true;
			} else if ((owner.getCurrentTurn() == (startTurn + 1)) &&
					(!owner.isTurnOver())) {
				/* mark still applies. */
				return true;
			} else {
				return false;
			}
		} else if (duration == DurationType.IMMEDIATE){
			return true;
		}
		return false;
	}
	
	public void addTarget(Creature target) {
		if (targets == null) {
			targets = new ArrayList<Creature>();
		}
		/* Add a creature that you are invisible to. */
		targets.add(target);
	}
}
