package com.jimmie.domain.creatures;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.NotEnoughCurrencyException;
import com.jimmie.domain.PowerId;
import com.jimmie.domain.Resistance;
import com.jimmie.domain.Ritual;
import com.jimmie.domain.TemporaryAidAnotherBonus;
import com.jimmie.domain.TemporaryBonus;
import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.classes.Fighter;
import com.jimmie.domain.classes.GuardianMight;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.classes.WeaponTalent;
import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Coins;
import com.jimmie.domain.items.Gear;
import com.jimmie.domain.items.Implement;
import com.jimmie.domain.items.Price;
import com.jimmie.domain.items.armor.Armor;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.armor.ArmorType;
import com.jimmie.domain.items.armor.ClothArmor;
import com.jimmie.domain.items.armor.NoShield;
import com.jimmie.domain.items.armor.Shield;
import com.jimmie.domain.items.weapons.Unarmed;
import com.jimmie.domain.items.weapons.Weapon;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.domain.items.weapons.WeaponGroup;
import com.jimmie.domain.items.weapons.WeaponHandType;
import com.jimmie.domain.items.weapons.WeaponType;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.Dice;
import com.jimmie.util.EncounterPower;
import com.jimmie.util.StandardAction;
import com.jimmie.util.Utils;
import com.jimmie.domain.AttackType;

public abstract class Character extends Creature {
	private Armor readiedArmor;
	private List<Armor> armor;
	private List<Gear> gear;

	public List<Gear> getGear() {
		return gear;
	}

	public void setGear(List<Gear> gear) {
		this.gear = gear;
	}

	public List<Armor> getArmor() {
		return armor;
	}

	public void setArmor(List<Armor> armor) {
		this.armor = armor;
	}

	public void setReadiedArmor(Armor readiedArmor) {
		this.readiedArmor = readiedArmor;
	}

	public int getBaseArmorClass() {
		return (10+getLevel()/2 + getReadiedArmor().getBonus()  + dndClass.getArmorClassBonus() + getReadiedShield().getBonus());
		// TODO: Enhancement and two miscellaneous????
	}
	
