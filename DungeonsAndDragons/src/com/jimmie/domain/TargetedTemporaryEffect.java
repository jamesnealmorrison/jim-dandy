package com.jimmie.domain;

import com.jimmie.domain.creatures.Creature;

public class TargetedTemporaryEffect extends TemporaryEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Creature target;

	public Creature getTarget() {
		return target;
	}

	public void setTarget(Creature target) {
		this.target = target;
	}
}
