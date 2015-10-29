package com.jimmie.domain;

import com.jimmie.domain.creatures.Creature;

public class Mark {
	private MarkType typeOfMark;
	private Creature marker;
	private DurationType duration;
	private int startTurn;
	private Creature misdirectedMarker;

	public MarkType getTypeOfMark() {
		return typeOfMark;
	}


	public void setTypeOfMark(MarkType typeOfMark) {
		this.typeOfMark = typeOfMark;
	}


	public Creature getMarker() {
		
		/* If its misdirected mark, use it instead. */
		if (misdirectedMarker != null) {
			return misdirectedMarker;
		} else {
			return marker;
		}
	}


	public void setMarker(Creature marker) {
		this.marker = marker;
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

	public boolean stillApplies() {
		if (duration == DurationType.END_OF_NEXT_TURN) {
			if (marker.getCurrentTurn() <= startTurn) {
				/* mark still applies. */
				return true;
			} else if ((marker.getCurrentTurn() == (startTurn + 1)) &&
					(!marker.isTurnOver())) {
				/* mark still applies. */
				return true;
			} else {
				return false;
			}
		}
		return false;
	}


	public void setMisdirectedMarker(Creature misdirectedMarker) {
		this.misdirectedMarker = misdirectedMarker;
	}
}
