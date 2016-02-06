package com.jimmie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.jimmie.domain.DamageType;
import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.KoboldDragonshield;
import com.jimmie.domain.creatures.monsters.KoboldMinion;
import com.jimmie.domain.creatures.monsters.KoboldSkirmisher;
import com.jimmie.domain.creatures.monsters.KoboldSlinger;
import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.KoboldLairOutsideEncounter;
import com.jimmie.gui.BattleCardPanel;
import com.jimmie.gui.BattlefieldPanel;
import com.jimmie.gui.ConsolePanel;
import com.jimmie.gui.DungeonGUI;
import com.jimmie.gui.PartyPanel;
import com.jimmie.util.Utils;

@Configuration
//@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class KoboldLairOutsideEncounterConfig {
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
		return "c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\Battlefields\\KoboldOutsideLair.JPG";
	}
	@Bean
	public Encounter encounter() {
		KoboldLairOutsideEncounter encounter = new KoboldLairOutsideEncounter();
		return encounter;
	}
	@Bean
	public KoboldMinion m1() {
		KoboldMinion m1 = new KoboldMinion();
		m1.setName("Kobold Minion 1");
		m1.setDisplayName("M1");
		m1.setCurrentPosition(new Position(4,4));		
		return m1;
	}
	@Bean
	public KoboldMinion m2() {
		KoboldMinion m2 = new KoboldMinion();
		m2.setName("Kobold Minion 2");
		m2.setDisplayName("M2");
		m2.setCurrentPosition(new Position(5,3));		
		return m2;
	}
	@Bean
	public KoboldMinion m3() {
		KoboldMinion m3 = new KoboldMinion();
		m3.setName("Kobold Minion 3");
		m3.setDisplayName("M3");
		m3.setCurrentPosition(new Position(6,6));
		return m3;
	}
	@Bean
	public KoboldMinion m4() {
		KoboldMinion m4 = new KoboldMinion();
		m4.setName("Kobold Minion 4");
		m4.setDisplayName("M4");
		m4.setCurrentPosition(new Position(7,3));
		return m4;
	}
	@Bean
	public KoboldMinion m5() {
		KoboldMinion m5 = new KoboldMinion();
		m5.setName("Kobold Minion 5");
		m5.setDisplayName("M5");
		m5.setCurrentPosition(new Position(8,7));
		return m5;
	}
	@Bean
	public KoboldMinion m6() {
		KoboldMinion m6 = new KoboldMinion();
		m6.setName("Kobold Minion 6");
		m6.setDisplayName("M6");
		m6.setCurrentPosition(new Position(9,11));
		return m6;
	}
	@Bean
	public KoboldMinion m7() {
		KoboldMinion m7 = new KoboldMinion();
		m7.setName("Kobold Minion 7");
		m7.setDisplayName("M7");
		m7.setCurrentPosition(new Position(11,13));
		return m7;
	}
	@Bean
	public KoboldMinion m8() {
		KoboldMinion m8 = new KoboldMinion();
		m8.setName("Kobold Minion 8");
		m8.setDisplayName("M8");
		m8.setCurrentPosition(new Position(13,7));
		return m8;
	}
	@Bean
	public KoboldMinion m9() {
		KoboldMinion m9 = new KoboldMinion();
		m9.setName("Kobold Minion 9");
		m9.setDisplayName("M9");
		m9.setCurrentPosition(new Position(13,9));
		return m9;
	}
	@Bean
	public KoboldMinion m10() {
		KoboldMinion m10 = new KoboldMinion();
		m10.setName("Kobold Minion 10");
		m10.setDisplayName("M10");
		m10.setCurrentPosition(new Position(14,13));
		return m10;
	}
	@Bean
	public KoboldSkirmisher k() {
		KoboldSkirmisher k = new KoboldSkirmisher();
		k.setName("Kobold Skirmisher");
		k.setDisplayName("K");
		k.setCurrentPosition(new Position(15,19));
		return k;
	}
	@Bean
	public KoboldDragonshield d() {
		KoboldDragonshield d = new KoboldDragonshield(DamageType.ACID);
		d.setName("Kobold Dragonshield");
		d.setDisplayName("D");
		d.setCurrentPosition(new Position(7,11));
		return d;
	}
	@Bean
	public KoboldSlinger s() {
		KoboldSlinger s = new KoboldSlinger(0,1,2);
		s.setName("Kobold Slinger");
		s.setDisplayName("S");
		s.setCurrentPosition(new Position(14,15));
		return s;
	}
	
	@Bean
	public DndCharacter percian() {
		return Utils.loadCharacter("Percian");
	}

	@Bean
	public DndCharacter gamal() {
		return Utils.loadCharacter("Gamal");
	}

	@Bean
	public DndCharacter keothi() {
		return Utils.loadCharacter("Keothi");
	}
	
	@Bean
	public DndCharacter travok() {
		return Utils.loadCharacter("Travok");
	}

	@Bean
	public DndCharacter hazel() {
		return Utils.loadCharacter("Hazel");
	}
}
