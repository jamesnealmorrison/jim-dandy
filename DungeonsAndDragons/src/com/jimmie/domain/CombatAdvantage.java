package com.jimmie.domain;

import com.jimmie.domain.creatures.Creature;

public class CombatAdvantage {
	CombatAdvantageType typeOfCombatAdvantage;
	Creature owner;
	DurationType duration;
	int startTurn;
	public CombatAdvantageType getTypeOfCombatAdvantage() {
		return typeOfCombatAdvantage;
	}
	public void setTypeOfCombatAdvantage(CombatAdvantageType typeOfCombatAdvantage) {
		this.typeOfCombatAdvantage = typeOfCombatAdvantage;
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
}
