package com.jimmie.domain;

public class TemporaryCombatAdvantage extends TemporaryEffect {
	CombatAdvantageType typeOfCombatAdvantage;
	public CombatAdvantageType getTypeOfCombatAdvantage() {
		return typeOfCombatAdvantage;
	}
	public void setTypeOfCombatAdvantage(CombatAdvantageType typeOfCombatAdvantage) {
		this.typeOfCombatAdvantage = typeOfCombatAdvantage;
	}
}
