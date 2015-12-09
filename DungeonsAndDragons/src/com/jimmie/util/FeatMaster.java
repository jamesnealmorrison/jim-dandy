package com.jimmie.util;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.feats.*;

public class FeatMaster {
	public static List<Feat> getFullListOfFeats() {
		
		// TODO: The books may have feats I missed, like the first book has some pages for paragon, epic and multiclass feats.  :-(
		List<Feat> feats = new ArrayList<Feat>();
		
		// Book 1 Heroic
		feats.add(new ActionSurge());
		feats.add(new AgileHunter());
		feats.add(new Alertness());
		feats.add(new ArmorOfBahamut());
		feats.add(new ArmorProficiencyChainmail());
		feats.add(new ArmorProficiencyHide());
		feats.add(new ArmorProficiencyLeather());
		feats.add(new ArmorProficiencyPlate());
		feats.add(new ArmorProficiencyScale());
		feats.add(new AstralFire());
		feats.add(new AvandrasRescue());
		feats.add(new Backstabber());
		feats.add(new BladeOpportunist());
		feats.add(new BurningBlizzard());
		feats.add(new CombatReflexes());
		feats.add(new CorellonsGrace());
		feats.add(new DarkFury());
		feats.add(new DefensiveMobility());
		feats.add(new DistractingShield());
		feats.add(new DodgeGiants());
		feats.add(new DragonbornFrenzy());
		feats.add(new DragonbornSenses());
		feats.add(new Durable());
		feats.add(new DwarvenWeaponTraining());
		feats.add(new EladrinSoldier());
		feats.add(new ElvenPrecision());
		feats.add(new EnlargedDragonBreath());
		feats.add(new EscapeArtist());
		feats.add(new ExpandedSpellbook());
		feats.add(new FarShot());
		feats.add(new FarThrow());
		feats.add(new FastRunner());
		feats.add(new FerociousRebuke());
		feats.add(new GroupInsight());
		feats.add(new HalflingAgility());
		feats.add(new HarmonyOfErathis());
		feats.add(new HealingHands());
		feats.add(new HellfireBlood());
		feats.add(new HumanPerseverance());
		feats.add(new ImprovedDarkOnesBlessing());
		feats.add(new ImprovedFateOfTheVoid());
		feats.add(new ImprovedInitiative());
		feats.add(new ImprovedMistyStep());
		feats.add(new InspiredRecovery());
		feats.add(new IounsPoise());
		feats.add(new JackOfAllTrades());
		feats.add(new KordsFavor());
		feats.add(new LethalHunter());
		feats.add(new LightStep());
		feats.add(new Linguist());
		feats.add(new LongJumper());
		feats.add(new LostInTheCrowd());
		feats.add(new MelorasTide());
		feats.add(new MoradinsResolve());
		feats.add(new MountedCombat());
		feats.add(new NimbleBlade());
		feats.add(new PelorsRadiance());
		feats.add(new PotentChallenge());
		feats.add(new PowerAttack());
		feats.add(new PowerfulCharge());
		feats.add(new PreciseHunter());
		feats.add(new PressTheAdvantage());
		feats.add(new QuickDraw());
		feats.add(new RagingStorm());
		feats.add(new RavenQueensBlessing());
		feats.add(new RitualCaster());
		feats.add(new SehaninesReversal());
		feats.add(new ShieldProficiencyHeavy());
		feats.add(new ShieldProficiencyLight());
		feats.add(new ShieldPush());
		feats.add(new SkillFocus());
		feats.add(new SkillTraining());
		feats.add(new SureClimber());
		feats.add(new SurpriseKnockdown());
		feats.add(new TacticalAssault());
		feats.add(new Toughness());
		feats.add(new TwoWeaponDefense());
		feats.add(new TwoWeaponFighting());
		feats.add(new WeaponFocus());
		feats.add(new WeaponProficiency());
		feats.add(new Wintertouched());
		
		// Book 1 Paragon Tier Feats
		
		// Book 1 Epic Tier Feats
		
		
		// Book 2 Heroic Tier Feats
		feats.add(new AngerUnleashed());
		feats.add(new AuspiciousLineage());
		feats.add(new BlurringClaws());
		feats.add(new CombatMedic());
		feats.add(new CoordinatedExplosion());
		feats.add(new DistantAdvantage());
		feats.add(new EchoesOfThunder());
		feats.add(new ExpertRitualist());
		feats.add(new FeyTrickster());
		feats.add(new GoliathGreatweaponProwess());
		feats.add(new GorebruteCharge());
		feats.add(new GroupStealth());
		feats.add(new ImplementExpertise());
		feats.add(new ImprovedBullRush());
		feats.add(new ImprovedGrab());
		feats.add(new MarkingsOfTheBlessed());
		feats.add(new MarkingsOfTheVictor());
		feats.add(new MeleeTraining());
		feats.add(new OncomingStorm());
		feats.add(new PotentRebirth());
		feats.add(new RadiantPower());
		feats.add(new RestfulHealing());
		feats.add(new SavageAssault());
		feats.add(new ShadowSkulk());
		feats.add(new SpeedLoader());
		feats.add(new SurgingFlame());
		feats.add(new ThirstForBattle());
		feats.add(new TimelyRespite());
		feats.add(new TwoWeaponThreat());
		feats.add(new WeaponExpertise());
		feats.add(new WildSenses());
		feats.add(new GuaranteedRetribution());
		feats.add(new ImprovedArmorOfFaith());
		feats.add(new InvigoratingPursuit());
		feats.add(new DeadlyRage());
		feats.add(new ImprovedRagebloodVigor());
		feats.add(new ImprovedRoarOfTriumph());
		feats.add(new RisingFury());
		feats.add(new AdvantageOfCunning());
		feats.add(new BardicKnowledge());
		feats.add(new ImprovedMajesticWord());
		feats.add(new StrengthOfValor());
		feats.add(new EnragedBoarForm());
		feats.add(new FerociousTigerForm());
		feats.add(new PrimalFury());
		feats.add(new PrimalInstinct());
		feats.add(new InsightfulPreservation());
		feats.add(new InvokerDefense());
		feats.add(new ResonatingCovenant());
		feats.add(new ScouringWrath());
		feats.add(new ProtectorSpiritAdept());
		feats.add(new SharedHealingSpirit());
		feats.add(new SpiritSpeaker());
		feats.add(new StalkerSpiritAdept());
		feats.add(new ArcaneSpellfury());
		feats.add(new DisciplinedWildSoul());
		feats.add(new ImprovedDragonSoul());
		feats.add(new SorcerousBladeChanneling());
		feats.add(new CrushingEarthstrength());
		feats.add(new RevitalizingFontOfLife());
		feats.add(new SuddenRoots());
		feats.add(new WildbloodCunning());
		// missing some from page 188
		
		// Book 2 Paragon Tier Feats
		
		// Book 2 Epic Tier Feats
		
		
		// Book 3 Heroic Tier Feats
		feats.add(new AlhahnsMindfulRelocation());
		feats.add(new BattleHardened());
		feats.add(new BattleCasterDefense());
		feats.add(new BatteringShield());
		feats.add(new BeguilingEnchantment());
		feats.add(new BloodiedFerocity());
		feats.add(new BrutalFerocity());
		feats.add(new BurdenOfGuardianship());
		feats.add(new BurdenOfRejuvenation());
		feats.add(new CascadingRush());
		feats.add(new CleanseTheMadness());
		feats.add(new CombatIntuition());
		feats.add(new DakshaisBodyMindUnion());
		feats.add(new DeadlyDraw());
		feats.add(new DirectedBullRush());
		feats.add(new FocusedMind());
		feats.add(new GoringShove());
		feats.add(new GroundingShot());
		feats.add(new HaftedDefense());
		feats.add(new HammeringIron());
		feats.add(new HeadsmansChop());
		feats.add(new HybridTalent());
		feats.add(new ImpendingVictory());
		feats.add(new ImprovedAspectOfNature());
		feats.add(new IronResolveOfZerthadlun());
		feats.add(new LiberatingShardSwarm());
		feats.add(new LowCrawl());
		feats.add(new LuckyStart());
		feats.add(new MiryathsFirstStrike());
		feats.add(new NimbleRunner());
		feats.add(new OpportunisticShove());
		feats.add(new PolearmFlanker());
		feats.add(new PowerThrow());
		feats.add(new PrimeBurst());
		feats.add(new PsychicFocus());
		feats.add(new RapidAssault());
		feats.add(new SecretOfReawakening());
		feats.add(new ShiftingDefense());
		feats.add(new SkillPower());
		feats.add(new SpringStep());
		feats.add(new SpringingCharge());
		feats.add(new SteadyFeet());
		feats.add(new StrikeAndShove());
		feats.add(new SuperiorImplementTraining());
		feats.add(new TeamworkDefense());
		feats.add(new UnarmoredAgility());
		feats.add(new UnfailingVigor());
		feats.add(new VersatileExpertise());
		feats.add(new ViciousAdvantage());
		feats.add(new WardingDefense());
		feats.add(new WardingShardSwarm());
		feats.add(new WatchfulRedoubt());
		feats.add(new ZuwothsEnlightenedStep());
		feats.add(new BolsteringMantle());
		feats.add(new ClarifiedInstincts());
		feats.add(new ElatedEmotions());
		feats.add(new HearteningSurge());
		feats.add(new MantleOfReadiness());
		feats.add(new DeceptiveMind());
		feats.add(new ImprovedSpeedOfThought());
		feats.add(new LureOfIron());
		feats.add(new PunishingSpike());
		feats.add(new PursuingStep());
		feats.add(new CrashingTempestStyle());
		feats.add(new ImprovedMonkUnarmedStrike());
		feats.add(new PointedStepStyle());
		feats.add(new ControllingAdvantage());
		feats.add(new DisciplineAdept());
		feats.add(new ExchangePower());
		feats.add(new OrbitingObject());
		feats.add(new PreciseMind());
		feats.add(new RuneOfEloquence());
		feats.add(new RuneOfHope());
		feats.add(new RuneOfVengeance());
		feats.add(new RuneOfZeal());
		feats.add(new BloodiedElusion());
		feats.add(new ImprovedInevitableShot());
		feats.add(new InescapableShot());
		feats.add(new SpiritbondDefense());
		feats.add(new StrengthenedBond());
		
		return feats;
	}

}
