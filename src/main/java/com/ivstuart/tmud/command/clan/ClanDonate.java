/*
 * Copyright (c) 2016. Ivan Stuart
 *  All Rights Reserved
 */

package com.ivstuart.tmud.command.clan;

import com.ivstuart.tmud.command.BaseCommand;
import com.ivstuart.tmud.command.item.Donate;
import com.ivstuart.tmud.person.ClanMembership;
import com.ivstuart.tmud.state.Mob;
import com.ivstuart.tmud.state.Room;
import com.ivstuart.tmud.world.Clans;
import com.ivstuart.tmud.world.World;

/**
 * @author stuarti
 *         <p>
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ClanDonate extends BaseCommand {

    /**
     *
     */
    public ClanDonate() {
        super();
    }

    /*
     * (non-Javadoc)
     *
     * @see command.Command#execute(java.lang.String)
     */
    @Override
    public void execute(Mob mob, String input) {
        ClanMembership clanMembership = mob.getPlayer().getClanMembership();

        if (clanMembership == null) {
            mob.out("You are not in a clan");
            return;
        }

        Room donateRoom = Clans.getClan(clanMembership.getClanId()).getDonateRoom();

        // Default back to usual donation room
        if (donateRoom == null) {
            donateRoom = World.getDonateRoom(mob);
        }

        new Donate().execute(mob, input, donateRoom);

    }

}
