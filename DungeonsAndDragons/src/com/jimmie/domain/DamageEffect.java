package com.jimmie.domain;

import com.jimmie.util.Dice;

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
	
	public int getDamage() {
		Dice d = new Dice(diceType);
		int total = 0;
		
		for (int i = 0; i < numberOfDice; i++) {
			total = total + d.basicRoll();
		}
		total = total + modifier;
		return total;
	}
}
