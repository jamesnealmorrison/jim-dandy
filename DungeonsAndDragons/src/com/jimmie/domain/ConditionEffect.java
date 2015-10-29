package com.jimmie.domain;

import com.jimmie.domain.creatures.CreatureConditionType;

public class ConditionEffect extends Effect{
	private static final long serialVersionUID = 1L;
	CreatureConditionType conditionToApply;

	public CreatureConditionType getConditionToApply() {
		return conditionToApply;
	}

	public void setConditionToApply(CreatureConditionType conditionToApply) {
		this.conditionToApply = conditionToApply;
	}
}
