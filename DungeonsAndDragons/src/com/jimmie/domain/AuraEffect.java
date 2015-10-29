package com.jimmie.domain;

public class AuraEffect extends Effect {
	private static final long serialVersionUID = 1L;
	
	private TimeConstraint whenEffectOccurs;
	private EffecteeType whoIsEffected;
	private AuraEffectType effectType;
	/* TODO:  LOTS OF OTHER STUFF TO DO HERE! */

	public AuraEffectType getEffectType() {
		return effectType;
	}

	public void setEffectType(AuraEffectType effectType) {
		this.effectType = effectType;
	}

	public EffecteeType getWhoIsEffected() {
		return whoIsEffected;
	}

	public void setWhoIsEffected(EffecteeType whoIsEffected) {
		this.whoIsEffected = whoIsEffected;
	}

	public TimeConstraint getWhenEffectOccurs() {
		return whenEffectOccurs;
	}

	public void setWhenEffectOccurs(TimeConstraint whenEffectOccurs) {
		this.whenEffectOccurs = whenEffectOccurs;
	}
	

}
