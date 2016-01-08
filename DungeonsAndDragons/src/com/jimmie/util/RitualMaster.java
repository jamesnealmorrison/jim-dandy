package com.jimmie.util;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.rituals.AnimalMessenger;
import com.jimmie.rituals.ArcaneLock;
import com.jimmie.rituals.BrewPotion;
import com.jimmie.rituals.CommuneWithNature;
import com.jimmie.rituals.ComprehendLanguage;
import com.jimmie.rituals.ConsultMysticSages;
import com.jimmie.rituals.ConsultOracle;
import com.jimmie.rituals.CreateCampsite;
import com.jimmie.rituals.CreateHolyWater;
import com.jimmie.rituals.CureDisease;
import com.jimmie.rituals.DetectObject;
import com.jimmie.rituals.DetectSecretDoors;
import com.jimmie.rituals.DiscernLies;
import com.jimmie.rituals.DisenchantMagicItem;
import com.jimmie.rituals.DowsingRod;
import com.jimmie.rituals.DrawmigsInstantSummons;
import com.jimmie.rituals.EnchantMagicItem;
import com.jimmie.rituals.EndureElements;
import com.jimmie.rituals.EyeOfAlarm;
import com.jimmie.rituals.EyeOfWarning;
import com.jimmie.rituals.Forbiddance;
import com.jimmie.rituals.GentleRepose;
import com.jimmie.rituals.GlibLimerick;
import com.jimmie.rituals.HallucinatoryCreature;
import com.jimmie.rituals.HallucinatoryItem;
import com.jimmie.rituals.HandOfFate;
import com.jimmie.rituals.Knock;
import com.jimmie.rituals.LeomundsSecretChest;
import com.jimmie.rituals.LinkedPortal;
import com.jimmie.rituals.LoremastersBargain;
import com.jimmie.rituals.MagicCircle;
import com.jimmie.rituals.MagicMouth;
import com.jimmie.rituals.MakeWhole;
import com.jimmie.rituals.ObserveCreature;
import com.jimmie.rituals.Passwall;
import com.jimmie.rituals.PhantomSteed;
import com.jimmie.rituals.PlanarPortal;
import com.jimmie.rituals.PortendWeather;
import com.jimmie.rituals.PurifyWater;
import com.jimmie.rituals.RaiseDead;
import com.jimmie.rituals.RemoveAffliction;
import com.jimmie.rituals.Ritual;
import com.jimmie.rituals.SecretPage;
import com.jimmie.rituals.Sending;
import com.jimmie.rituals.ShadowWalk;
import com.jimmie.rituals.Silence;
import com.jimmie.rituals.SpeakWithDead;
import com.jimmie.rituals.TensersFloatingDisk;
import com.jimmie.rituals.TravelersCamouflage;
import com.jimmie.rituals.TravelersChant;
import com.jimmie.rituals.UnseenServant;

