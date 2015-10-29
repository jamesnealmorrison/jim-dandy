package com.jimmie.domain;

import java.util.List;

import com.jimmie.domain.creatures.Creature;

public class Attack {
	private List<Creature> targets;
	/* The purpose of this is for monsters.  The code
	 * should check if there is a basicAttackModifier
	 * or if we need to look up the weapon, etc.
	 */
	private int basicAttackModifier;
	private DefenseType defenseType;
	public DefenseType getDefenseType() {
		return defenseType;
	}
	public void setDefenseType(DefenseType defenseType) {
		this.defenseType = defenseType;
	}
	public List<Creature> getTargets() {
		return targets;
	}
	public void setTargets(List<Creature> targets) {
		this.targets = targets;
	}
	public int getBasicAttackModifier() {
		return basicAttackModifier;
	}
	public void setBasicAttackModifier(int basicAttackModifier) {
		this.basicAttackModifier = basicAttackModifier;
	}
}
