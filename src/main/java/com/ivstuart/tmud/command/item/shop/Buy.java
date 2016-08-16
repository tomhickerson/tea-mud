/*
 * Created on 28-Sep-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.ivstuart.tmud.command.item.shop;

import com.ivstuart.tmud.command.BaseCommand;
import com.ivstuart.tmud.command.Command;
import com.ivstuart.tmud.person.carried.SomeMoney;
import com.ivstuart.tmud.state.Item;
import com.ivstuart.tmud.state.Mob;

/**
 * @author stuarti
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Buy extends BaseCommand {

	@Override
	public void execute(Mob mob, String input) {

		Mob shopKeeper = mob.getRoom().getShopKeeper();

		if (shopKeeper == null) {
			mob.out("There is no shop here to buy and sell from");
			return;
		}

		// buy 1
		// buy 1.sword
		// buy sword
		// buy all - surely not

		Item item = shopKeeper.getInventory().get(input);

		if (item == null) {
			mob.out("There is no item "+input+" to buy");
			return;
		}

		// SomeMoney
		SomeMoney cost = item.getCost();

		if (!mob.getInventory().getPurse().remove(cost)) {
			mob.out("You can not afford "+cost+" of item");
			return;
		}

		shopKeeper.getInventory().remove(item);
		shopKeeper.getInventory().add(cost);

		mob.getInventory().add(item);

		mob.out("You buy a "+item.getName());

	}

}
