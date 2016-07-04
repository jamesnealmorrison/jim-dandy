package com.jimmie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.jimmie.domain.DamageType;
import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.KoboldDragonshield;
import com.jimmie.domain.creatures.monsters.KoboldMinion;
import com.jimmie.domain.creatures.monsters.KoboldSlinger;
import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.OnTheRoadKoboldBrigandsEncounter;
import com.jimmie.gui.BattleCardPanel;
import com.jimmie.gui.BattlefieldPanel;
import com.jimmie.gui.ConsolePanel;
import com.jimmie.gui.DungeonGUI;
import com.jimmie.gui.PartyPanel;
import com.jimmie.util.Utils;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class OnTheRoadKoboldBrigandsEncounterConfig {
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
		return "c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Battlefields\\OnTheRoadKoboldBrigands.JPG";
	}
	@Bean
	public Encounter encounter() {
		OnTheRoadKoboldBrigandsEncounter encounter = new OnTheRoadKoboldBrigandsEncounter();
		return encounter;
	}
	@Bean
	public KoboldMinion m1() {
		KoboldMinion m1 = new KoboldMinion();
		m1.setName("Kobold Minion 1");
		m1.setDisplayName("M1");
		m1.setCurrentPosition(new Position(9,18));
		return m1;
	}

	@Bean
	public KoboldMinion m2() {
		KoboldMinion m2 = new KoboldMinion();
		m2.setName("Kobold Minion 2");
		m2.setDisplayName("M2");
		m2.setCurrentPosition(new Position(13,18));
		return m2;
	}

	@Bean
	public KoboldMinion m3() {
		KoboldMinion m3 = new KoboldMinion();
		m3.setName("Kobold Minion 3");
		m3.setDisplayName("M3");
		m3.setCurrentPosition(new Position(14,18));
		return m3;
	}

	@Bean
	public KoboldMinion m4() {
		KoboldMinion m4 = new KoboldMinion();
		m4.setName("Kobold Minion 4");
		m4.setDisplayName("M4");
		m4.setCurrentPosition(new Position(12,17));
		return m4;
	}

	@Bean
	public KoboldMinion m5() {
		KoboldMinion m5 = new KoboldMinion();
		m5.setName("Kobold Minion 5");
		m5.setDisplayName("M5");
		m5.setCurrentPosition(new Position(15,11));
		return m5;
	}

	@Bean
	public KoboldSlinger s() {
		KoboldSlinger s = new KoboldSlinger(2,0,1);
		s.setName("Kobold Slinger");
		s.setDisplayName("S");
		s.setCurrentPosition(new Position(10,17));
		return s;
	}

	@Bean
	public KoboldDragonshield d1() {
		KoboldDragonshield d1 = new KoboldDragonshield(DamageType.FIRE);
		d1.setName("Kobold Dragonshield 1");
		d1.setDisplayName("D1");
		d1.setCurrentPosition(new Position(12,10));
		return d1;
	}

	@Bean
	public KoboldDragonshield d2() {
		KoboldDragonshield d2 = new KoboldDragonshield(DamageType.FIRE);
		d2.setName("Kobold Dragonshield 2");
		d2.setDisplayName("D2");
		d2.setCurrentPosition(new Position(12,9));
		return d2;
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
