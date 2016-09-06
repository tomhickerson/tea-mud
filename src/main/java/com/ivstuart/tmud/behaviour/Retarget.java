/*
 * Copyright (c) 2016. Ivan Stuart
 *  All Rights Reserved
 */

package com.ivstuart.tmud.behaviour;

import com.ivstuart.tmud.common.DiceRoll;
import com.ivstuart.tmud.state.Mob;
import com.ivstuart.tmud.state.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Retarget extends BaseBehaviour {

    private static Logger LOGGER = LogManager.getLogger();


    public Retarget() {
        parameter = 50; //aggro precentage
    }

    @Override
    public String getId() {
        return mob.getId();
    }

    @Override
    public boolean tick() {
        // If any players visible in same location then random pick one to attack.
        if (!mob.getFight().isFighting()) {
            LOGGER.debug(mob.getName() + " is not fighting so will not retarget");
            return false;
        }

        if (DiceRoll.ONE_D100.rollMoreThan(parameter)) {
            LOGGER.debug(mob.getName()+" is does not feel like retargetting");
            return false;
        }

        Room room = mob.getRoom();

        if (room == null) {
            LOGGER.warn(mob.getName() + " has no room");
            return false;
        }

        Mob target = room.getRandomPlayer();

        if (target == null) {
            LOGGER.debug(mob.getName() + " has no players to attack");
            return false;
        }

        if (target.getFight().getTarget() != mob) {
            LOGGER.debug(mob.getName() + " is not being attached by that player");
            return false;
        }

        mob.getFight().getMelee().setTarget(target);

        return false;
    }

}
