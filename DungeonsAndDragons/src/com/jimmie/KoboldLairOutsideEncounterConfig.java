package com.jimmie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.monsters.KoboldDragonshield;
import com.jimmie.domain.creatures.monsters.KoboldMinion;
import com.jimmie.domain.creatures.monsters.KoboldSkirmisher;
import com.jimmie.domain.creatures.monsters.KoboldSlinger;
import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.KoboldLairOutsideEncounter;
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
	public Encounter encounter() {
		KoboldLairOutsideEncounter encounter = new KoboldLairOutsideEncounter();
		return encounter;
	}
	@Bean
	public KoboldMinion m1() {
		KoboldMinion m1 = new KoboldMinion();
		return m1;
	}
	@Bean
	public KoboldMinion m2() {
		return new KoboldMinion();
	}
	@Bean
	public KoboldMinion m3() {
		return new KoboldMinion();
	}
	@Bean
	public KoboldMinion m4() {
		return new KoboldMinion();
	}
	@Bean
	public KoboldMinion m5() {
		return new KoboldMinion();
	}
	@Bean
	public KoboldMinion m6() {
		return new KoboldMinion();
	}
	@Bean
	public KoboldMinion m7() {
		return new KoboldMinion();
	}
	@Bean
	public KoboldMinion m8() {
		return new KoboldMinion();
	}
	@Bean
	public KoboldMinion m9() {
		return new KoboldMinion();
	}
	@Bean
	public KoboldMinion m10() {
		return new KoboldMinion();
	}
	@Bean
	public KoboldSkirmisher k() {
		return new KoboldSkirmisher();
	}
	@Bean
	public KoboldDragonshield d() {
		return new KoboldDragonshield();
	}
	@Bean
	public KoboldSlinger s() {
		return new KoboldSlinger();
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
