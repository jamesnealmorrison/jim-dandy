package com.jimmie.util;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.powers.*;

public class PowerMaster {

	public static List<Power> getFullListOfPowers() {
		List<Power> powers = new ArrayList<Power>();
		
		// Cleric
		powers.add(new LanceOfFaith());
		powers.add(new PriestsShield());
		powers.add(new RighteousBrand());
		powers.add(new SacredFlame());
		powers.add(new CauseFear());
		powers.add(new DivineGlow());
		powers.add(new HealingStrike());
		powers.add(new WrathfulThunder());
		powers.add(new AvengingFlame());
		powers.add(new BeaconOfHope());
		powers.add(new CascadeOfLight());
		powers.add(new GuardianOfFaith());
		
		// Fighter
		powers.add(new Cleave());
		powers.add(new ReapingStrike());
		powers.add(new SureStrike());
		powers.add(new TideOfIron());
		powers.add(new CoveringAttack());
		powers.add(new PassingAttack());
		powers.add(new SpinningSweep());
		powers.add(new SteelSerpentStrike());
		powers.add(new BruteStrike());
		powers.add(new ComebackStrike());
		powers.add(new VillainsMenace());
		
		// Paladin
		powers.add(new BolsteringStrike());
		powers.add(new EnfeeblingStrike());
		powers.add(new HolyStrike());
		powers.add(new ValiantStrike());
		powers.add(new FearsomeSmite());
		powers.add(new PiercingSmite());
		powers.add(new RadiantSmite());
		powers.add(new ShieldingSmite());
		powers.add(new OnPainOfDeath());
		powers.add(new PaladinsJudgment());
		powers.add(new RadiantDelirium());
		
		// Ranger
		powers.add(new CarefulAttack());
		powers.add(new HitAndRun());
		powers.add(new NimbleStrike());
		powers.add(new TwinStrike());
		powers.add(new DireWolverineStrike());
		powers.add(new EvasiveStrike());
		powers.add(new FoxsCunning());
		powers.add(new TwoFangedStrike());
		powers.add(new HuntersBearTrap());
		powers.add(new JawsOfTheWolf());
		powers.add(new SplitTheTree());
		powers.add(new SuddenStrike());
		
		powers.add(new AngelicAlacrity());
		powers.add(new AspectOfMight());
		powers.add(new BondOfPursuit());
		powers.add(new MisdirectedMark());
		powers.add(new OathOfEnmity());
		powers.add(new RadiantVengeance());
		
		return powers;
	}

}
