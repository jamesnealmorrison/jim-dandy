package com.jimmie.domain;

import java.io.Serializable;

import com.jimmie.domain.creatures.Creature;

public class TemporaryEffect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int modifier;
	protected int startTurn;
	protected DurationType duration;
	protected Creature source;
	protected TemporaryEffectType effectType;
	protected TemporaryEffectReason reason;

	public TemporaryEffectReason getReason() {
		return reason;
	}

	public void setReason(TemporaryEffectReason reason) {
		this.reason = reason;
	}

	public boolean stillApplies() {
		if ((duration == DurationType.END_OF_NEXT_TURN) || (duration == DurationType.IMMEDIATE_BY_END_OF_NEXT_TURN)) {
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
			if (source.getCurrentTurn() <= startTurn) {
				/* Bonus still applies. */
				return true;
			} else {
				return false;
			}
		} else if (duration == DurationType.IMMEDIATE) {
			return true;
		} else if (duration == DurationType.SPECIAL) {
			// Special marks will be removed elsewhere.  That's why they're special.
			return true;
		} else if (duration == DurationType.SAVE_ENDS) {
			return true;
		} else if (duration == DurationType.END_OF_NEXT_EXTENDED_REST) {
			return true;
		}
		return false;
	}

	public int getModifier() {
		return modifier;
	}
	public void setModifier(int modifier) {
		this.modifier = modifier;
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

	public TemporaryEffectType getEffectType() {
		return effectType;
	}

	public void setEffectType(TemporaryEffectType effectType) {
		this.effectType = effectType;
	}

	public boolean shouldBeRemoved() {
		if (duration == DurationType.END_OF_NEXT_TURN) {
			if (source.getCurrentTurn() <= startTurn) {
				/* Bonus still applies. It should NOT be removed.*/
				return false;
			} else if ((source.getCurrentTurn() == (startTurn + 1)) &&
					(!source.isTurnOver())) {
				/* Bonus still applies. It should NOT be removed. */
				return false;
			} else {
				return true;
			}
		} else if (duration == DurationType.START_OF_NEXT_TURN) {
			if (source.getCurrentTurn() <= startTurn) {
				/* Bonus still applies. It should NOT be removed. */
				return false;
			} else {
				return true;
			}
		} else if ((duration == DurationType.IMMEDIATE) || (duration == DurationType.IMMEDIATE_BY_END_OF_NEXT_TURN)) {
			// The effect was immediate.  The assumption is that this method is only being called because the effect was just used.
			// So, since it was just used, it should be removed now.
			return true;
		} else if (duration == DurationType.SPECIAL) {
			// Special marks will be removed elsewhere.  That's why they're special.
			return false;
		} else if (duration == DurationType.SAVE_ENDS) {
			return false;
		} else if (duration == DurationType.END_OF_NEXT_EXTENDED_REST) {
			return false;
		}
		return false;
	}
}
