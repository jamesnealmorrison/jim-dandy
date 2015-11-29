package com.jimmie.domain.creatures;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.util.Utils;

public class Shardmind extends Race {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getRacialDamageBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initializeForEncounter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processAfterHurtEffects(Creature creature) {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeRaceChoices(PlayerCharacter pc, DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following names:");
		Utils.print("Names: Amata, Arshaka, Arwia, Balashi, Bashanu, Belessunu, Dipana, Erishti, Eshunu, Hunzu,");
		Utils.print("       Iltani, Ishmea, Kuaya, Kubaba, Kuri, Manishtu, Naram, Nuraya, Seluku, Tabni, Ubashu, Utua, Zakiti");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 5' 9\" - 6' 2\" (69\" - 74\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 180 - 230 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setBaseSpeed(6);
		
		Utils.print("Adding normal vision to senses.");
		pc.addSense(new Sense(SenseType.NORMAL_VISION));
		
		pc.addLanguage("Common");
		pc.addLanguage("DeepSpeech");
		
		// TODO: Language choice
		Utils.print("NOTE: I have not yet coded the language choice for Shardmind.");
		
		Utils.print("As a Shardmind, you get +2 Arcana and Endurance");
		Skill arcana = pc.getSkill(SkillType.ARCANA);
		arcana.setMisc(arcana.getMisc()+2);
		
		Skill endurance = pc.getSkill(SkillType.ENDURANCE);
		endurance.setMisc(endurance.getMisc()+2);
		
		Utils.print("As an Shardmind, you get to choose one extra skill to add +2 bonus.");
		
		List<String> choices = new ArrayList<String>();
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Bluff");
		choices.add("Diplomacy");
		choices.add("Dungeoneering");
		choices.add("Heal");
		choices.add("History");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Nature");
		choices.add("Perception");
		choices.add("Religion");
		choices.add("Stealth");
		choices.add("Streetwise");
		choices.add("Thievery");
		
		Utils.print("Choose one of the following");
		Utils.printValidStringChoices(choices);
		Utils.print("Your choice:");
		String s = Utils.getValidInput(choices);

		Skill skill = null;
		if ("Acrobatics".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.ACROBATICS);
		} else if ("Athletics".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.ATHLETICS);
		} else if ("Bluff".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.BLUFF);
		} else if ("Diplomacy".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.DIPLOMACY);
		} else if ("Dungeoneering".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.DUNGEONEERING);
		} else if ("Heal".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.HEAL);
		} else if ("History".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.HISTORY);
		} else if ("Insight".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.INSIGHT);
		} else if ("Intimidate".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.INTIMIDATE);
		} else if ("Nature".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.NATURE);
		} else if ("Perception".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.PERCEPTION);
		} else if ("Religion".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.RELIGION);
		} else if ("Stealth".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.STEALTH);
		} else if ("Streetwise".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.STREETWISE);
		} else if ("Thievery".equalsIgnoreCase(s)) {
			skill = pc.getSkill(SkillType.THIEVERY);
		} else {
			Utils.print("ERROR!!!!! There was a skill selected that I don't understand: " + s);
		}
		if (skill != null) {
			skill.setMisc(skill.getMisc()+2);
		}
		
		// TODO: Telepathy, Crystalline Mind, Living Construct, Immortal Origin, Shard Swarm.
		Utils.print("NOTE: Telepathy, Crystalline Mind, Living Construct, Immortal Origin, Shard Swarm.");
		
		
		
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As a Shardmind you get +2 to Intelligence.");
		setIntelligenceBonus(getIntelligenceBonus()+2);

		Utils.print("As a Shardmind, you get to choose to add +2 to Wisdom or Charisma.");
		Utils.print("1. Wisdom");
		Utils.print("2. Charisma");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (1 == choice) {
			setWisdomBonus(getWisdomBonus()+2);
		} else {
			setCharismaBonus(getCharismaBonus()+2);
		}
	}

}
