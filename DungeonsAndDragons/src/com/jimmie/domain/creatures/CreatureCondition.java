package com.jimmie.domain.creatures;

public class CreatureCondition {
	private CreatureConditionType conditionType;
	private boolean saveEnds;
	public CreatureConditionType getConditionType() {
		return conditionType;
	}
	public void setConditionType(CreatureConditionType conditionType) {
		this.conditionType = conditionType;
	}
	public boolean isSaveEnds() {
		return saveEnds;
	}
	public void setSaveEnds(boolean saveEnds) {
		this.saveEnds = saveEnds;
	}
}
