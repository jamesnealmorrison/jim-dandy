package com.jimmie.domain;

import java.util.List;


import com.jimmie.powers.Power;

public interface TurnTaker {
	int getInitiative();
	int getInitiativeRoll();
	void setInitiativeRoll(int initiativeRoll);
	void initializeForEncounter();
	void initializeForNewDay();
	String getName();
	void startOfTurn();
	void endOfTurn();
	boolean canTakeMoveAction();
	boolean canTakeMinorAction();
	boolean canTakeStandardAction();
	void useMoveAction();
	Position getCurrentPosition();
	List<Power> getPowers();
}
