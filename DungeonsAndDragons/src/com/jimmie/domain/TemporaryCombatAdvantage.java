package com.jimmie.domain;

public class TemporaryCombatAdvantage extends TemporaryEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CombatAdvantageType typeOfCombatAdvantage;
	public CombatAdvantageType getTypeOfCombatAdvantage() {
		return typeOfCombatAdvantage;
	}
	public void setTypeOfCombatAdvantage(CombatAdvantageType typeOfCombatAdvantage) {
		this.typeOfCombatAdvantage = typeOfCombatAdvantage;
	}
}
