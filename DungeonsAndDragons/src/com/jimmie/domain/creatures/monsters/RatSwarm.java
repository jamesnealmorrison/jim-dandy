package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.AttackType;
import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.RatSwarmSwarmOfTeeth;

public class RatSwarm extends Rat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RatSwarm() {
		setSize(Size.MEDIUM);
		setInitiative(6);
		setMaxHitPoints(36);
		setCurrentHitPoints(36);
		setArmorClass(15);
		setFortitude(12);
		setReflex(14);
		setWill(11);
		setBaseSpeed(4);  // TODO: Climb 2
		setStrength(12);
		setConstitution(12);
		setDexterity(17);
		setIntelligence(2);
		setWisdom(10);
		setCharisma(9);
		addPower(new RatSwarmSwarmOfTeeth());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\RatSwarm.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\RatSwarmBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\RatSwarmBattleCard.jpg");
		addAttackTypeVulnerability(AttackType.AREA_BURST, 5);
		addAttackTypeVulnerability(AttackType.AREA_WALL, 5);
		addAttackTypeVulnerability(AttackType.CLOSE_BLAST, 5);
		addAttackTypeVulnerability(AttackType.CLOSE_BURST, 5);
		addPercentAttackTypeResistance(AttackType.MELEE_NUMBER, .50);
		addPercentAttackTypeResistance(AttackType.MELEE_OR_RANGED_WEAPON, .50);
		addPercentAttackTypeResistance(AttackType.MELEE_SPIRIT_NUMBER, .50);
		addPercentAttackTypeResistance(AttackType.MELEE_TOUCH, .50);
		addPercentAttackTypeResistance(AttackType.MELEE_WEAPON, .50);
		addPercentAttackTypeResistance(AttackType.RANGED_NUMBER, .50);
		addPercentAttackTypeResistance(AttackType.RANGED_SIGHT, .50);
		addPercentAttackTypeResistance(AttackType.RANGED_WEAPON, .50);
		addPercentAttackTypeResistance(AttackType.MELEE_OR_RANGED_WEAPON, .50);
	}

	public int getStrengthModifier() {
		return 2;
	}

	public int getConstitutionModifier() {
		return 2;
	}

	public int getDexterityModifier() {
		return 4;
	}

	public int getIntelligenceModifier() {
		return -3;
	}
	
	public int getWisdomModifier() {
		return 1;
	}
	
	public int getCharismaModifier() {
		return 0;
	}
}
