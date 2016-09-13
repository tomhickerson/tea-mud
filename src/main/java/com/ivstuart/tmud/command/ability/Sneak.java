/*
 * Copyright (c) 2016. Ivan Stuart
 *  All Rights Reserved
 */

/*
 * Created on 22-Sep-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.ivstuart.tmud.command.ability;

import com.ivstuart.tmud.command.BaseCommand;
import com.ivstuart.tmud.common.Msg;
import com.ivstuart.tmud.state.Ability;
import com.ivstuart.tmud.state.Mob;

import static com.ivstuart.tmud.constants.SkillNames.SNEAK;

/**
 * @author stuarti
 * 
 */
public class Sneak extends BaseCommand {

	/**
	 * Move without alerting others to your presence as you enter in the same
	 * room as them
	 */
	@Override
	public void execute(Mob mob, String input) {

        Ability sneak = mob.getLearned().getAbility(SNEAK);

        if (sneak == null || sneak.isNull()) {
            mob.out("You have no knowledge of sneak");
			return;
		}

		if (mob.isSneaking()) {
			mob.out("You are already sneaking around");
		}

        if (sneak.isSuccessful(mob)) {
            mob.out(new Msg(mob, "<S-You/NAME> successfully start to sneak"));

			mob.getMobStatus().setSneaking(60);// 1 minute of sneaking around.

		}
	}

}
