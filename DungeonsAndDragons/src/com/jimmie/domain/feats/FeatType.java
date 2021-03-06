package com.jimmie.domain.feats;

public enum FeatType {
	// Feats from the first book:
	ACTION_SURGE, AGILE_HUNTER, ALERTNESS, ARMOR_OF_BAHAMUT, ARMOR_PROFICIENCY_CHAINMAIL, ARMOR_PROFICIENCY_HIDE, ARMOR_PROFICIENCY_LEATHER, 
	ARMOR_PROFICIENCY_PLATE, ARMOR_PROFICIENCY_SCALE,
	ASTRAL_FIRE, AVANDRAS_RESCUE, BACKSTABBER, BLADE_OPPORTUNIST, BURNING_BLIZZARD, COMBAT_REFLEXES, CORELLONS_GRACE, DARK_FURY, DEFENSIVE_MOBILITY,
	DISTRACTING_SHIELD, DODGE_GIANTS, DRAGONBORN_FRENZY, DRAGONBORN_SENSES, DURABLE, DWARVEN_WEAPON_TRAINING, ELADRIN_SOLDIER, ELVEN_PRECISION,
	ENLARGED_DRAGON_BREATH, ESCAPE_ARTIST, EXPANDED_SPELLBOOK, FAR_SHOT, FAR_THROW, FAST_RUNNER, FEROCIOUS_REBUKE, GROUP_INSIGHT, HALFLING_AGILITY,
	HARMONY_OF_ERATHIS, HEALING_HANDS, HELLFIRE_BLOOD, HUMAN_PERSEVERANCE, IMPROVED_DARK_ONES_BLESSING, IMPROVED_FATE_OF_THE_VOID, IMPROVED_INITIATIVE,
	IMPROVED_MISTY_STEP, INSPIRED_RECOVERY, IOUNS_POISE, JACK_OF_ALL_TRADES, KORDS_FAVOR, LETHAL_HUNTER, LIGHT_STEP, LINGUIST, LONG_JUMPER,
	LOST_IN_THE_CROWD, MELORAS_TIDE, MORADINS_RESOLVE, MOUNTED_COMBAT, NIMBLE_BLADE, PELORS_RADIANCE, POTENT_CHALLENGE, POWER_ATTACK, POWERFUL_CHARGE,
	PRECISE_HUNTER, PRESS_THE_ADVANTAGE, QUICK_DRAW, RAGING_STORM, RAVEN_QUEENS_BLESSING, RITUAL_CASTER, SEHANINES_REVERSAL, SHIELD_PROFICIENCY_HEAVY,
	SHIELD_PROFICIENCY_LIGHT, SHIELD_PUSH, SKILL_FOCUS,	SKILL_TRAINING, SURE_CLIMBER, SURPRISE_KNOCKDOWN, TACTICAL_ASSAULT, TOUGHNESS,
	TWO_WEAPON_DEFENSE,	TWO_WEAPON_FIGHTING, WEAPON_FOCUS, WEAPON_PROFICIENCY, WINTERTOUCHED,
	
	// Feats from the second book:
	ANGER_UNLEASHED, AUSPICIOUS_LINEAGE, BLURRING_CLAWS, COMBAT_MEDIC, COORDINATED_EXPLOSION, DISTANT_ADVANTAGE, ECHOES_OF_THUNDER, EXPERT_RITUALIST,
	FEY_TRICKSTER, GOLIATH_GREATWEAPON_PROWESS, GOREBRUTE_CHARGE, GROUP_STEALTH, IMPLEMENT_EXPERTISE, IMPROVED_BULL_RUSH, IMPROVED_GRAB,
	MARKINGS_OF_THE_BLESSED, MARKINGS_OF_THE_VICTOR, MELEE_TRAINING, ONCOMING_STORM, POTENT_REBIRTH, RADIANT_POWER, RESTFUL_HEALING, SAVAGE_ASSAULT,
	SHADOW_SKULK, SPEED_LOADER, SURGING_FLAME, THIRST_FOR_BATTLE, TIMELY_RESPITE, TWO_WEAPON_THREAT, WEAPON_EXPERTISE, WILD_SENSES,
	GUARANTEED_RETRIBUTION, IMPROVED_ARMOR_OF_FAITH, INVIGORATING_PURSUIT, DEADLY_RAGE, IMPROVED_RAGEBLOOD_VIGOR, IMPROVED_ROAR_OF_TRIUMPH,
	RISING_FURY, ADVANTAGE_OF_CUNNING, BARDIC_KNOWLEDGE, IMPROVED_MAJESTIC_WORD, STRENGTH_OF_VALOR, ENRAGED_BOAR_FORM, FEROCIOUS_TIGER_FORM,
	PRIMAL_FURY, PRIMAL_INSTINCT, INSIGHTFUL_PRESERVATION, INVOKER_DEFENSE, RESONATING_COVENANT, SCOURING_WRATH, PROTECTOR_SPIRIT_ADEPT,
	SHARED_HEALING_SPIRIT, SPIRIT_SPEAKER, STALKER_SPIRIT_ADEPT, ARCANE_SPELLFURY, DISCIPLINED_WILD_SOUL, IMPROVED_DRAGON_SOUL,
	SORCEROUS_BLADE_CHANNELING, CRUSHING_EARTHSTRENGTH, REVITALIZING_FONT_OF_LIFE, SUDDEN_ROOTS, WILDBLOOD_CUNNING,

