package com.jimmie.domain;

import com.jimmie.domain.creatures.CreatureConditionType;

public class TemporaryCondition extends TemporaryEffect {
	CreatureConditionType conditionType = null;

	public CreatureConditionType getConditionType() {
		return conditionType;
	}

	public void setConditionType(CreatureConditionType conditionType) {
		this.conditionType = conditionType;
	}
}
