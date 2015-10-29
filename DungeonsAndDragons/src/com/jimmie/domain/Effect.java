package com.jimmie.domain;

import java.io.Serializable;
import java.util.List;

public abstract class Effect implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<EffecteeType> affectedTargets;
	private List<EffecteeType> unaffectedTargets;
	public List<EffecteeType> getAffectedTargets() {
		return affectedTargets;
	}
	public void setAffectedTargets(List<EffecteeType> affectedTargets) {
		this.affectedTargets = affectedTargets;
	}
	public List<EffecteeType> getUnaffectedTargets() {
		return unaffectedTargets;
	}
	public void setUnaffectedTargets(List<EffecteeType> unaffectedTargets) {
		this.unaffectedTargets = unaffectedTargets;
	}
}
