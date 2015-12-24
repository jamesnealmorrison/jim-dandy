package com.jimmie.domain;

import com.jimmie.domain.creatures.CreatureConditionType;

public class TemporaryCondition extends TemporaryEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CreatureConditionType conditionType = null;

	public CreatureConditionType getConditionType() {
		return conditionType;
	}

	public void setConditionType(CreatureConditionType conditionType) {
		this.conditionType = conditionType;
	}
}
