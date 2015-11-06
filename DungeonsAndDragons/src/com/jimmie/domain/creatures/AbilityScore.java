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
		double div = (score-10)/2;
		return (int) Math.floor(div);
	}
}
