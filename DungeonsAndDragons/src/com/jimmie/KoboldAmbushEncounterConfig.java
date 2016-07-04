package com.jimmie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.jimmie.domain.DamageType;
import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.KoboldDragonshield;
import com.jimmie.domain.creatures.monsters.KoboldSkirmisher;
import com.jimmie.domain.creatures.monsters.KoboldWyrmpriest;
import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.KoboldAmbushEncounter;
import com.jimmie.gui.BattleCardPanel;
import com.jimmie.gui.BattlefieldPanel;
import com.jimmie.gui.ConsolePanel;
import com.jimmie.gui.DungeonGUI;
import com.jimmie.gui.PartyPanel;
import com.jimmie.util.Utils;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class KoboldAmbushEncounterConfig {
	@Bean
	public DungeonGUI dungeonGUI() {
		return new DungeonGUI();
	}
	@Bean
	public PartyPanel partyPanel() {
		return new PartyPanel();
	}
	@Bean
	public ConsolePanel consolePanel() {
		return new ConsolePanel();
	}
	@Bean
	public BattlefieldPanel battlefieldPanel() {
		return new BattlefieldPanel();
	}
	@Bean
	public BattleCardPanel battleCardPanel() {
		return new BattleCardPanel();
	}
	@Bean
	public String battlefieldImageFilePath() {
		return "c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Battlefields\\KoboldAmbush.JPG";
	}
	@Bean
	public Encounter encounter() {
		KoboldAmbushEncounter encounter = new KoboldAmbushEncounter();
		return encounter;
	}
	@Bean
	public KoboldDragonshield d1() {
		KoboldDragonshield d1 = new KoboldDragonshield(DamageType.ACID);
		d1.setName("Kobold Dragonshield 1");
		d1.setDisplayName("D1");
		d1.setCurrentPosition(new Position(15,16));
		return d1;
	}

	@Bean
	public KoboldDragonshield d2() {
		KoboldDragonshield d2 = new KoboldDragonshield(DamageType.ACID);
		d2.setName("Kobold Dragonshield 2");
		d2.setDisplayName("D2");
		d2.setCurrentPosition(new Position(18,10));
		return d2;
	}

	@Bean
	public KoboldDragonshield d3() {
		KoboldDragonshield d3 = new KoboldDragonshield(DamageType.ACID);
		d3.setName("Kobold Dragonshield 3");
		d3.setDisplayName("D3");
		d3.setCurrentPosition(new Position(19,10));
		return d3;
	}

	@Bean
	public KoboldSkirmisher k() {
		KoboldSkirmisher k = new KoboldSkirmisher();
		k.setName("Kobold Skirmisher");
		k.setDisplayName("K");
		k.setCurrentPosition(new Position(14,18));
		return k;
	}

	@Bean
	public KoboldWyrmpriest w() {
		KoboldWyrmpriest w = new KoboldWyrmpriest(DamageType.ACID);
		w.setName("Kobold Wyrmpriest");
		w.setDisplayName("W");
		w.setCurrentPosition(new Position(15,19));
		return w;
	}

	@Bean
	public DndCharacter percian() {
		DndCharacter percian = Utils.loadCharacter("Percian");
		percian.setCurrentPosition(new Position(-1,-1));
		return percian; 
	}

	@Bean
	public DndCharacter gamal() {
		DndCharacter gamal = Utils.loadCharacter("Gamal");
		gamal.setCurrentPosition(new Position(-1,-1));
		return gamal; 
	}

	@Bean
	public DndCharacter keothi() {
		DndCharacter keothi = Utils.loadCharacter("Keothi");
		keothi.setCurrentPosition(new Position(-1,-1));
		return keothi; 
	}
	
	@Bean
	public DndCharacter travok() {
		DndCharacter travok = Utils.loadCharacter("Travok");
		travok.setCurrentPosition(new Position(-1,-1));
		return travok; 
	}

	@Bean
	public DndCharacter hazel() {
		DndCharacter hazel = Utils.loadCharacter("Hazel");
		hazel.setCurrentPosition(new Position(-1,-1));
		return hazel; 
	}

}
