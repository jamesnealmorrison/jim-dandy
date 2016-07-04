package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.creatures.Creature;
import com.jimmie.powers.AttackPower;
import com.jimmie.util.Utils;

public abstract class Goblin extends Monster {
	private static final long serialVersionUID = 1L;

	@Override
	public void miss(Creature misser, AttackPower power) {
		super.miss(misser, power);
		if (power != null) {
			if (Utils.isMeleeAttack(power.getAttackType())) {
				if (isBloodied() && (Irontooth.class.isAssignableFrom(this.getClass()))) {
					Utils.print("Normally Irontooth would be able to shift now due to his Goblin Tactics power, but he is Blood Crazed now.");
				} else {
					Utils.print(getName() + " has Goblin Tactics power and can shift whenever missed by a melee atack.");
					shift(1, true);
				}
			}
		}
	}
}
