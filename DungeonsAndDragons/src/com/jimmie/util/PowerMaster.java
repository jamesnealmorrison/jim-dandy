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
		
		// Rogue
		powers.add(new DeftStrike());
		powers.add(new PiercingStrike());
		powers.add(new RiposteStrike());
		powers.add(new SlyFlourish());
		powers.add(new DazingStrike());
		powers.add(new KingsCastle());
		powers.add(new PositioningStrike());
		powers.add(new TorturousStrike());
		powers.add(new BlindingBarrage());
		powers.add(new EasyTarget());
		powers.add(new TrickStrike());

		// Warlock
		powers.add(new DireRadiance());
		powers.add(new EldritchBlast());
		powers.add(new Eyebite());
		powers.add(new HellishRebuke());
		powers.add(new DiabolicGrasp());
		powers.add(new DreadfulWord());
		powers.add(new VampiricEmbrace());
		powers.add(new Witchfire());
		powers.add(new ArmorOfAgathys());
		powers.add(new CurseOfTheDarkDream());
		powers.add(new DreadStar());
		powers.add(new FlamesOfPhlegethos());
		
		// Warlord
		powers.add(new CommandersStrike());
		powers.add(new FuriousSmash());
		powers.add(new VipersStrike());
		powers.add(new WolfPackTactics());
		powers.add(new GuardingAttack());
		powers.add(new HammerAndAnvil());
		powers.add(new LeafOnTheWind());
		powers.add(new WarlordsFavor());
		powers.add(new BastionOfDefense());
		powers.add(new LeadTheAttack());
		powers.add(new PinTheFoe());
		powers.add(new WhiteRavenOnslaught());

		// Wizard
		powers.add(new CloudOfDaggers());
		powers.add(new MagicMissile());
		powers.add(new RayOfFrost());
		powers.add(new ScorchingBurst());
		powers.add(new Thunderwave());
		powers.add(new BurningHands());
		powers.add(new ChillStrike());
		powers.add(new ForceOrb());
		powers.add(new IcyTerrain());
		powers.add(new RayOfEnfeeblement());
		powers.add(new AcidArrow());
		powers.add(new FlamingSphere());
		powers.add(new FreezingCloud());
		powers.add(new Sleep());
		
		// Book 2
		// Avenger
		powers.add(new BondOfPursuit());
		powers.add(new BondOfRetribution());
		powers.add(new OverwhelmingStrike());
		powers.add(new RadiantVengeance());
		powers.add(new AngelicAlacrity());
		powers.add(new AvengingEcho());
		powers.add(new SharedMadness());
		powers.add(new WhirlwindCharge());
		powers.add(new AspectOfMight());
		powers.add(new OathOfTheFinalDuel());
		powers.add(new RenewingStrike());
		powers.add(new TempleOfLight());
		
		// Barbarian
		powers.add(new DevastatingStrike());
		powers.add(new HowlingStrike());
		powers.add(new PressingStrike());
		powers.add(new RecuperatingStrike());
		powers.add(new AvalancheStrike());
		powers.add(new Bloodletting());
		powers.add(new GreatCleave());
		powers.add(new VaultTheFallen());
		powers.add(new BloodhuntRage());
		powers.add(new MacetailsRage());
		powers.add(new RageDrakesFrenzy());
		powers.add(new SwiftPantherRage());

		// Bard
		powers.add(new GuidingStrike());
		powers.add(new MisdirectedMark());
		powers.add(new ViciousMockery());
		powers.add(new WarSongStrike());
		powers.add(new Blunder());
		powers.add(new FastFriends());
		powers.add(new InspiringRefrain());
		powers.add(new ShoutOfTriumph());
		powers.add(new EchoesOfTheGuardian());
		powers.add(new SlayersSong());
		powers.add(new StirringShout());
		powers.add(new VerseOfTriumph());
		
		// Druid
		powers.add(new CallOfTheBeast());
		powers.add(new ChillWind());
		powers.add(new FlameSeed());
		powers.add(new GraspingClaws());
		powers.add(new Pounce());
		powers.add(new SavageRend());
		powers.add(new StormSpike());
		powers.add(new ThornWhip());
		powers.add(new CullTheHerd());
		powers.add(new DartingBite());
		powers.add(new FrostFlash());
		powers.add(new TwistingVines());
		powers.add(new FaerieFire());
		powers.add(new FiresOfLife());
		powers.add(new SavageFrenzy());
		powers.add(new WindPrison());
		
		// Invoker
		powers.add(new AvengingLight());
		powers.add(new DivineBolts());
		powers.add(new GraspingShards());
		powers.add(new SunStrike());
		powers.add(new VanguardsLightning());
		powers.add(new AstralTerror());
		powers.add(new BladesOfAstralFire());
		powers.add(new SpearOfTheInquisitor());
		powers.add(new ThunderOfJudgment());
		powers.add(new AngelicEchelon());
		powers.add(new BindingInvocationOfChains());
		powers.add(new PurgingFlame());
		powers.add(new SummonAngelOfFire());
		
		// Shaman
		powers.add(new DefendingStrike());
		powers.add(new HauntingSpirits());
		powers.add(new ProtectingStrike());
		powers.add(new StalkersStrike());
		powers.add(new WatchersStrike());
		powers.add(new WrathOfWinter());
		powers.add(new CallToTheAncestralWarrior());
		powers.add(new CallToTheAncientDefender());
		powers.add(new ThunderBearsWarding());
		powers.add(new TwinPanthers());
		powers.add(new BlessingOfTheSevenWinds());
		powers.add(new CleansingWindOfTheNorth());
		powers.add(new SpiritOfTheHealingFlood());
		powers.add(new WrathOfTheSpiritWorld());
		
		// Sorcerer
		powers.add(new AcidOrb());
		powers.add(new BurningSpray());
		powers.add(new ChaosBolt());
		powers.add(new Dragonfrost());
		powers.add(new StormWalk());
		powers.add(new BedevilingBurst());
		powers.add(new ExplosivePyre());
		powers.add(new Frostbind());
		powers.add(new TempestBreath());
		powers.add(new ThunderSlam());
		powers.add(new ChromaticOrb());
		powers.add(new DazzlingRay());
		powers.add(new DragonfangBolt());
		powers.add(new LightningBreath());
		
		// Warden
		powers.add(new EarthShieldStrike());
		powers.add(new StrengthOfStone());
		powers.add(new ThornStrike());
		powers.add(new WeightOfEarth());
		powers.add(new EarthSpikes());
		powers.add(new HungryEarth());
		powers.add(new ThunderRamAssault());
		powers.add(new WildbloodFrenzy());
		powers.add(new FormOfTheFearsomeRam());
		powers.add(new FormOfTheRelentlessPanther());
		powers.add(new FormOfTheWillowSentinel());
		powers.add(new FormOfWintersHerald());

		return powers;
	}

}