	// Feats from the third book:
	ALHAHNS_MINDFUL_RELOCATION, BATTLE_HARDENED, BATTLE_CASTER_DEFENSE, BATTERING_SHIELD, BEGUILING_ENCHANTMENT, BLOODIED_FEROCITY, BRUTAL_FEROCITY,
	BURDEN_OF_GUARDIANSHIP, BURDEN_OF_REJUVENATION, CASCADING_RUSH, CLEANSE_THE_MADNESS, COMBAT_INTUITION, DAKSHAIS_BODY_MIND_UNION,
	DEADLY_DRAW, DIRECTED_BULL_RUSH, FOCUSED_MIND, GORING_SHOVE, GROUNDING_SHOT, HAFTED_DEFENSE, HAMMERING_IRON, HEADSMANS_CHOP, HYBRID_TALENT,
	IMPENDING_VCTORY, IMPROVED_ASPECT_OF_NATURE, IRON_RESOLVE_OF_ZERTHADLUN, LIBERATING_SHARD_SWARM, LOW_CRAWL, LUCKY_START, MIRYATHS_FIRST_STRIKE,
	NIMBLE_RUNNER, OPPORTUNISTIC_SHOVE, POLEARM_FLANKER, POWER_THROW, PRIME_BURST, PSYCHIC_FOCUS, RAPID_ASSAULT, SECRET_OF_REAWAKENING, SHIFTING_DEFENSE,
	SKILL_POWER, SPRING_STEP, SPRINGING_CHARGE, STEADY_FEET, STRIKE_AND_SHOVE, SUPERIOR_IMPLEMENT_TRAINING, TEAMWORK_DEFENSE, UNARMORED_AGILITY,
	UNFAILING_VIGOR, VERSATILE_EXPERTISE, VICIOUS_ADVANTAGE, WARDING_DEFENSE, WARDING_SHARD_SWARM, WATCHFUL_REDOUBT, ZUWOTHS_ENLIGHTENED_STEP,
	BOLSTERING_MANTLE, CLARIFIED_INSTINCTS, ELATED_EMOTIONS, HEARTENING_SURGE, MANTLE_OF_READINESS, DECEPTIVE_MIND, IMPROVED_SPEED_OF_THOUGHT,
	LURE_OF_IRON, PUNISHING_SPIKE, PURSUING_STEP, CRASHING_TEMPEST_STYLE, IMPROVED_MONK_UNARMED_STRIKE, POINTED_STEP_STYLE, CONTROLLING_ADVANTAGE,
	DISCIPLINE_ADEPT, EXCHANGE_POWER, ORBITING_OBJECT, PRECISE_MIND, RUNE_OF_ELOQUENCE, RUNE_OF_HOPE,RUNE_OF_VENGEANCE, RUNE_OF_ZEAL, BLOODIED_ELUSION,
	IMPROVED_INEVITABLE_SHOT, INESCAPABLE_SHOT, SPIRITBOND_DEFENSE, STRENGTHENED_BOND,
	
	// PARAGON FEATS FROM FIRST BOOK:
	ACTION_RECOVERY, AGILE_ATHLETE, ARCANE_REACH, ARMOR_SPECIALIZATION_CHAINMAIL, ARMOR_SPECIALIZATION_HIDE, ARMOR_SPECIALIZATION_PLATE,
	ARMOR_SPECIALIZATION_SCALE, BACK_TO_THE_WALL, BLOOD_THIRST, COMBAT_ANTICIPATION, COMBAT_COMMANDER, DANGER_SENSE, DEADLY_AXE, DEFENSIVE_ADVANTAGE,
	DEVASTATING_CRITICAL, DISTANT_SHOT, DWARVEN_DURABILITY, EMPOWERED_DRAGON_BREATH, EVASION, FEYWILD_PROTECTION, FIERY_REBUKE, FLEET_FOOTED,
	GREAT_FORTITUDE, HAMMER_RHYTHM, HEAVY_BLADE_OPPORTUNITY, IMPROVED_SECOND_WIND, INESCAPABLE_FORCE, 
	
	
	// Rune feats (mix in with others later)
	RUNE_OF_ESCAPE, RUNE_OF_TORMENT, RUNE_OF_BATTLE, RUNE_OF_HEALTH,
	
	// Arcane Powers
	RISING_SPELLFURY,

	// Divine Powers
	DEVOTED_PALADIN,

	// Primal Powers
	LIFESPIRIT_HEALING
	
}
