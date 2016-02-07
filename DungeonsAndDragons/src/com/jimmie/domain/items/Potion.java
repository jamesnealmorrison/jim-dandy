package com.jimmie.domain.items;

import com.jimmie.domain.creatures.Creature;

public abstract class Potion extends Gear {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract void quaff(Creature user);
}
