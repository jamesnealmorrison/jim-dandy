package com.jimmie.domain;

import com.jimmie.encounters.Encounter;

public interface TurnTaker {
	int getInitiative();
	int getInitiativeRoll();
	void setInitiativeRoll(int initiativeRoll);
	void initializeForEncounter();
	void initializeForNewDay();
	String getName();
	void startOfTurn();
	void endOfTurn(Encounter encounter);
	boolean canTakeMoveAction();
	boolean canTakeMinorAction();
	boolean canTakeStandardAction();
	void useMinorAction(Encounter encounter);
	void useMoveAction(Encounter encounter);
	void useStandardAction(Encounter encounter);
	Position getCurrentPosition();
	void useFreeAction(Encounter encounter);
}
