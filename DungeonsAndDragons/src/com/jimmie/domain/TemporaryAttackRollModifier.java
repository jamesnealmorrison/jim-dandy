package com.jimmie.domain;

import com.jimmie.domain.creatures.Creature;

public class TemporaryAttackRollModifier {
	Creature owner;
	DurationType duration;
	int startTurn;
	int attackRollModifier;
	public int getAttackRollModifier() {
		return attackRollModifier;
	}
	public void setAttackRollModifier(int attackRollModifier) {
		this.attackRollModifier = attackRollModifier;
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
}
