package com.jimmie.domain.creatures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.ImplementType;
import com.jimmie.domain.NotEnoughCurrencyException;
import com.jimmie.domain.Resistance;
import com.jimmie.domain.Ritual;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.classes.Fighter;
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
import com.jimmie.domain.items.weapons.Hand;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.domain.items.weapons.Unarmed;
import com.jimmie.domain.items.weapons.Weapon;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.domain.items.weapons.WeaponGroup;
import com.jimmie.domain.items.weapons.WeaponHandType;
import com.jimmie.domain.items.weapons.WeaponProperty;
import com.jimmie.domain.items.weapons.WeaponType;
import com.jimmie.encounters.Encounter;
import com.jimmie.powers.AidAnother;
import com.jimmie.powers.MeleeBasicAttack;
import com.jimmie.powers.SecondWind;
import com.jimmie.powers.SpendActionPoint;
import com.jimmie.util.Utils;

public abstract class DndCharacter extends Creature {
	private static final long serialVersionUID = 1L;
	private Armor readiedArmor;
	private List<Armor> armor;
	private List<Gear> gear;
	protected Coins coins;
	protected List<Ritual> rituals;	
	protected int age;
	protected Gender gender;
	protected int height;  // in inches
	protected int weight;  // in pounds
	protected Deity deity;
	protected String adventuringCompanyOrOtherAffiliations;
	protected int initiativeMisc;
	protected int armorClassMisc1;
	protected int armorClassMisc2;
	protected int fortitudeMisc1;
	protected int fortitudeMisc2;
	protected int reflexMisc1;
	protected int reflexMisc2;
	protected int willMisc1;
	protected int willMisc2;
	protected int speedMisc;
	protected int healingSurgesPerDay;
	protected int currentSurgeUses;
	protected int deathSavingThrowFailures;
	protected String deathSavingThrowMods; // Not sure what this is.
	protected List<Resistance> resistances;
	protected String additionalEffectsForSpendingActionPoints;
	protected String personalityTraits;
	protected String mannerismsAndAppearance;
	protected String background;
	protected List<CompanionOrAlly> companionsAndAllies;
	protected List<String> sessionAndCampaignNotes;
	private HashMap<Hand, ReadiedWeapon> readiedWeapons;
	private List<Weapon> weapons;
	private Implement readiedImplement;
	private List<WeaponType> weaponTypeProficiencies;
	private List<WeaponGroup> weaponGroupProficiencies;
	private List<WeaponCategory> weaponCategoryProficiencies;
	private List<ArmorType> armorTypeProficiencies;
	protected List<ArmorGroup> armorGroupProficiencies;
	protected List<ImplementType> implementProficiencies;
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
			heal(getHealingSurgeValue());
			currentSurgeUses++;
		} else {
			Utils.print("OOPS!  You can't use any more healing surges.");
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
		int total = (10+getLevel()/2 + getReadiedArmor().getBonus() + getArmorAbilityBonus()  + dndClass.getArmorClassBonus() + getReadiedShield().getBonus());
		return total;
		// TODO: Enhancement and two miscellaneous????
	}

	public int getArmorAbilityBonus() {
		// The book says (on page 212 of book 1) that light armor lets you add your intelligence or dexterity modifier to it (whichever is greater).
		if (getReadiedArmor().isLightArmor()) {
			if (getAbilityModifier(AbilityType.INTELLIGENCE) > getAbilityModifier(AbilityType.DEXTERITY)) {
				return getAbilityModifier(AbilityType.INTELLIGENCE);
			} else {
				return getAbilityModifier(AbilityType.DEXTERITY);
			}
		}
		return 0;
	}

	public int getArmorClass(Creature attacker) {
		int armorClass = getBaseArmorClass();

		/* Avenger's have "Armor of Faith". */
		if (Avenger.class.isInstance(dndClass)) {
			/* But they only get the bonus if wearing light armor AND not using a shield. */
			if (getReadiedArmor().isLightArmor() && (NoShield.class.isInstance(getReadiedShield()))) {
				armorClass = armorClass + 3;
			}
		}

		/* See if there is a temporary bonus to the armor class. */
		for (Iterator<TemporaryEffect> it = temporaryEffects.iterator(); it.hasNext();) {
			TemporaryEffect tempEffect = it.next();

			if (tempEffect.getEffectType() == TemporaryEffectType.ARMOR_CLASS_MODIFIER) {
				if (tempEffect.stillApplies()) {
					Utils.print(name + " is supposed to get a bonus to armor class until the end of " + tempEffect.getSource().getName() + "'s next turn.");
					armorClass = armorClass + tempEffect.getModifier();
					Utils.print("Armor Class Modifier still applies.");
				} else {
					/* Bonus is over.  Reset the bonus. */
					it.remove();
					Utils.print("Armor Class Modifier no longer applies.  Removint.");
				}
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
//	public void setFortitudeMisc1(int fortitudeMisc1) {
//		this.fortitudeMisc1 = fortitudeMisc1;
//	}
	public int getFortitudeMisc2() {
		return fortitudeMisc2;
	}
//	public void setFortitudeMisc2(int fortitudeMisc2) {
//		this.fortitudeMisc2 = fortitudeMisc2;
//	}
	public int getReflexMisc1() {
		return reflexMisc1;
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
		return maxHitPoints / 4;
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

	public Deity getDeity() {
		return deity;
	}

	public void setDeity(Deity deity) {
		this.deity = deity;
	}

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

	public List<ImplementType> getImplementProficiencies() {
		return implementProficiencies;
	}

	public void setImplementProficiencies(List<ImplementType> implementProficiencies) {
		this.implementProficiencies = implementProficiencies;
	}

	public void addReadiedWeapon(ReadiedWeapon readiedWeapon) {
		// See if they are trying to put a weapon WITHOUT the off-hand keyword into their off hand.
		if ((readiedWeapon.getHand() == Hand.OFF_HAND) && !(readiedWeapon.getWeapon().getWeaponProperties().contains(WeaponProperty.OFF_HAND))) {
			Utils.print("Sorry.  You are trying to use a weapon in your off hand that CAN NOT be used as an off hand weapon.");
			// TODO: Some feat might allow this.
		}
		
		if (readiedWeapons == null) {
			readiedWeapons = new HashMap<Hand, ReadiedWeapon>();
		}
		// See if they are trying to wield a two-handed weapon.
		if (readiedWeapon.getWeapon().getHandType() == WeaponHandType.TWO_HANDED) {
			// Remove all weapons first.
			readiedWeapons = new HashMap<Hand, ReadiedWeapon>();
			// Since it's two handed, make sure the hand is set to both (defensive programming).
			readiedWeapon.setHand(Hand.BOTH_HANDS);
			readiedWeapons.put(readiedWeapon.getHand(), readiedWeapon);
			return;
		}
		// See if they already have a weapon in that hand and remove it.
		if (readiedWeapons.containsKey(readiedWeapon.getHand())) {
			readiedWeapons.remove(readiedWeapon.getHand());
		}
		readiedWeapons.put(readiedWeapon.getHand(), readiedWeapon);
		
	}
	@Override
	public int getInitiative() {
		return getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY) + getInitiativeMisc();
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
	
	public void addImplementProficiency(ImplementType implement) {
		if (implementProficiencies == null) {
			implementProficiencies = new ArrayList<ImplementType>();
		}
		implementProficiencies.add(implement);
	}

	public DndCharacter() {
		super();
		weaponTypeProficiencies = new ArrayList<WeaponType>();
		weaponGroupProficiencies = new ArrayList<WeaponGroup>();
		weaponCategoryProficiencies = new ArrayList<WeaponCategory>();
		armorTypeProficiencies = new ArrayList<ArmorType>();
		armorGroupProficiencies = new ArrayList<ArmorGroup>();
		implementProficiencies = new ArrayList<ImplementType>();
		addPower(new MeleeBasicAttack());
		addPower(new SpendActionPoint());
		addPower(new SecondWind());
		addPower(new AidAnother());

		coins = new Coins();
		age = 0;
		height = 0;  // in inches
		weight = 0;  // in pounds
		initiativeMisc = 0;
		armorClassMisc1 = 0;
		armorClassMisc2 = 0;
		fortitudeMisc1 = 0;
		fortitudeMisc2 = 0;
		reflexMisc1 = 0;
		reflexMisc2 = 0;
		willMisc1 = 0;
		willMisc2 = 0;
		speedMisc = 0;
		healingSurgesPerDay = 0;
		currentSurgeUses = 0;
		deathSavingThrowFailures = 0;
}

	/* In this method, I will have general other bonus that are hard to define anywhere else. */
	public int getOtherAttackModifier(List<AttackTarget> targets, Encounter encounter) {
		int total = 0;

		/* See if they have Fighter Weapon Talent. */
		if (Fighter.class.isInstance(dndClass)) {

			if ((((Fighter) dndClass).getWeaponTalent() == WeaponTalent.ONE_HANDED_WEAPONS) && (getReadiedWeapon().getWeapon().getHandType() == WeaponHandType.ONE_HANDED)) {
				total = total + 1;
			} else if ((((Fighter) dndClass).getWeaponTalent() == WeaponTalent.TWO_HANDED_WEAPONS) && (getReadiedWeapon().getWeapon().getHandType() == WeaponHandType.TWO_HANDED)) {
				total = total + 1;
			}
		}
		
		// TODO: I think this is probably the best place to put a check for the off hand penalty.

		total = total + super.getOtherAttackModifier(targets, encounter);

		return total;
	}

	@Override
	public ReadiedWeapon getReadiedWeapon() {		
		if (readiedWeapons == null) {
			Weapon weapon = new Unarmed();
			ReadiedWeapon readiedWeapon = new ReadiedWeapon();
			readiedWeapon.setHand(Hand.MAIN_HAND);
			readiedWeapon.setWeapon(weapon);
			addReadiedWeapon(readiedWeapon);
		}
		
		if (readiedWeapons.size() > 1) {
			Utils.print(getName() + " is carrying more than one weapon.  Choose which weapon to use.");
			Utils.print("1. " + readiedWeapons.get(Hand.MAIN_HAND).getWeapon().getName() + " in the main hand.");
			Utils.print("2. " + readiedWeapons.get(Hand.OFF_HAND).getWeapon().getName() + " in the off hand.");
			Utils.print("Your choice:");
			int choice = Utils.getValidIntInputInRange(1, 2);
			if (choice == 1) {
				return readiedWeapons.get(Hand.MAIN_HAND);
			} else {
				return readiedWeapons.get(Hand.OFF_HAND);
			}
		}
		
		if (readiedWeapons.containsKey(Hand.BOTH_HANDS)) {
			return readiedWeapons.get(Hand.BOTH_HANDS);
		}
		
		// NOTE: This assumes that no one would every be using a weapon ONLY in thier off hand.  That person should die anyway.  :-)
		return readiedWeapons.get(Hand.MAIN_HAND);
	}

	@Override
	public HashMap<Hand,ReadiedWeapon> getReadiedWeapons() {
		if (readiedWeapons == null) {
			Weapon weapon = new Unarmed();
			ReadiedWeapon readiedWeapon = new ReadiedWeapon();
			readiedWeapon.setHand(Hand.MAIN_HAND);
			readiedWeapon.setWeapon(weapon);
			addReadiedWeapon(readiedWeapon);			
		}
		return readiedWeapons;
	}
	
	public void setReadiedImplement(Implement readiedImplement) {
		this.readiedImplement = readiedImplement;
	}

	public Implement getReadiedImplement() {
		return readiedImplement;
	}

	public void setDndClass(DndClass dndClass) {
		this.dndClass = dndClass;
	}

	@Override
	public int getWeaponProficiencyBonus() {
		Weapon weapon = getReadiedWeapon().getWeapon();

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

	@Override
	public int getImplementAttackBonus() {
		Implement implement = getReadiedImplement();

		if (implement != null) {
			return implement.getAttackBonus();
		} else {
			return 0;
		}
	}

	@Override
	public int getImplementDamageBonus() {
		Implement implement = getReadiedImplement();
		
		if (implement != null) {
			if (!getImplementProficiencies().contains(implement.getImplementType())) {
				return 0;
			}
			return implement.getDamageBonus();
		} else {
			return 0;
		}
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

}
