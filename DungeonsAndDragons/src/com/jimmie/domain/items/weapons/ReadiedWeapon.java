package com.jimmie.domain.items.weapons;

import java.io.Serializable;

public class ReadiedWeapon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Weapon weapon;
	private Hand hand;
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
}
