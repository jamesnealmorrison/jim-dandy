package com.jimmie.domain;

public class TemporaryOngoingDamage extends TemporaryEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DamageType damageType;

	public DamageType getDamageType() {
		return damageType;
	}

	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}

}
