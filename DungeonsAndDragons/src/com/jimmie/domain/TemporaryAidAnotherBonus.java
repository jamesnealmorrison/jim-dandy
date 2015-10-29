package com.jimmie.domain;

public class TemporaryAidAnotherBonus extends TemporaryBonus {
	public static final int ATTACK = 1;
	public static final int DEFENSE = 2;
	AttackTarget target;
	int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public AttackTarget getTarget() {
		return target;
	}

	public void setTarget(AttackTarget target) {
		this.target = target;
	}
}
