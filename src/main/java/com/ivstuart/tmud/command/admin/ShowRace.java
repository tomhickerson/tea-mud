package com.ivstuart.tmud.command.admin;

import com.ivstuart.tmud.state.Mob;
import com.ivstuart.tmud.state.World;

public class ShowRace extends AdminCommand {

    @Override
    public void execute(Mob mob, String input) {

        super.execute(mob, input);

        mob.out("Showing race by id");

        mob.out("Race = " + World.getInstance().getRace(Integer.parseInt(input)));

    }
}