	@Override
	public int getArmorClass(Creature attacker) {
		int armorClass = getBaseArmorClass();

		/* Light armor lets you add intelligence or dexterity modifier, whichever is greater. */
		if (getReadiedArmor().isLightArmor()) {
			if (Warden.class.isInstance(dndClass)) {
				if (((Warden) dndClass).getGuardianMight() == GuardianMight.EARTHSTRENGTH) {
					if ((getAbilityModifierPlusHalfLevel(AbilityType.CONSTITUTION) > getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE)) &&
							(getAbilityModifierPlusHalfLevel(AbilityType.CONSTITUTION) > getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY))) {
						armorClass = armorClass + getAbilityModifierPlusHalfLevel(AbilityType.CONSTITUTION);
					} else {
						if (getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE) > getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY)) {
							armorClass = armorClass + getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE);
						} else {
							armorClass = armorClass + getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY);
						}					
					}
				}
			} else {
				if (getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE) > getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY)) {
					armorClass = armorClass + getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE);
				} else {
					armorClass = armorClass + getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY);
				}
			}
			/* Avenger's have "Armor of Faith". */
			if (Avenger.class.isInstance(dndClass)) {
				/* But they only get the bonus if wearing light armor AND not using a shield. */
				if (getReadiedArmor().isLightArmor() && (NoShield.class.isInstance(getReadiedShield()))) {
					armorClass = armorClass + 3;
				}
			}
		}

		/* See if there is a temporary bonus to the armor class. */
		if (temporaryArmorClassBonus != null) {
			if (temporaryArmorClassBonus.stillApplies()) {
				Utils.print(name + " is supposed to get a bonus to armor class until the end of " + temporaryArmorClassBonus.getSource().getName() + "'s next turn.");
				armorClass = armorClass + temporaryArmorClassBonus.getBonus();
				Utils.print("Bonus still applies.");
			} else {
				/* Bonus is over.  Reset the bonus. */
				temporaryArmorClassBonus = null;
				Utils.print("Bonus no longer applies.  Resetting bonus.");
			}
		}

		/* See if there is a temporary defense bonus due to the "Aid another" bonus. */
		if ((temporaryAidAnotherBonus != null) && (temporaryAidAnotherBonus.getType() == TemporaryAidAnotherBonus.DEFENSE)) {
			if (temporaryAidAnotherBonus.stillApplies() && (temporaryAidAnotherBonus.getTarget() == attacker)) {
				Utils.print(name + " is supposed to get a bonus of " + temporaryAidAnotherBonus.getBonus() + " to defense against this attack by " + attacker.getName() + ".");
				armorClass = armorClass + temporaryAidAnotherBonus.getBonus();
				Utils.print("Bonus still applies.");
				temporaryAidAnotherBonus = null;
				Utils.print("One time bonus so bonus no longer applies.  Resetting bonus.");
			} else {
				/* Bonus is over.  Reset the bonus. */
				temporaryAidAnotherBonus = null;
				Utils.print("Bonus no longer applies.  Resetting bonus.");
			}
		}

		return armorClass;
	}

	public Armor getReadiedArmor() {
		if (readiedArmor == null) {
			readiedArmor = new ClothArmor();
		}
		return readiedArmor;
	}
	private static final long serialVersionUID = 1L;
	public List<Ritual> getRituals() {
		return rituals;
	}
	public void setRituals(List<Ritual> rituals) {
		this.rituals = rituals;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getAdventuringCompanyOrOtherAffiliations() {
		return adventuringCompanyOrOtherAffiliations;
	}
	public void setAdventuringCompanyOrOtherAffiliations(
			String adventuringCompanyOrOtherAffiliations) {
		this.adventuringCompanyOrOtherAffiliations = adventuringCompanyOrOtherAffiliations;
	}
	public int getInitiativeMisc() {
		return initiativeMisc;
	}
	public void setInitiativeMisc(int initiativeMisc) {
		this.initiativeMisc = initiativeMisc;
	}
	public int getArmorClassMisc1() {
		return armorClassMisc1;
	}
	public void setArmorClassMisc1(int armorClassMisc1) {
		this.armorClassMisc1 = armorClassMisc1;
	}
	public int getArmorClassMisc2() {
		return armorClassMisc2;
	}
	public void setArmorClassMisc2(int armorClassMisc2) {
		this.armorClassMisc2 = armorClassMisc2;
	}
	public int getFortitudeMisc1() {
		return fortitudeMisc1;
	}
	public void setFortitudeMisc1(int fortitudeMisc1) {
		this.fortitudeMisc1 = fortitudeMisc1;
	}
	public int getFortitudeMisc2() {
		return fortitudeMisc2;
	}
	public void setFortitudeMisc2(int fortitudeMisc2) {
		this.fortitudeMisc2 = fortitudeMisc2;
	}
	public int getReflexMisc1() {
		return reflexMisc1;
	}
	@Override
	public int getFortitude(Encounter encounter, Creature attacker) {
		return super.getFortitude(encounter, attacker) + getFortitudeMisc1() + getFortitudeMisc1();
	}

	@Override
	public int getReflex(Creature attacker) {
		return super.getReflex(attacker) + getReflexMisc1() + getReflexMisc2();
	}

	@Override
	public int getWill(Creature attacker) {
		return super.getWill(attacker) + getWillMisc1() + getWillMisc2();
	}

	public void setReflexMisc1(int reflexMisc1) {
		this.reflexMisc1 = reflexMisc1;
	}
	public int getReflexMisc2() {
		return reflexMisc2;
	}
	public void setReflexMisc2(int reflexMisc2) {
		this.reflexMisc2 = reflexMisc2;
	}
	public int getWillMisc1() {
		return willMisc1;
	}
	public void setWillMisc1(int willMisc1) {
		this.willMisc1 = willMisc1;
	}
	public int getWillMisc2() {
		return willMisc2;
	}
	public void setWillMisc2(int willMisc2) {
		this.willMisc2 = willMisc2;
	}
	public int getSpeedMisc() {
		return speedMisc;
	}
	public void setSpeedMisc(int speedMisc) {
		this.speedMisc = speedMisc;
	}
	public int getHealingSurgeValue() {
		return healingSurgeValue;
	}
	public void setHealingSurgeValue(int healingSurgeValue) {
		this.healingSurgeValue = healingSurgeValue;
	}
	public int getHealingSurgesPerDay() {
		return healingSurgesPerDay;
	}
	public void setHealingSurgesPerDay(int healingSurgesPerDay) {
		this.healingSurgesPerDay = healingSurgesPerDay;
	}
	public int getCurrentSurgeUses() {
		return currentSurgeUses;
	}
	public void setCurrentSurgeUses(int currentSurgeUses) {
		this.currentSurgeUses = currentSurgeUses;
	}
	public int getDeathSavingThrowFailures() {
		return deathSavingThrowFailures;
	}
	public void setDeathSavingThrowFailures(int deathSavingThrowFailures) {
		this.deathSavingThrowFailures = deathSavingThrowFailures;
	}
	public String getDeathSavingThrowMods() {
		return deathSavingThrowMods;
	}
	public void setDeathSavingThrowMods(String deathSavingThrowMods) {
		this.deathSavingThrowMods = deathSavingThrowMods;
	}
	public List<Resistance> getResistances() {
		return resistances;
	}
	public void setResistances(List<Resistance> resistances) {
		this.resistances = resistances;
	}
	public String getAdditionalEffectsForSpendingActionPoints() {
		return additionalEffectsForSpendingActionPoints;
	}
	public void setAdditionalEffectsForSpendingActionPoints(
			String additionalEffectsForSpendingActionPoints) {
		this.additionalEffectsForSpendingActionPoints = additionalEffectsForSpendingActionPoints;
	}
	public String getPersonalityTraits() {
		return personalityTraits;
	}
	public void setPersonalityTraits(String personalityTraits) {
		this.personalityTraits = personalityTraits;
	}
	public String getMannerismsAndAppearance() {
		return mannerismsAndAppearance;
	}
	public void setMannerismsAndAppearance(String mannerismsAndAppearance) {
		this.mannerismsAndAppearance = mannerismsAndAppearance;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public List<CompanionOrAlly> getCompanionsAndAllies() {
		return companionsAndAllies;
	}
	public void setCompanionsAndAllies(List<CompanionOrAlly> companionsAndAllies) {
		this.companionsAndAllies = companionsAndAllies;
	}
	public List<String> getSessionAndCampaignNotes() {
		return sessionAndCampaignNotes;
	}
	public void setSessionAndCampaignNotes(List<String> sessionAndCampaignNotes) {
		this.sessionAndCampaignNotes = sessionAndCampaignNotes;
	}

	protected Coins coins = new Coins();
	
	protected List<Ritual> rituals;	
	protected int age = 0;
	protected Gender gender;
	protected int height = 0;  // in inches
	protected int weight = 0;  // in pounds
	protected Deity deity;
	public Deity getDeity() {
		return deity;
	}

	public void setDeity(Deity deity) {
		this.deity = deity;
	}
	protected String adventuringCompanyOrOtherAffiliations;
	protected int initiativeMisc = 0;
	protected int armorClassMisc1 = 0;
	protected int armorClassMisc2 = 0;
	protected int fortitudeMisc1 = 0;
	protected int fortitudeMisc2 = 0;
	protected int reflexMisc1 = 0;
	protected int reflexMisc2 = 0;
	protected int willMisc1 = 0;
	protected int willMisc2 = 0;
	protected int speedMisc = 0;
	protected int healingSurgeValue = 0;
	protected int healingSurgesPerDay = 0;
	protected int currentSurgeUses = 0;
	protected boolean usedSecondWind = false;
	protected int deathSavingThrowFailures = 0;
	protected String deathSavingThrowMods; // Not sure what this is.
	protected List<Resistance> resistances;
	protected String additionalEffectsForSpendingActionPoints;
	protected String personalityTraits;
	protected String mannerismsAndAppearance;
	protected String background;
	protected List<CompanionOrAlly> companionsAndAllies;
	protected List<String> sessionAndCampaignNotes;
	private Weapon readiedWeapon;
	private List<Weapon> weapons;
	private Implement readiedImplement;
	private List<WeaponType> weaponTypeProficiencies;
	private List<WeaponGroup> weaponGroupProficiencies;
	private List<WeaponCategory> weaponCategoryProficiencies;
	private List<ArmorType> armorTypeProficiencies;
	protected List<ArmorGroup> armorGroupProficiencies;

	public List<WeaponType> getWeaponTypeProficiencies() {
		return weaponTypeProficiencies;
	}

	public void setWeaponTypeProficiencies(List<WeaponType> weaponTypeProficiencies) {
		this.weaponTypeProficiencies = weaponTypeProficiencies;
	}

	public List<WeaponGroup> getWeaponGroupProficiencies() {
		return weaponGroupProficiencies;
	}

	public void setWeaponGroupProficiencies(
			List<WeaponGroup> weaponGroupProficiencies) {
		this.weaponGroupProficiencies = weaponGroupProficiencies;
	}

	public List<WeaponCategory> getWeaponCategoryProficiencies() {
		return weaponCategoryProficiencies;
	}

	public void setWeaponCategoryProficiencies(
			List<WeaponCategory> weaponCategoryProficiencies) {
		this.weaponCategoryProficiencies = weaponCategoryProficiencies;
	}

	public List<ArmorType> getArmorTypeProficiencies() {
		return armorTypeProficiencies;
	}

	public void setArmorTypeProficiencies(List<ArmorType> armorTypeProficiencies) {
		this.armorTypeProficiencies = armorTypeProficiencies;
	}

	public List<ArmorGroup> getArmorGroupProficiencies() {
		return armorGroupProficiencies;
	}

	public void setArmorGroupProficiencies(List<ArmorGroup> armorGroupProficiencies) {
		this.armorGroupProficiencies = armorGroupProficiencies;
	}

	public void setReadiedWeapon(Weapon readiedWeapon) {
		this.readiedWeapon = readiedWeapon;
	}
	@Override
	public int getInitiative() {
		return getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY) + getInitiativeMisc();
	}

	public void addPower(PowerId powerId) {
		powers.add(powerId);
	}

	public void addWeaponTypeProficiency(WeaponType weaponType) {
		if (weaponTypeProficiencies == null) {
			weaponTypeProficiencies = new ArrayList<WeaponType>();
		}
		weaponTypeProficiencies.add(weaponType);
	}

	public void addWeaponGroupProficiency(WeaponGroup weaponGroup) {
		if (weaponGroupProficiencies == null) {
			weaponGroupProficiencies = new ArrayList<WeaponGroup>();
		}
		weaponGroupProficiencies.add(weaponGroup);
	}

	public void addWeaponCategoryProficiency(WeaponCategory weaponCategory) {
		if (weaponCategoryProficiencies == null) {
			weaponCategoryProficiencies = new ArrayList<WeaponCategory>();
		}
		weaponCategoryProficiencies.add(weaponCategory);
	}

	public void addArmorTypeProficiency(ArmorType armorType) {
		if (armorTypeProficiencies == null) {
			armorTypeProficiencies = new ArrayList<ArmorType>();
		}
		armorTypeProficiencies.add(armorType);
	}

	public void addArmorGroupProficiency(ArmorGroup armorGroup) {
		if (armorGroupProficiencies == null) {
			armorGroupProficiencies = new ArrayList<ArmorGroup>();
		}
		armorGroupProficiencies.add(armorGroup);
	}

	public Character() {
		super();
		weaponTypeProficiencies = new ArrayList<WeaponType>();
		weaponGroupProficiencies = new ArrayList<WeaponGroup>();
		weaponCategoryProficiencies = new ArrayList<WeaponCategory>();
		armorTypeProficiencies = new ArrayList<ArmorType>();
		armorGroupProficiencies = new ArrayList<ArmorGroup>();
		addPower(PowerId.BASIC_MELEE_ATTACK);
		addPower(PowerId.SPEND_ACTION_POINT);
		addPower(PowerId.SECOND_WIND);
		addPower(PowerId.AID_ANOTHER);
	}

	@StandardAction(powerId = PowerId.BASIC_MELEE_ATTACK, isBasicAttack = true, weaponTag = true, powerSource = PowerSource.NONE, attackType = AttackType.MELEE)
	@AtWillPower
	public void basicMeleeAttack(Encounter encounter) {
		AttackTarget target = encounter.chooseMeleeTarget(this, getReadiedWeapon().getNormalRange());

		if (target != null) {
			List<AttackTarget> targets = new ArrayList<AttackTarget>();
			targets.add(target);
			Dice d = new Dice(DiceType.TWENTY_SIDED);
			int diceRoll = d.attackRoll(this, target, encounter, getCurrentPosition());
			int roll = diceRoll + getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + getWeaponProficiencyBonus() + getOtherAttackModifier(targets, encounter);

			Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);

			int targetArmorClass = target.getArmorClass(this);
			Utils.print("Your target has an AC of " + targetArmorClass);

			if (roll >= targetArmorClass) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				/* See if this target was hit by Stirring Shout. */
				if (target.isHitByStirringShout()) {
					Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
					heal(target.getStirringShoutCharismaModifier());
				}

				int damageRolls = this.getReadiedWeapon().getDamageRolls();
				DiceType damageDiceType = this.getReadiedWeapon().getDamageDice();

				/* Book says at level 21 increase damage to 2[W]. */
				if (getLevel() >= 21) {
					damageRolls = damageRolls * 2;
				}
				int avengerBonus = 0;
				if (Avenger.class.isInstance(dndClass)) {
					if (((Avenger) dndClass).isAspectOfMightEncounterBonus() == true) {
						avengerBonus = 2;
						Utils.print("Because of your Aspect Of Might bonus, you get a two bonus to this damage.  I'll add it for you!");
					}
				}
				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, getReadiedWeapon().getDamageBonus() + avengerBonus, getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), race), DamageType.NORMAL_DAMAGE, encounter, true);
			} else {
				Utils.print("You missed " + target.getName());
			}

			/* If this is a fighter, then they have "Combat Challenge", and can mark the target. */
			if (Fighter.class.isInstance(dndClass)) {
				/* Hit or miss, I can mark the target.  For now, I'm going to assume that I want to every time.
				 * I can't think of a reason I wouldn't WANT to mark the target.
				 */
				target.markByCombatChallenge(this, DurationType.END_OF_NEXT_TURN);
				Utils.print(target.getName() + " is now marked by " + getName() + " until the end of my next turn because I have Combat Challenge.");
			}
		}

	}

	/* In this method, I will have general other bonus that are hard to define anywhere else. */
	public int getOtherAttackModifier(List<AttackTarget> targets, Encounter encounter) {
		int total = 0;

		/* See if they have Fighter Weapon Talent. */
		if (Fighter.class.isInstance(dndClass)) {

			if ((((Fighter) dndClass).getWeaponTalent() == WeaponTalent.ONE_HANDED_WEAPONS) && (getReadiedWeapon().getHandType() == WeaponHandType.ONE_HANDED)) {
				total = total + 1;
			} else if ((((Fighter) dndClass).getWeaponTalent() == WeaponTalent.TWO_HANDED_WEAPONS) && (getReadiedWeapon().getHandType() == WeaponHandType.TWO_HANDED)) {
				total = total + 1;
			}
		}

		total = total + super.getOtherAttackModifier(targets, encounter);

		return total;
	}

	public Weapon getReadiedWeapon() {
		if (readiedWeapon == null) {
			readiedWeapon = new Unarmed();
		}
		return readiedWeapon;
	}

	public Implement getReadiedImplement() {
		return readiedImplement;
	}

	public void setDndClass(DndClass dndClass) {
		this.dndClass = dndClass;
	}

	public int getWeaponProficiencyBonus() {
		Weapon weapon = getReadiedWeapon();

		// check for type or category proficiency is simple.
		if ((weaponTypeProficiencies.contains(weapon.getWeaponType())) || (weaponCategoryProficiencies.contains(weapon.getWeaponCategory()))) {
			return weapon.getProficiencyBonus();
		} else {
			// Now check for a weapon group proficiency of any of the groups.
			for (WeaponGroup group : weapon.getWeaponGroups()) {
				if (weaponGroupProficiencies.contains(group)) {
					return weapon.getProficiencyBonus();
				}
			}
			return 0;
		}
	}

	public int getImplementAttackBonus() {
		Implement implement = getReadiedImplement();

		if (implement != null) {
			return implement.getAttackBonus();
		} else {
			return 0;
		}
	}

	public int getImplementDamageBonus() {
		Implement implement = getReadiedImplement();

		if (implement != null) {
			return implement.getDamageBonus();
		} else {
			return 0;
		}
	}

	public void setTemporaryArmorClassBonus(int bonus, int bonusStartTurn,
			DurationType duration, Creature source) {
		temporaryArmorClassBonus = new TemporaryBonus();
		temporaryArmorClassBonus.setBonus(bonus);
		temporaryArmorClassBonus.setStartTurn(bonusStartTurn);
		temporaryArmorClassBonus.setDuration(duration);
		temporaryArmorClassBonus.setSource(source);
	}

	@Override
	public void initializeForEncounter() {
		super.initializeForEncounter();
		if (dndClass != null) {
			dndClass.initializeForEncounter();
		}
		if (race != null) {
			race.initializeForEncounter();
		}
		usedSecondWind = false;
	}

	@Override
	public void initializeForNewDay() {
		super.initializeForNewDay();
		if (dndClass != null) {
			dndClass.initializeForNewDay();
		}
		if (race != null) {
			race.initializeForNewDay();
		}
		this.currentSurgeUses = 0;
		actionPoints = 1;
		usedSecondWind = false;
	}

	public void setTemporaryReflexBonus(int bonus, int bonusStartTurn,
			DurationType duration, Creature source) {
		temporaryReflexBonus = new TemporaryBonus();
		temporaryReflexBonus.setBonus(bonus);
		temporaryReflexBonus.setStartTurn(bonusStartTurn);
		temporaryReflexBonus.setDuration(duration);
		temporaryReflexBonus.setSource(source);
	}

	private Shield readiedShield;
	private List<Shield> shields;

	public Shield getReadiedShield() {
		if (readiedShield == null) {
			readiedShield = new NoShield();
		}
		return readiedShield;
	}

	public void setReadiedShield(Shield readiedShield) {
		this.readiedShield = readiedShield;
	}

	public boolean isShieldReadied() {
		if ((readiedShield != null) || !(NoShield.class.isInstance(readiedShield))) {
			return true;
		} else {
			return false;
		}
	}

	public void useHealingSurge() {
		if (currentSurgeUses < this.healingSurgesPerDay) {
			heal(healingSurgeValue);
			currentSurgeUses++;
		} else {
			Utils.print("OOPS!  You can't use any more healing surges.");
		}
	}

	public void heal(int hitPoints) {
		int tempHitPoints = currentHitPoints + hitPoints;
		if (tempHitPoints >= this.maxHitPoints) {
			currentHitPoints = maxHitPoints;
			Utils.print("Fully healed.  Hit Points = " + currentHitPoints);
		} else {
			currentHitPoints = tempHitPoints;
			Utils.print("Partially healed.  Hit Points = " + currentHitPoints);
		}
	}

	@StandardAction(powerId = PowerId.SECOND_WIND, isBasicAttack = false, weaponTag = false, powerSource = PowerSource.NONE, attackType = AttackType.NONE)
	@EncounterPower
	public void secondWind(Encounter encounter) {
		if (!usedSecondWind) {
			useHealingSurge();
			usedSecondWind = true;
			temporaryArmorClassBonus = new TemporaryBonus();
			temporaryArmorClassBonus.setBonus(2);
			temporaryArmorClassBonus.setDuration(DurationType.START_OF_NEXT_TURN);
			temporaryArmorClassBonus.setSource(this);
			temporaryArmorClassBonus.setStartTurn(this.getCurrentTurn());

			temporaryWillBonus = new TemporaryBonus();
			temporaryWillBonus.setBonus(2);
			temporaryWillBonus.setDuration(DurationType.START_OF_NEXT_TURN);
			temporaryWillBonus.setSource(this);
			temporaryWillBonus.setStartTurn(this.getCurrentTurn());

			temporaryFortitudeBonus = new TemporaryBonus();
			temporaryFortitudeBonus.setBonus(2);
			temporaryFortitudeBonus.setDuration(DurationType.START_OF_NEXT_TURN);
			temporaryFortitudeBonus.setSource(this);
			temporaryFortitudeBonus.setStartTurn(this.getCurrentTurn());

			temporaryReflexBonus = new TemporaryBonus();
			temporaryReflexBonus.setBonus(2);
			temporaryReflexBonus.setDuration(DurationType.START_OF_NEXT_TURN);
			temporaryReflexBonus.setSource(this);
			temporaryReflexBonus.setStartTurn(this.getCurrentTurn());
		} else {
			Utils.print("You have already used your second wind in this encounter.  I know it would have been nice if I mentioned that already.  Sorry!");
		}
	}

	@StandardAction(powerId = PowerId.AID_ANOTHER, isBasicAttack = false, weaponTag = false, powerSource = PowerSource.NONE, attackType = AttackType.MELEE)
	@AtWillPower
	public void aidAnother(Encounter encounter) {

		Utils.print("Pick the target that you want to help your ally against.");
		AttackTarget target = encounter.chooseMeleeTarget(this, getReadiedWeapon().getNormalRange());

		if (target != null) {
			List<AttackTarget> targets = new ArrayList<AttackTarget>();
			targets.add(target);
			Dice d = new Dice(DiceType.TWENTY_SIDED);
			int diceRoll = d.attackRoll(this, target, encounter, getCurrentPosition());
			int roll = diceRoll + getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + getWeaponProficiencyBonus() + getOtherAttackModifier(targets, encounter);

			Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);

			Utils.print("The 'Aid Another' action is against a DC of 10.");

			if (roll >= 10) {
				/* A HIT! */
				Utils.print("You are successfully aiding against " + target.getName());

				TemporaryAidAnotherBonus temporaryAidAnotherBonus = new TemporaryAidAnotherBonus();
				temporaryAidAnotherBonus.setBonus(2);
				temporaryAidAnotherBonus.setStartTurn(this.getCurrentTurn());
				temporaryAidAnotherBonus.setDuration(DurationType.END_OF_NEXT_TURN);
				temporaryAidAnotherBonus.setSource(this);
				temporaryAidAnotherBonus.setTarget(target);

				Utils.print("Now choose the ally you are going to aid.");
				Creature ally = encounter.chooseAnyAlly(this);

				Utils.print("Now choose whether to add 2 to " + ally.getName() + "'s next attack (attack), or to their defenses (defense).");
				List<String> validChoices = new ArrayList<String>();
				validChoices.add("attack");
				validChoices.add("defense");
				String choice = Utils.getValidInput(validChoices);
				if ("attack".equalsIgnoreCase(choice)) {
					temporaryAidAnotherBonus.setType(TemporaryAidAnotherBonus.ATTACK);				
				} else {
					temporaryAidAnotherBonus.setType(TemporaryAidAnotherBonus.DEFENSE);				
				}
				ally.setTemporaryAidAnotherBonus(temporaryAidAnotherBonus);
			} else {
				Utils.print("You missed " + target.getName());
			}

			/* If this is a fighter, then they have "Combat Challenge", and can mark the target. */
			if (Fighter.class.isInstance(dndClass)) {
				/* Hit or miss, I can mark the target.  For now, I'm going to assume that I want to every time.
				 * I can't think of a reason I wouldn't WANT to mark the target.
				 */
				target.markByCombatChallenge(this, DurationType.END_OF_NEXT_TURN);
				Utils.print(target.getName() + " is now marked by " + getName() + " until the end of my next turn because I have Combat Challenge.");
			}
		}
	}

	public void addCoins(int i, CoinType coinType) {
		switch (coinType) {
		case COPPER_PIECE :
			coins.addCopperPieces(i);
			break;
		case SILVER_PIECE :
			coins.addSilverPieces(i);
			break;
		case GOLD_PIECE :
			coins.addGoldPieces(i);
			break;
		case PLATINUM_PIECE :
			coins.addPlatinumPieces(i);
			break;
		case ASTRAL_DIAMOND :
			coins.addAstralDiamonds(i);
			break;
		}
	}

	public Coins getCoins() {
		return coins;
	}

	public void setCoins(Coins coins) {
		this.coins = coins;
	}

	public void spendCoins(Price price) throws NotEnoughCurrencyException {
		coins.spend(price.getAmount(), price.getCoinType());
	}

	public void addArmor(Armor armor) {
		if (this.armor == null) {
			this.armor = new ArrayList<Armor>();
		}
		this.armor.add(armor);
	}

	public void addGear(Gear gear) {
		if (this.gear == null) {
			this.gear = new ArrayList<Gear>();
		}
		this.gear.add(gear);
	}

	public List<Shield> getShields() {
		return shields;
	}

	public void setShields(List<Shield> shields) {
		this.shields = shields;
	}
	
	public void addShield(Shield shield) {
		if (shields == null) {
			shields = new ArrayList<Shield>();
		}
		shields.add(shield);
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}
	
	public void addWeapon(Weapon weapon) {
		if (weapons == null) {
			weapons = new ArrayList<Weapon>();
		}
		weapons.add(weapon);
	}
}
