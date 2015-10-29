package com.jimmie.util;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StandardAction {
	String menuName();
	boolean isMeleeAttack();
	boolean isRangedAttack();
	boolean isBasicAttack();
	boolean martialTag();
	boolean weaponTag();
	boolean divineTag();
	boolean primalTag();
	boolean arcaneTag();
	boolean psionicTag();

}
