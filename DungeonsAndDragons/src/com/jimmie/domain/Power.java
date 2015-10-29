package com.jimmie.domain;

import java.io.Serializable;

public abstract class Power implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private PowerType powerType;
	private boolean basic;
	private ActionType actionType;
	private RechargeType rechargeType;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public PowerType getPowerType() {
		return powerType;
	}


	public void setPowerType(PowerType powerType) {
		this.powerType = powerType;
	}


	public boolean isBasic() {
		return basic;
	}


	public void setBasic(boolean basic) {
		this.basic = basic;
	}


	public ActionType getActionType() {
		return actionType;
	}


	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}


	public RechargeType getRechargeType() {
		return rechargeType;
	}


	public void setRechargeType(RechargeType rechargeType) {
		this.rechargeType = rechargeType;
	}
	
	public abstract void usePower();

}
