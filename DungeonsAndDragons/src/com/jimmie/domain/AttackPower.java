package com.jimmie.domain;

public class AttackPower extends Power {
	private static final long serialVersionUID = 1L;
	/* TODO: REMINDER, JIM!!!! The plan is that a power itself
	 * has a reach (mainly for monsters).  In addition, the
	 * weapons will also have a reach.  So, when I am coding 
	 * this stuff, I should check both the power reach, and the
	 * weapon reach (take the maximum).
	 */
	private int reach;
	private Attack primaryAttack;
	private Attack secondaryAttack;
	public int getReach() {
		return reach;
	}
	public void setReach(int reach) {
		this.reach = reach;
	}
	public Attack getPrimaryAttack() {
		return primaryAttack;
	}
	public void setPrimaryAttack(Attack primaryAttack) {
		this.primaryAttack = primaryAttack;
	}
	public Attack getSecondaryAttack() {
		return secondaryAttack;
	}
	public void setSecondaryAttack(Attack secondaryAttack) {
		this.secondaryAttack = secondaryAttack;
	}
	
	@Override
	public void usePower() {
		/* Do primary attack */
	}
}
