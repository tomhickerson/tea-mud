package com.ivstuart.tmud.spells;

import com.ivstuart.tmud.person.movement.MoveManager;
import com.ivstuart.tmud.state.Mob;
import com.ivstuart.tmud.state.Room;
import com.ivstuart.tmud.state.Spell;
import com.ivstuart.tmud.state.World;

/**
 * Created by Ivan on 09/08/2016.
 */
public class Summon implements SpellEffect {

    public void effect(Mob caster, Mob target, Spell spell) {

        if (caster.getFight().isEngaged()) {
            caster.out("You summon fizzles uselessly can not be used in combat");
            return;
        }

        // check same alignment
        if (caster.isGood() != target.isGood()) {
            caster.out("You may only summon players of your own alignment");
            return;
        }


        Room toRoom = caster.getRoom();

        if (toRoom != null) {
            caster.out("You summon to " + toRoom.getId());
            MoveManager.move(target,toRoom);
        } else {
            target.out("Room not found!");
        }
    }

    public boolean isPositiveEffect() {
        return true;
    }
}
