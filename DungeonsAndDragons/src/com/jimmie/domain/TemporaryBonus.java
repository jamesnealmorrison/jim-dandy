package com.jimmie.domain;

import com.jimmie.domain.creatures.Creature;

public class TemporaryBonus {
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public int getStartTurn() {
		return startTurn;
	}
	public void setStartTurn(int startTurn) {
		this.startTurn = startTurn;
	}
	public DurationType getDuration() {
		return duration;
	}
	public void setDuration(DurationType duration) {
		this.duration = duration;
	}
	public Creature getSource() {
		return source;
	}
	public void setSource(Creature source) {
		this.source = source;
	}
	protected int bonus;
	protected int startTurn;
	protected DurationType duration;
	protected Creature source;
	public boolean stillApplies() {
		if (duration == DurationType.END_OF_NEXT_TURN) {
			if (source.getCurrentTurn() <= startTurn) {
				/* Bonus still applies. */
				return true;
			} else if ((source.getCurrentTurn() == (startTurn + 1)) &&
					(!source.isTurnOver())) {
				/* Bonus still applies. */
				return true;
			} else {
				return false;
			}
		} else if (duration == DurationType.START_OF_NEXT_TURN) {
			/* For now, just return true.  I'm trusting that the method that initializes a new turn will
			 * null out the temporary bonus. 
			 */
			return true;
		}
		return false;
	}
}
