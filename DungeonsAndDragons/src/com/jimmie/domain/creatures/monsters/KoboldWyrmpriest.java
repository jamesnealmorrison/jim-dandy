package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.DamageType;
import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.KoboldWyrmpriestDragonBreath;
import com.jimmie.powers.KoboldWyrmpriestEnergyOrb;
import com.jimmie.powers.KoboldWyrmpriestInciteFaith;
import com.jimmie.powers.KoboldWyrmpriestSpear;

public class KoboldWyrmpriest extends Kobold {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public KoboldWyrmpriest(DamageType damageType) {
		setSize(Size.SMALL);
		setInitiative(4);
		setMaxHitPoints(36);
		setCurrentHitPoints(36);
		setArmorClass(17);
		setFortitude(13);
		setReflex(15);
		setWill(15);
		setBaseSpeed(6);
		setStrength(9);
		setConstitution(12);
		setDexterity(16);
		setIntelligence(9);
		setWisdom(17);
		setCharisma(12);
		addPower(new KoboldWyrmpriestSpear());
		addPower(new KoboldWyrmpriestEnergyOrb(damageType));
		addPower(new KoboldWyrmpriestInciteFaith());
		addPower(new KoboldWyrmpriestDragonBreath(DamageType.ACID));
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldWyrmpriest.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldWyrmpriestBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldWyrmpriestBattleCard.jpg");
	}

	public int getStrengthModifier() {
		return 0;
	}

	public int getConstitutionModifier() {
		return 2;
	}

	public int getDexterityModifier() {
		return 4;
	}

	public int getIntelligenceModifier() {
		return 0;
	}
	
	public int getWisdomModifier() {
		return 4;
	}
	
	public int getCharismaModifier() {
		return 2;
	}
}
