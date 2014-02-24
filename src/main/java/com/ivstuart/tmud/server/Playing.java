/*
 * Created on 17-Sep-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.ivstuart.tmud.server;

import org.apache.log4j.Logger;

import com.ivstuart.tmud.command.CommandProvider;
import com.ivstuart.tmud.person.Player;
import com.ivstuart.tmud.state.Mob;

/**
 * @author stuarti
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Playing implements Readable {

	private static final Logger LOGGER = Logger.getLogger(Playing.class);

	private Player player;
	private Mob mob;
	
	/**
	 * 
	 */
	public Playing(Player player) {
		this.player = player;
		mob = player.getMob();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.Read#read(java.lang.String)
	 */
	@Override
	public void read(String line) {
		
		if (player.getSnooper() != null) {
			player.getSnooper().out("You snoop:"+line);
		}
		
		String[] input = line.split(" ", 2);
		String parameters = "";

		// This is required as some commands act on their input
		if (input.length == 2) {
			parameters = input[1];
		}
		
		if (mob.getMobStatus().isFrozen()) {
			mob.out("You can only quit while frozen");
			if (!input[0].equals("quit")) {
				return;
			}
		}

		try {
			CommandProvider.getCommandByString(input[0]).execute(mob,
					parameters);
		} catch (Exception e) {
			LOGGER.error("Problem sourcing command for [ " + input[0] + " ]", e);
			mob.out(e.getMessage());
		}

	}
}
