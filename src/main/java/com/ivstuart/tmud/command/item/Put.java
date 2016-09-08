/*
 * Copyright (c) 2016. Ivan Stuart
 *  All Rights Reserved
 */

/*
 * Created on 23-Sep-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.ivstuart.tmud.command.item;

import com.ivstuart.tmud.command.BaseCommand;
import com.ivstuart.tmud.constants.DoorState;
import com.ivstuart.tmud.state.Bag;
import com.ivstuart.tmud.state.Chest;
import com.ivstuart.tmud.state.Item;
import com.ivstuart.tmud.state.Mob;
import static com.ivstuart.tmud.utils.StringUtil.*;

/**
 * The following put modes are supported by the code below:
 * 
 * 1) put <object> <container> 2) put all.<object> <container> 3) put all
 * <container>
 * 
 * <container> must be in inventory or on ground. all objects to be put into
 * container must be in inventory.
 */
public class Put extends BaseCommand {


	@Override
	public void execute(Mob mob, String input) {

		String bagString = getLastWord(input);

		Item bag = mob.getInventory().get(bagString);

		if (bag == null) {
			bag = mob.getRoom().getInventory().get(bagString);
		}

		if (bag == null) {
			mob.out("There is no bag to put stuff into");
			return;
		}

		if (!bag.isContainer()) {
			mob.out("You can not put things into "+bag.getName());
			return;
		}


		String target = getFirstWord(input);

		Item anItem = mob.getInventory().get(target);

		if (anItem == null) {
			mob.out("Can not put " + target + " it is not here!");
			return;
		}

		if (anItem == bag) {
			mob.out("You can not put something into itself");
			return;
		}

		Bag aBag = (Bag)bag;

        if (bag instanceof Chest) {
            if (((Chest) bag).getState() != DoorState.OPEN) {
                mob.out("That item is not open");
                return;
            }
        }

		aBag.getInventory().add(anItem);
		mob.getInventory().remove(anItem);

		mob.out("You put an "+anItem.getName()+" into a "+bag.getName());


	}



}
