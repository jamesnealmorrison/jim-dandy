package com.jimmie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.Character;
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
import com.jimmie.util.CreatureAspect;
import com.jimmie.util.Utils;

@Configuration
//@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class KoboldLairOutsideEncounterConfig {
	@Bean
	public CreatureAspect creatureAspect() {
		return new CreatureAspect();
	}
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
	public Character elfAvenger() {
		return Utils.loadCharacter("Arannis");
	}
	@Bean
	public Character kellen() {
		return Utils.loadCharacter("Arannis");
	}
	@Bean
	public Character glock() {
		return Utils.loadCharacter("Arannis");
	}
	@Bean
	public Character halfOrcFighter() {
		return Utils.loadCharacter("Arannis");
	}
	@Bean
	public Character zanros() {
		return Utils.loadCharacter("Arannis");
	}
}
