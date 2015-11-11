package com.jimmie.util;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.items.Arrows;
import com.jimmie.domain.items.Backpack;
import com.jimmie.domain.items.Bedroll;
import com.jimmie.domain.items.BeltPouch;
import com.jimmie.domain.items.Candle;
import com.jimmie.domain.items.Chain;
import com.jimmie.domain.items.Chest;
import com.jimmie.domain.items.ClimbersKit;
import com.jimmie.domain.items.CrossbowBolts;
import com.jimmie.domain.items.EverburningTorch;
import com.jimmie.domain.items.FineClothing;
import com.jimmie.domain.items.Flask;
import com.jimmie.domain.items.FlintAndSteel;
import com.jimmie.domain.items.Gear;
import com.jimmie.domain.items.GrapplingHook;
import com.jimmie.domain.items.Hammer;
import com.jimmie.domain.items.HempenRope;
import com.jimmie.domain.items.HolySymbol;
import com.jimmie.domain.items.Journeybread;
import com.jimmie.domain.items.Lantern;
import com.jimmie.domain.items.Orb;
import com.jimmie.domain.items.Pitons;
import com.jimmie.domain.items.RitualBook;
import com.jimmie.domain.items.Rod;
import com.jimmie.domain.items.SilkRope;
import com.jimmie.domain.items.SlingBullets;
import com.jimmie.domain.items.Spellbook;
import com.jimmie.domain.items.Staff;
import com.jimmie.domain.items.StandardAdventurersKit;
import com.jimmie.domain.items.Sunrods;
import com.jimmie.domain.items.Tent;
import com.jimmie.domain.items.ThievesTools;
import com.jimmie.domain.items.Torch;
import com.jimmie.domain.items.TrailRations;
import com.jimmie.domain.items.Wand;
import com.jimmie.domain.items.Waterskin;
import com.jimmie.domain.items.armor.Armor;
import com.jimmie.domain.items.armor.ChainMail;
import com.jimmie.domain.items.armor.ClothArmor;
import com.jimmie.domain.items.armor.HeavyShield;
import com.jimmie.domain.items.armor.HideArmor;
import com.jimmie.domain.items.armor.LeatherArmor;
import com.jimmie.domain.items.armor.LightShield;
import com.jimmie.domain.items.armor.PlateArmor;
import com.jimmie.domain.items.armor.ScaleArmor;
import com.jimmie.domain.items.armor.Shield;
import com.jimmie.domain.items.weapons.BastardSword;
import com.jimmie.domain.items.weapons.Battleaxe;
import com.jimmie.domain.items.weapons.Club;
import com.jimmie.domain.items.weapons.Crossbow;
import com.jimmie.domain.items.weapons.Dagger;
import com.jimmie.domain.items.weapons.Falchion;
import com.jimmie.domain.items.weapons.Flail;
import com.jimmie.domain.items.weapons.Glaive;
import com.jimmie.domain.items.weapons.Greataxe;
import com.jimmie.domain.items.weapons.Greatclub;
import com.jimmie.domain.items.weapons.Greatsword;
import com.jimmie.domain.items.weapons.Halberd;
import com.jimmie.domain.items.weapons.HandCrossbow;
import com.jimmie.domain.items.weapons.Handaxe;
import com.jimmie.domain.items.weapons.HeavyFlail;
import com.jimmie.domain.items.weapons.Javelin;
import com.jimmie.domain.items.weapons.Katar;
import com.jimmie.domain.items.weapons.Longbow;
import com.jimmie.domain.items.weapons.Longspear;
import com.jimmie.domain.items.weapons.Mace;
import com.jimmie.domain.items.weapons.Maul;
import com.jimmie.domain.items.weapons.Morningstar;
import com.jimmie.domain.items.weapons.Quarterstaff;
import com.jimmie.domain.items.weapons.Rapier;
import com.jimmie.domain.items.weapons.Scimitar;
import com.jimmie.domain.items.weapons.Scythe;
import com.jimmie.domain.items.weapons.ShortSword;
import com.jimmie.domain.items.weapons.Shortbow;
import com.jimmie.domain.items.weapons.Shuriken;
import com.jimmie.domain.items.weapons.Sickle;
import com.jimmie.domain.items.weapons.Sling;
import com.jimmie.domain.items.weapons.Spear;
import com.jimmie.domain.items.weapons.SpikedChain;
import com.jimmie.domain.items.weapons.ThrowingHammer;
import com.jimmie.domain.items.weapons.WarPick;
import com.jimmie.domain.items.weapons.Warhammer;
import com.jimmie.domain.items.weapons.Weapon;
import com.jimmie.domain.items.weapons.Longsword;

