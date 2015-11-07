package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.PowerId;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.MinorAction;

public abstract class Kobold extends Monster {

	private static final long serialVersionUID = 1L;

	protected Kobold() {
		speed = 6;
	}

	@MinorAction(powerId = PowerId.SHIFTY)
	@AtWillPower
	public void shifty(Encounter encounter) {
		shift(1, true, encounter);
	}

}
