package com.jimmie.util;

import java.util.Random;

import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;

public class Dice {
	private DiceType type;
	private int sides;
	public static final int USER_ENTERED = 1;
	public static final int AUTOMATED = 2;
	private Random random;
	private static int rollType = AUTOMATED;
	
	public static int getRollType() {
		return rollType;
	}

	public static void setRollType(int r) {
		rollType = r;
	}

	public Dice(DiceType type) {
		this.type = type;
		
		random = new Random(System.currentTimeMillis());
		
		switch (this.type) {
			case FOUR_SIDED :
				sides = 4;
				break;
			case SIX_SIDED :
				sides = 6;
				break;
			case EIGHT_SIDED :
				sides = 8;
				break;
			case TEN_SIDED :
				sides = 10;
				break;
			case TWELVE_SIDED :
				sides = 12;
				break;
			case TWENTY_SIDED :
				sides = 20;
				break;
		}
	}
	
	public int roll(DiceRollType diceRollType) {
		if (Dice.rollType != USER_ENTERED) {
			/* The computer is so fast, it doesn't roll new numbers sometimes.  So take the 6th different number. */
			int index = 0;
			int currentRoll = 0;
			int lastRoll = 0;
		
			for (int i = 0; i < 10000; i++) {
				for (int j = 0; j < 10000; j++);
			}
			while (index < 6) {
				currentRoll = random.nextInt(sides)+1;
				if (currentRoll != lastRoll) {
					index++;
					lastRoll = currentRoll;
					random = new Random(System.currentTimeMillis());
				}
			}
			
			Utils.print("You rolled a " + currentRoll);
			return currentRoll;
		} else {
			Utils.print("You roll (1 to " + sides + "): " );
			return Utils.getValidIntInputInRange(1, sides);
		}
	}
}
