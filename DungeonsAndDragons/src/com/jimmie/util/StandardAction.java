package com.jimmie.util;

import java.lang.annotation.*;
import com.jimmie.domain.PowerId;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.AttackType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StandardAction{
	PowerId powerId();
	AttackType attackType();
	boolean isBasicAttack();
	boolean weaponTag();
	PowerSource powerSource();
}
