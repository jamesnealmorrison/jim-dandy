package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.KoboldMinionJavelin;
import com.jimmie.powers.KoboldMinionSpear;
import com.jimmie.powers.KoboldShifty;

public class KoboldMinion extends Kobold {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int javelins;
	
	public KoboldMinion() {
		setSize(Size.SMALL);
		setInitiative(3);
		setMaxHitPoints(1);
		setCurrentHitPoints(1);
		setArmorClass(15);
		setFortitude(11);
		setReflex(13);
		setWill(11);
		setBaseSpeed(6);
		setStrength(8);
		setConstitution(12);
		setDexterity(16);
		setIntelligence(9);
		setWisdom(12);
		setCharisma(10);
		addPower(new KoboldMinionJavelin());
		addPower(new KoboldMinionSpear());
		addPower(new KoboldShifty());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldMinion.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldMinionBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldMinionBattleCard.jpg");
	}

	public int getStrengthModifier() {
		return -1;
	}

	public int getConstitutionModifier() {
		return 1;
	}

	public int getDexterityModifier() {
		return 3;
	}

	public int getIntelligenceModifier() {
		return -1;
	}
	
	public int getWisdomModifier() {
		return 1;
	}
	
	public int getCharismaModifier() {
		return 0;
	}

	public void inializeForEncounter(int javelins) {
		this.javelins = javelins;		
	}

	public int getJavelins() {
		return javelins;
	}

	public void setJavelins(int javelins) {
		this.javelins = javelins;
	}
}
