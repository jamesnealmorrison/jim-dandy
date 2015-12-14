package com.jimmie.domain.creatures.monsters;

import java.util.List;

import com.jimmie.domain.Aura;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureType;
import com.jimmie.domain.creatures.Origin;
import com.jimmie.util.Utils;

public abstract class Monster extends Creature {

	public Monster() {
		super();
	}
	@Override
	public boolean isShieldReadied() {
		/* For monsters, the shield bonus is already part of their armor class.  So this method returns
		 * false.  It should be overridden in the character class. */
		return false;
	}
	private static final long serialVersionUID = 1L;
	protected List<Aura> auras;
	protected int savingThrows;
	protected String tactics;
	protected int experiencePointValue;
	protected Origin origin;
	protected CreatureType type;
	protected List<MonsterKeyword> keywords;
	protected int armorClass;
	protected int fortitude;
	public int getFortitude() {
		return fortitude;
	}
	public void setFortitude(int fortitude) {
		this.fortitude = fortitude;
	}
	public int getArmorClass(Creature attacker) {
		return armorClass;
	}
	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}
	public List<MonsterKeyword> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<MonsterKeyword> keywords) {
		this.keywords = keywords;
	}
	protected boolean elite;
	public boolean isElite() {
		return elite;
	}
	public void setElite(boolean elite) {
		this.elite = elite;
	}
	public boolean isSolo() {
		return solo;
	}
	public void setSolo(boolean solo) {
		this.solo = solo;
	}
	public boolean isLeader() {
		return leader;
	}
	public void setLeader(boolean leader) {
		this.leader = leader;
	}
	protected boolean solo;
	protected boolean leader;
	public List<Aura> getAuras() {
		return auras;
	}
	public void setAuras(List<Aura> auras) {
		this.auras = auras;
	}
	public int getSavingThrows() {
		return savingThrows;
	}
	public void setSavingThrows(int savingThrows) {
		this.savingThrows = savingThrows;
	}
	public String getTactics() {
		return tactics;
	}
	public void setTactics(String tactics) {
		this.tactics = tactics;
	}
	public int getExperiencePointValue() {
		return experiencePointValue;
	}
	public void setExperiencePointValue(int experiencePointValue) {
		this.experiencePointValue = experiencePointValue;
	}
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	public CreatureType getType() {
		return type;
	}
	public void setType(CreatureType type) {
		this.type = type;
	}
	
	public int getStrengthModifier() {
		Utils.print("You have reached the getStrengthModifier method for the Monster class.  This is a problem.  This method should be overridden in the subclass.  FIX IT.");
		return 0;
	}

	public int getConstitutionModifier() {
		Utils.print("You have reached the getConstitutionModifier method for the Monster class.  This is a problem.  This method should be overridden in the subclass.  FIX IT.");
		return 0;
	}

	public int getDexterityModifier() {
		Utils.print("You have reached the getDexterityModifier method for the Monster class.  This is a problem.  This method should be overridden in the subclass.  FIX IT.");
		return 0;
	}

	public int getIntelligenceModifier() {
		Utils.print("You have reached the getIntelligenceModifier method for the Monster class.  This is a problem.  This method should be overridden in the subclass.  FIX IT.");
		return 0;
	}
	
	public int getWisdomModifier() {
		Utils.print("You have reached the getWisdomModifier method for the Monster class.  This is a problem.  This method should be overridden in the subclass.  FIX IT.");
		return 0;
	}
	
	public int getCharismaModifier() {
		Utils.print("You have reached the getCharismaModifier method for the Monster class.  This is a problem.  This method should be overridden in the subclass.  FIX IT.");
		return 0;
	}
}
