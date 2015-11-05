package com.jimmie.domain.creatures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.EpicDestiny;
import com.jimmie.domain.MagicItem;
import com.jimmie.domain.ParagonPath;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.CommandingPresence;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.classes.EldritchPact;
import com.jimmie.domain.classes.Fighter;
import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.classes.Ranger;
import com.jimmie.domain.classes.Rogue;
import com.jimmie.domain.classes.Warlock;
import com.jimmie.domain.classes.Warlord;
import com.jimmie.domain.classes.Wizard;
import com.jimmie.domain.feats.Feat;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.util.Utils;

public class PlayerCharacter extends Character implements Serializable {
	private static final long serialVersionUID = 1L;
	private ParagonPath paragonPath;
	private EpicDestiny epicDestiny;
	private int experiencePoints;
	private String playerName;
	private List<Feat> feats;
	private List<MagicItem> magicItems;
	public int getHitPointsPerLevelGained() {
		return hitPointsPerLevelGained;
	}

	public void setHitPointsPerLevelGained(int hitPointsPerLevelGained) {
		this.hitPointsPerLevelGained = hitPointsPerLevelGained;
	}

	private PowerSource powerSource;
	private int hitPointsPerLevelGained = 0;

	public PowerSource getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(PowerSource powerSource) {
		this.powerSource = powerSource;
	}

	public PlayerCharacter(Race r, DndClass c) {
		race = r;
		dndClass = c;
	}

	public ParagonPath getParagonPath() {
		return paragonPath;
	}
	public void setParagonPath(ParagonPath paragonPath) {
		this.paragonPath = paragonPath;
	}
	public EpicDestiny getEpicDestiny() {
		return epicDestiny;
	}
	public void setEpicDestiny(EpicDestiny epicDestiny) {
		this.epicDestiny = epicDestiny;
	}
	public int getExperiencePoints() {
		return experiencePoints;
	}
	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public List<Feat> getFeats() {
		return feats;
	}
	public void setFeats(List<Feat> feats) {
		this.feats = feats;
	}
	public List<MagicItem> getMagicItems() {
		return magicItems;
	}
	public void setMagicItems(List<MagicItem> magicItems) {
		this.magicItems = magicItems;
	}

