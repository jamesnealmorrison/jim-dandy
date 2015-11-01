package com.jimmie.domain;

import java.io.Serializable;

public class Skill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SkillType skillType;
	public SkillType getSkillType() {
		return skillType;
	}

	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}

	public boolean isTrained() {
		return trained;
	}

	public void setTrained(boolean trained) {
		this.trained = trained;
	}

	public boolean hasArmorPenalty() {
		return hasArmorPenalty;
	}

	public int getMisc() {
		return misc;
	}

	public void setMisc(int misc) {
		this.misc = misc;
	}

	private boolean trained = false;
	private boolean hasArmorPenalty = false;
	private int misc = 0;
	private AbilityType abilityType;
	
	public Skill(SkillType skillType) {
		this.skillType = skillType;
		setArmorPenalty(skillType);
		setAbility(skillType);
	}

	public AbilityType getAbilityType() {
		return abilityType;
	}

	private void setAbility(SkillType skillType2) {
		if (skillType == SkillType.ACROBATICS) {
			abilityType = AbilityType.DEXTERITY;
		} else if (skillType == SkillType.ARCANA) {
			abilityType = AbilityType.INTELLIGENCE;
		} else if (skillType == SkillType.ATHLETICS) {
			abilityType = AbilityType.STRENGTH;
		} else if (skillType == SkillType.BLUFF) {
			abilityType = AbilityType.CHARISMA;
		} else if (skillType == SkillType.DIPLOMACY) {
			abilityType = AbilityType.CHARISMA;
		} else if (skillType == SkillType.DUNGEONEERING) {
			abilityType = AbilityType.WISDOM;
		} else if (skillType == SkillType.ENDURANCE) {
			abilityType = AbilityType.CONSTITUTION;
		} else if (skillType == SkillType.HEAL) {
			abilityType = AbilityType.WISDOM;
		} else if (skillType == SkillType.HISTORY) {
			abilityType = AbilityType.INTELLIGENCE;
		} else if (skillType == SkillType.INSIGHT) {
			abilityType = AbilityType.WISDOM;
		} else if (skillType == SkillType.INTIMIDATE) {
			abilityType = AbilityType.CHARISMA;
		} else if (skillType == SkillType.NATURE) {
			abilityType = AbilityType.WISDOM;
		} else if (skillType == SkillType.PERCEPTION) {
			abilityType = AbilityType.WISDOM;
		} else if (skillType == SkillType.RELIGION) {
			abilityType = AbilityType.INTELLIGENCE;
		} else if (skillType == SkillType.STEALTH) {
			abilityType = AbilityType.DEXTERITY;
		} else if (skillType == SkillType.STREETWISE) {
			abilityType = AbilityType.CHARISMA;
		} else if (skillType == SkillType.THIEVERY) {
			abilityType = AbilityType.DEXTERITY;
		}	
	}

	private void setArmorPenalty(SkillType skillType) {
		if (skillType == SkillType.ACROBATICS) {
			hasArmorPenalty = true;
		} else if (skillType == SkillType.ARCANA) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.ATHLETICS) {
			hasArmorPenalty = true;
		} else if (skillType == SkillType.BLUFF) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.DIPLOMACY) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.DUNGEONEERING) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.ENDURANCE) {
			hasArmorPenalty = true;
		} else if (skillType == SkillType.HEAL) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.HISTORY) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.INSIGHT) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.INTIMIDATE) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.NATURE) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.PERCEPTION) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.RELIGION) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.STEALTH) {
			hasArmorPenalty = true;
		} else if (skillType == SkillType.STREETWISE) {
			hasArmorPenalty = false;
		} else if (skillType == SkillType.THIEVERY) {
			hasArmorPenalty = true;
		}	
	}
}
