/*
 * Created on 28-Sep-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.ivstuart.tmud.command.misc;

import com.ivstuart.tmud.command.Command;
import com.ivstuart.tmud.state.Item;
import com.ivstuart.tmud.state.Mob;

/**
 * @author stuarti
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Death implements Command {

	@Override
	public void execute(Mob mob, String input) {

		Item corpse = new Item();

		corpse.setLook(mob.getId() + "'s corpse.");

		mob.getRoom().add(corpse);

		mob.out("Testing corpse thing");
	}

}
