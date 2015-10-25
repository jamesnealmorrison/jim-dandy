package com.jimmie.domain.creatures;

public class AbilityScore {
	int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getModifier() {
		return Math.floorDiv((score-10), 2);
	}
}
