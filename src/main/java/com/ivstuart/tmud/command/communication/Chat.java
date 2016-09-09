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
package com.ivstuart.tmud.command.communication;

import com.ivstuart.tmud.command.BaseCommand;
import com.ivstuart.tmud.person.config.ChannelData;
import com.ivstuart.tmud.state.Mob;
import com.ivstuart.tmud.world.Channel;
import com.ivstuart.tmud.world.ChannelHistory;
import com.ivstuart.tmud.world.World;

/**
 * @author stuarti
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Chat extends BaseCommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#execute(java.lang.String)
	 */
	@Override
	public void execute(Mob mob, String input) {
		Channel c = ChannelHistory.getInstance().getChat();

		if (input.length() > 0) {

			c.add("$B(" + mob.getId() + ") " + input + "$J", mob.isGood());
			World.out(input, mob.isGood(), ChannelData.CHAT);
		} else {
			mob.out("$H------------( Chat History  )------------$J");
			mob.out(c.toString(mob.isGood()));
		}
	}

}