public class RitualMaster {
	public static List<Ritual> getFullListOfRituals() {
		List<Ritual> rituals = new ArrayList<Ritual>();

		// Player's Handbook
		rituals.add(new AnimalMessenger());
		rituals.add(new ArcaneLock());
		rituals.add(new BrewPotion());
		rituals.add(new CommuneWithNature());
		rituals.add(new ComprehendLanguage());
		rituals.add(new ConsultMysticSages());
		rituals.add(new ConsultOracle());
		rituals.add(new CureDisease());
		rituals.add(new DetectObject());
		rituals.add(new DetectSecretDoors());
		rituals.add(new DiscernLies());
		rituals.add(new DisenchantMagicItem());
		rituals.add(new DrawmigsInstantSummons());
		rituals.add(new EnchantMagicItem());
		rituals.add(new EndureElements());
		rituals.add(new EyeOfAlarm());
		rituals.add(new EyeOfWarning());
		rituals.add(new Forbiddance());
		rituals.add(new GentleRepose());
		rituals.add(new HallucinatoryCreature());
		rituals.add(new HallucinatoryItem());
		rituals.add(new HandOfFate());
		rituals.add(new Knock());
		rituals.add(new LeomundsSecretChest());
		rituals.add(new LinkedPortal());
		rituals.add(new LoremastersBargain());
		rituals.add(new MagicCircle());
		rituals.add(new MagicMouth());
		rituals.add(new MakeWhole());
		rituals.add(new ObserveCreature());
		rituals.add(new Passwall());
		rituals.add(new PhantomSteed());
		rituals.add(new PlanarPortal());
		rituals.add(new RaiseDead());
		rituals.add(new RemoveAffliction());
		rituals.add(new SecretPage());
		rituals.add(new Sending());
		rituals.add(new ShadowWalk());
		rituals.add(new Silence());
		rituals.add(new SpeakWithDead());
		rituals.add(new TensersFloatingDisk());
/* TODO Finish non-first level rituals
 		rituals.add(new TravelersFeast());
		rituals.add(new TruePortal());
		rituals.add(new ViewLocation());
		rituals.add(new ViewObject());
		rituals.add(new VoiceOfFate());
		rituals.add(new WaterBreathing());
		rituals.add(new WaterWalk());
		rituals.add(new WizardsSight()); */
		// Player's Handbook 2
/*		rituals.add(new AffectNormalFire());
		rituals.add(new AnimalFriendship());
		rituals.add(new AriaOfRevelation());
		rituals.add(new Bloom());
		rituals.add(new CallWildernessGuide());
		rituals.add(new ChorusOfTruth());
		rituals.add(new ControlWeather());
*/		
		rituals.add(new CreateCampsite());
//		rituals.add(new FoolsSpeech());
		rituals.add(new GlibLimerick());
/*		rituals.add(new Ironwood());
		rituals.add(new Lullaby());
		rituals.add(new Pyrotechnics());
		rituals.add(new ReversePortal());
		rituals.add(new Snare());
		rituals.add(new SongOfRestfulness());
		rituals.add(new SongOfSustenance());
		rituals.add(new SpeakWithNature());
*/		
		rituals.add(new TravelersChant());
/*		rituals.add(new TreeShape());
		rituals.add(new TreeStride());
		rituals.add(new TuneOfMerriment());
		rituals.add(new WyvernWatch());
*/		
		// No rituals in Player's Handbook 3
		
		// Arcane Power
/*		rituals.add(new AnthemOfUnity());
		rituals.add(new ArcaneBarrier());
		rituals.add(new BeastGrowth());
		rituals.add(new CallOfFriendship());
		rituals.add(new DetectTreasure());
		rituals.add(new Farsight());
		rituals.add(new FoolsGold());
		rituals.add(new GuardsAndWards());
		rituals.add(new HistoryRevealed());
		rituals.add(new Imprisonment());
		rituals.add(new LowerWater());
		rituals.add(new MagicMap());
		rituals.add(new ObjectReading());
		rituals.add(new PreserveFlame());
*/		
		rituals.add(new PurifyWater());
/*		rituals.add(new Shrink());
		rituals.add(new TelepathicBond());
*/		
		rituals.add(new UnseenServant());
//		rituals.add(new WhispersOfTheEdifice());
		
		// Divine Power
//		rituals.add(new Adjure());
		rituals.add(new CreateHolyWater());
/*		rituals.add(new EaseSpirit());
		rituals.add(new HallowedTemple());
		rituals.add(new IronVigil());
		rituals.add(new MarkOfJustice());
		rituals.add(new Succor());
		rituals.add(new ThiefsLament());
*/		
		
		// None in Martial Power 1 or 2
		
		// Primal Power
		rituals.add(new DowsingRod());
//		rituals.add(new EaglesFlight());
		rituals.add(new PortendWeather());
/*		rituals.add(new PrimalGrove());
		rituals.add(new PrimalPrison());
		rituals.add(new Starshine());
*/		
		rituals.add(new TravelersCamouflage());
//		rituals.add(new WatersGift());

		// None in Psionic Power
		
		return rituals;
	}
}
