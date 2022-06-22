package me.cketernity.satisfactoryplanner;

import me.cketernity.satisfactoryplanner.items.FactoryManager;
import me.cketernity.satisfactoryplanner.items.ItemManager;

public class Main {
	public static void main(String[] args) {
		FactoryManager.getFactoryManager().getFactory(ItemManager.getItemManager().getItem("Smart Plating"));
		FactoryManager.getFactoryManager().printFactory("Smart Plating");
	}
}
