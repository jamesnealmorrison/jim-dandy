package com.jimmie.domain;

public class DamageEffect extends Effect {
	private static final long serialVersionUID = 1L;
	
	private int numberOfDice;
	private DiceType diceType;
	private int modifier;
	
	public int getNumberOfDice() {
		return numberOfDice;
	}
	public void setNumberOfDice(int numberOfDice) {
		this.numberOfDice = numberOfDice;
	}
	public DiceType getDiceType() {
		return diceType;
	}
	public void setDiceType(DiceType diceType) {
		this.diceType = diceType;
	}
	public int getModifier() {
		return modifier;
	}
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
}
