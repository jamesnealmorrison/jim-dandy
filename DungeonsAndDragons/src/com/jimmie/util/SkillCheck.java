package com.jimmie.util;

import com.jimmie.domain.SkillType;

public class SkillCheck {
	public SkillType getSkillType() {
		return skillType;
	}
	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}
	public int getDifficultyClass() {
		return difficultyClass;
	}
	public void setDifficultyClass(int difficultyClass) {
		this.difficultyClass = difficultyClass;
	}
	SkillType skillType;
	int difficultyClass;
}