public class BasicStore extends Store {

	@Override
	public List<Armor> getArmor() {
		List<Armor> armor = new ArrayList<Armor>();
		armor.add(new ClothArmor());
		armor.add(new LeatherArmor());
		armor.add(new HideArmor());
		armor.add(new ChainMail());
		armor.add(new ScaleArmor());
		armor.add(new PlateArmor());
		return armor;
	}

	@Override
	public List<Shield> getShields() {
		List<Shield> shields = new ArrayList<Shield>();
		shields.add(new LightShield());
		shields.add(new HeavyShield());
		return shields;
	}

	@Override
	public List<Weapon> getWeapons() {
		List<Weapon> weapons = new ArrayList<Weapon>();
		weapons.add(new Club());
		weapons.add(new Dagger());
		weapons.add(new Javelin());
		weapons.add(new Mace());
		weapons.add(new Sickle());
		weapons.add(new Spear());
		weapons.add(new Greatclub());
		weapons.add(new Morningstar());
		weapons.add(new Quarterstaff());
		weapons.add(new Scythe());
		weapons.add(new Battleaxe());
		weapons.add(new Flail());
		weapons.add(new Handaxe());
		weapons.add(new Longsword());
		weapons.add(new Scimitar());
		weapons.add(new ShortSword());
		weapons.add(new ThrowingHammer());
		weapons.add(new Warhammer());
		weapons.add(new WarPick());
		weapons.add(new Falchion());
		weapons.add(new Glaive());
		weapons.add(new Greataxe());
		weapons.add(new Greatsword());
		weapons.add(new Halberd());
		weapons.add(new HeavyFlail());
		weapons.add(new Longspear());
		weapons.add(new Maul());
		weapons.add(new BastardSword());
		weapons.add(new Katar());
		weapons.add(new Rapier());
		weapons.add(new SpikedChain());
		weapons.add(new HandCrossbow());
		weapons.add(new Sling());
		weapons.add(new Crossbow());
		weapons.add(new Longbow());
		weapons.add(new Shortbow());
		weapons.add(new Shuriken());
		
		return weapons;
	}

	@Override
	public List<Gear> getGear() {
		List<Gear> gear = new ArrayList<Gear>();
		gear.add(new StandardAdventurersKit());
		gear.add(new Backpack());
		gear.add(new Bedroll());
		gear.add(new FlintAndSteel());
		gear.add(new BeltPouch());
		gear.add(new TrailRations());
		gear.add(new HempenRope());
		gear.add(new Sunrods());
		gear.add(new Waterskin());
		gear.add(new Arrows());
		gear.add(new CrossbowBolts());
		gear.add(new SlingBullets());
		gear.add(new Orb());
		gear.add(new Rod());
		gear.add(new Staff());
		gear.add(new Wand());
		gear.add(new Candle());
		gear.add(new Chain());
		gear.add(new Chest());
		gear.add(new ClimbersKit());
		gear.add(new GrapplingHook());
		gear.add(new Hammer());
		gear.add(new Pitons());
		gear.add(new EverburningTorch());
		gear.add(new FineClothing());
		gear.add(new Flask());
		gear.add(new HolySymbol());
		gear.add(new Journeybread());
		gear.add(new Lantern());
		gear.add(new RitualBook());
		gear.add(new SilkRope());
		gear.add(new Spellbook());
		gear.add(new Tent());
		gear.add(new ThievesTools());
		gear.add(new Torch());
		
		return gear;
	}

}
