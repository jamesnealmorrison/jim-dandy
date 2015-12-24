package com.jimmie.domain;

import java.io.Serializable;

import com.jimmie.domain.creatures.Creature;

public class Zone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Position zoneOrigin;
	private ZoneShape zoneShape;
	private int size;
	private Creature owner;
	private DurationType duration;
	private int startTurn;
	public Position getZoneOrigin() {
		return zoneOrigin;
	}
	public void setZoneOrigin(Position zoneOrigin) {
		this.zoneOrigin = zoneOrigin;
	}
	public ZoneShape getZoneShape() {
		return zoneShape;
	}
	public void setZoneShape(ZoneShape zoneShape) {
		this.zoneShape = zoneShape;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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
	public ZoneType getZoneType() {
		return zoneType;
	}
	public void setZoneType(ZoneType zoneType) {
		this.zoneType = zoneType;
	}
	private ZoneType zoneType;

	public boolean stillApplies() {
		if ((duration == DurationType.END_OF_NEXT_TURN) || (duration == DurationType.IMMEDIATE_BY_END_OF_NEXT_TURN)) {
			if (owner.getCurrentTurn() <= startTurn) {
				/* Bonus still applies. */
				return true;
			} else if ((owner.getCurrentTurn() == (startTurn + 1)) &&
					(!owner.isTurnOver())) {
				/* Bonus still applies. */
				return true;
			} else {
				return false;
			}
		} else if (duration == DurationType.START_OF_NEXT_TURN) {
			if (owner.getCurrentTurn() <= startTurn) {
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
}