	/*
	 * Use this method to learn a new feat.
	 */
	public void selectFeat() {
		List<String> choices = new ArrayList<String>();
		/*
		 * TODO: Put in code to check if we already know that feat or not, and if it can be selected multiple times.
		 */
		
		/* Some races get to choose human feats. */
// JIM!!!! DON'T FORGET THAT HALF ELVES CAN CHOOSE HUMAN FEATS!!!
		if ((getDexterity() >= 15) && (Ranger.class.isInstance(dndClass))) {
			/* The book says that Hunter's Quarry is a prerequisite, but ALL Rangers have it, so I'm not checking for it. */
			choices.add("Agile Hunter");
		}
		choices.add("Alertness");
		// TODO: Armor of Bahamut
		if ((getStrength() >= 13) && (getConstitution() >= 13) && ((armorGroupProficiencies.contains(ArmorGroup.LEATHER) || (armorGroupProficiencies.contains(ArmorGroup.HIDE))))) {
			if (!armorGroupProficiencies.contains(ArmorGroup.CHAINMAIL)) {
				choices.add("Armor Proficiency (Chainmail)");
			}
		}
		if (!armorGroupProficiencies.contains(ArmorGroup.LEATHER)) {
			choices.add("Armor Proficiency (Leather)");
		}
		if ((getStrength() >= 13) && (getConstitution() >= 13) && (armorGroupProficiencies.contains(ArmorGroup.HIDE))) {
			if (!armorGroupProficiencies.contains(ArmorGroup.HIDE)) {
				choices.add("Armor Proficiency (Hide)");
			}
		}
		if ((getStrength() >= 15) && (getConstitution() >= 15) && (armorGroupProficiencies.contains(ArmorGroup.SCALE))) {
			if (!armorGroupProficiencies.contains(ArmorGroup.PLATE)) {
				choices.add("Armor Proficiency (Plate)");
			}
		}
		if ((getStrength() >= 13) && (getConstitution() >= 13) && (armorGroupProficiencies.contains(ArmorGroup.CHAINMAIL))) {
			if (!armorGroupProficiencies.contains(ArmorGroup.SCALE)) {
				choices.add("Armor Proficiency (Scale)");
			}
		}
		if ((getDexterity() >= 13) && (getCharisma() >= 13)) {
			choices.add("Astral Fire");
		}
		// TODO: Avandra's Rescue
		if (Rogue.class.isInstance(dndClass)) {
			// The book says Sneak Attack is a prerequisite, but all rogues have it, so I'm not checking for it.
			choices.add("Backstabber");
		}
		if ((getDexterity() >= 13) && (getStrength() >= 13)) {
			choices.add("Blade Opportunist");
		}
		if ((getIntelligence() >= 13) && (getWisdom() >= 13)) {
			choices.add("Burning Blizzard");
		}
		if (getDexterity() >= 13) {
			choices.add("Combat Reflexes");
		}
		// TODO: Corellon's Grace
		if ((getConstitution() >= 13) && (getWisdom() >= 13)) {
			choices.add("Dark Fury");
		}
		choices.add("Defensive Mobility");		
		if ((Fighter.class.isInstance(dndClass)) && (getWisdom() >= 13)) {
			// The book says Combat Challenge is a prerequisite, but all fighters have it, so I'm not checking for it.
			choices.add("Distracting Shield");
		}
		if (Dwarf.class.isInstance(race)) {
			choices.add("Dodge Giants");
		}
		if (Dragonborn.class.isInstance(race)) {
			choices.add("Dragonborn Frenzy");
		}
		if (Dragonborn.class.isInstance(race)) {
			choices.add("Dragonborn Senses");
		}
		choices.add("Durable");
		if (Dwarf.class.isInstance(race)) {
			choices.add("Dwarven Weapon Training");
		}
		if (Eladrin.class.isInstance(race)) {
			choices.add("Eladrin Soldier");
		}
		if (Elf.class.isInstance(race)) {
			choices.add("Elven Precision");
		}
		if (Dragonborn.class.isInstance(race)) {
			// The book says that Dragon Breath is a prerequisite, but all dragonborns have it, so I'm not checking for it.
			choices.add("Enlarged Dragon Breath");
		}
		if (getSkill(SkillType.ACROBATICS).isTrained()) {
			choices.add("Escape Artist");
		}
		if ((Wizard.class.isInstance(dndClass)) && (getWisdom() >= 13)) {
			choices.add("Expanded Spellbook");
		}		
		if (getDexterity() >= 13) {
			choices.add("Far Shot");
		}		
		if (getStrength() >= 13) {
			choices.add("Far Throw");
		}		
		if (getConstitution() >= 13) {
			choices.add("Fast Runner");
		}
		if (Tiefling.class.isInstance(race)) {
			// The book says that Infernal Wrath is a prerequisite, but all Tieflings have it, so I'm not checking for it.
			choices.add("Ferocious Rebuke");
		}
		if (HalfElf.class.isInstance(race)) {
			choices.add("Group Insight");
		}
		if (Halfling.class.isInstance(race)) {
			// The book says that Second Chance is a prerequisite, but all Halflings have it, so I'm not checking for it.
			choices.add("Halfling Agility");
		}
		// TODO: Harmony of Erathis
		if (Paladin.class.isInstance(dndClass)) {
			// The book says that Lay On Hands power is a prerequisite, but all Paladins have it, so I'm not checking for it.
			choices.add("Healing Hands");
		}
		if (Tiefling.class.isInstance(race)) {
			choices.add("Hellfire Blood");
		}
		if ((Human.class.isInstance(race)) || (HalfElf.class.isInstance(race))) {
			choices.add("Human Perseverance");
		}
		if ((getConstitution() >= 15) && (Warlock.class.isInstance(dndClass))) {
			Warlock warlock = (Warlock) dndClass;
			if (warlock.getEldritchPact() == EldritchPact.INFERNAL_PACT) {
				choices.add("Improved Dark One's Blessing");
			}
		}
		if (((getConstitution() >= 13) || (getCharisma() >= 13)) && (Warlock.class.isInstance(dndClass))) {
			Warlock warlock = (Warlock) dndClass;
			if (warlock.getEldritchPact() == EldritchPact.STAR_PACT) {
				choices.add("Improved Fate of the Void");
			}
		}
		choices.add("Improved Initiative");
		if ((getIntelligence() >= 13)  && (Warlock.class.isInstance(dndClass))) {
			Warlock warlock = (Warlock) dndClass;
			if (warlock.getEldritchPact() == EldritchPact.FEY_PACT) {
				choices.add("Improved Misty Step");
			}
		}
		if (Warlord.class.isInstance(dndClass)) {
			Warlord warlord = (Warlord) dndClass;
			if (warlord.getCommandingPresence() == CommandingPresence.INSPIRING_PRESENCE) {
				choices.add("Inspired Recovery");
			}
		}
		// TODO: Ioun's Poise
		if (getIntelligence() >= 13) {
			choices.add("Jack of All Trades");
		}
		// TODO: Kord's Favor
		if (Ranger.class.isInstance(dndClass)) {
			// The book says that Hunter's Quarry is a prerequisite, but all Rangers have it, so I'm not looking for it.
			choices.add("Lethal Hunter");
		}
		if (Elf.class.isInstance(race)) {
			choices.add("Light Step");
		}
		if (getIntelligence() <= 13) {
			choices.add("Linguist");
		}
		if (getSkill(SkillType.ATHLETICS).isTrained()) {
			choices.add("Long Jumper");
		}
		if (Halfling.class.isInstance(race)) {
			choices.add("Lost in the Crowd");
		}
		// TODO: Melora's Tide
		// TODO: Moradin's Resolve
		choices.add("Mounted Combat");
		if (getDexterity() >= 15) {
			choices.add("Nimble Blade");
		}
		// TODO: Pelor's Radiance
		if ((getConstitution() >= 15) && (Fighter.class.isInstance(dndClass))) {
			// The book says that Combat Challenge is a prerequisite, but all Fighters have it, so I'm not checking for it.
			choices.add("Potent Challenge");
		}
		if (getStrength() >= 15) {
			choices.add("Power Attack");
		}
		if (getStrength() >= 13) {
			choices.add("Powerful Charge");
		}
		if ((getWisdom() >= 15) && (Ranger.class.isInstance(dndClass))) {
			/* The book says that Hunter's Quarry is a prerequisite, but ALL Rangers have it, so I'm not checking for it. */
			choices.add("Precise Hunter");
		}
		if ((getCharisma() >= 15) && (Rogue.class.isInstance(dndClass))) {
			choices.add("Press the Advantage");
		}
		if (getDexterity() >= 13) {
			choices.add("Quick Draw");
		}
		if ((getConstitution() >= 13) && (getDexterity() >= 13)) {
			choices.add("Raging Storm");
		}
		// TODO: Raven Queen's Blessing
		if ((getSkill(SkillType.ARCANA).isTrained()) || (getSkill(SkillType.RELIGION).isTrained())) {
			choices.add("Ritual Caster");
		}
		// TODO: Sehanine's Reversal
		if ((getStrength() >= 15) && (armorGroupProficiencies.contains(ArmorGroup.LIGHT_SHIELD))) {
			if (!armorGroupProficiencies.contains(ArmorGroup.HEAVY_SHIELD)) {
				choices.add("Shield Proficiency (Heavy)");
			}
		}
		if (getStrength() >= 13) {
			if (!armorGroupProficiencies.contains(ArmorGroup.LIGHT_SHIELD)) {
				choices.add("Shield Proficiency (Light)");
			}
		}
		if (Fighter.class.isInstance(dndClass)) {
			// The book says Combat Challenge is a prerequisite, but all fighters have it, so I'm not checking for it.
			choices.add("Shield Push");
		}
		if (getSkill(SkillType.ACROBATICS).isTrained()) {
			choices.add("Skill Focus (Acrobatics)");
		}
		if (getSkill(SkillType.ARCANA).isTrained()) {
			choices.add("Skill Focus (Arcana)");
		}
		if (getSkill(SkillType.ATHLETICS).isTrained()) {
			choices.add("Skill Focus (Athletics)");
		}
		if (getSkill(SkillType.BLUFF).isTrained()) {
			choices.add("Skill Focus (Bluff)");
		}
		if (getSkill(SkillType.DIPLOMACY).isTrained()) {
			choices.add("Skill Focus (Diplomacy)");
		}
		if (getSkill(SkillType.DUNGEONEERING).isTrained()) {
			choices.add("Skill Focus (Dungeoneering)");
		}
		if (getSkill(SkillType.ENDURANCE).isTrained()) {
			choices.add("Skill Focus (Endurance)");
		}
		if (getSkill(SkillType.HEAL).isTrained()) {
			choices.add("Skill Focus (Heal)");
		}
		if (getSkill(SkillType.HISTORY).isTrained()) {
			choices.add("Skill Focus (History)");
		}
		if (getSkill(SkillType.INSIGHT).isTrained()) {
			choices.add("Skill Focus (Insight)");
		}
		if (getSkill(SkillType.INTIMIDATE).isTrained()) {
			choices.add("Skill Focus (Intimidate)");
		}
		if (getSkill(SkillType.NATURE).isTrained()) {
			choices.add("Skill Focus (Nature)");
		}
		if (getSkill(SkillType.PERCEPTION).isTrained()) {
			choices.add("Skill Focus (Perception)");
		}
		if (getSkill(SkillType.RELIGION).isTrained()) {
			choices.add("Skill Focus (Religion)");
		}
		if (getSkill(SkillType.STEALTH).isTrained()) {
			choices.add("Skill Focus (Stealth)");
		}
		if (getSkill(SkillType.STREETWISE).isTrained()) {
			choices.add("Skill Focus (Streetwise)");
		}
		if (getSkill(SkillType.THIEVERY).isTrained()) {
			choices.add("Skill Focus (Thievery)");
		}
		if (!getSkill(SkillType.ACROBATICS).isTrained()) {
			choices.add("Skill Training (Acrobatics)");
		}
		if (!getSkill(SkillType.ARCANA).isTrained()) {
			choices.add("Skill Training (Arcana)");
		}
		if (!getSkill(SkillType.ATHLETICS).isTrained()) {
			choices.add("Skill Training (Athletics)");
		}
		if (!getSkill(SkillType.BLUFF).isTrained()) {
			choices.add("Skill Training (Bluff)");
		}
		if (!getSkill(SkillType.DIPLOMACY).isTrained()) {
			choices.add("Skill Training (Diplomacy)");
		}
		if (!getSkill(SkillType.DUNGEONEERING).isTrained()) {
			choices.add("Skill Training (Dungeoneering)");
		}
		if (!getSkill(SkillType.ENDURANCE).isTrained()) {
			choices.add("Skill Training (Endurance)");
		}
		if (!getSkill(SkillType.HEAL).isTrained()) {
			choices.add("Skill Training (Heal)");
		}
		if (!getSkill(SkillType.HISTORY).isTrained()) {
			choices.add("Skill Training (History)");
		}
		if (!getSkill(SkillType.INSIGHT).isTrained()) {
			choices.add("Skill Training (Insight)");
		}
		if (!getSkill(SkillType.INTIMIDATE).isTrained()) {
			choices.add("Skill Training (Intimidate)");
		}
		if (!getSkill(SkillType.NATURE).isTrained()) {
			choices.add("Skill Training (Nature)");
		}
		if (!getSkill(SkillType.PERCEPTION).isTrained()) {
			choices.add("Skill Training (Perception)");
		}
		if (!getSkill(SkillType.RELIGION).isTrained()) {
			choices.add("Skill Training (Religion)");
		}
		if (!getSkill(SkillType.STEALTH).isTrained()) {
			choices.add("Skill Training (Stealth)");
		}
		if (!getSkill(SkillType.STREETWISE).isTrained()) {
			choices.add("Skill Training (Streetwise)");
		}
		if (!getSkill(SkillType.THIEVERY).isTrained()) {
			choices.add("Skill Training (Thievery)");
		}
		if (getSkill(SkillType.ATHLETICS).isTrained()) {
			choices.add("Sure Climber");
		}
		if ((getStrength() >= 15) && (Rogue.class.isInstance(dndClass))) {
			choices.add("Surprise Knockdown");
		}
		if (Warlord.class.isInstance(dndClass)) {
			Warlord warlord = (Warlord) dndClass;
			if (warlord.getCommandingPresence() == CommandingPresence.TACTICAL_PRESENCE) {
				choices.add("Tactical Assault");
			}
		}
		choices.add("Toughness");
		// TODO: Two-Weapon Defense
		if (getDexterity() >= 13) {
			choices.add("Two-Weapon Fighting");
		}
		// TODO: Weapon Focus
		// TODO: Weapon Proficiency
		choices.add("Wintertouched");

		
		
		Utils.print("Please select one of the following Feats:");
		Utils.printValidStringChoices(choices);
		Utils.print("Your choice:");
		String feat = Utils.getValidInput(choices);
	}
}
