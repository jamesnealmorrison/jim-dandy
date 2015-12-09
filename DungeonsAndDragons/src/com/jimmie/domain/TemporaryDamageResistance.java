package com.jimmie.domain;

public class TemporaryDamageResistance extends TemporaryBonus {
	private DamageType damageType;

	public DamageType getDamageType() {
		return damageType;
	}

	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
}
