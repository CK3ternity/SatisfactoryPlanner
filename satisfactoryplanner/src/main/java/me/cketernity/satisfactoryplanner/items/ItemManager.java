package me.cketernity.satisfactoryplanner.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemManager {
	private static ItemManager itemManager = new ItemManager(ListManager.getListHandler());
	private static List<Item> items;
	private static Scanner scan;
	
	private ItemManager(ListManager manager) { init(manager); }
	
	private void init(ListManager handler) {
		items = handler.getItems();
		scan = new Scanner(System.in);
	}
	
	public Item askUserForNewItem(String name) {
		System.out.println("Item: " + name + "\n");
		System.out.print("How many are made per minute? ");
		int itemsMade = Integer.parseInt(scan.nextLine());
		
		Item newItem = new Item(name, itemsMade);
		
		System.out.println("Enter \"Done\" when finished");
		List<CraftingItem> cItems = new ArrayList<>();
		boolean stop = false;
		do {
			System.out.print("Crafting material name: ");
			String cName = scan.nextLine();
			
			if(cName.equalsIgnoreCase("Done")) {
				stop = true;
				break;
			}
			
			System.out.print("How many are needed per minute? ");
			int itemsNeeded = Integer.parseInt(scan.nextLine());
			
			cItems.add(new CraftingItem(cName, itemsNeeded));
		} while(!stop);
		
		scan.reset();
		newItem.setItems(cItems);
		
		items.add(newItem);
		ListManager.getListHandler().addListToFile();
		return newItem;
	}
	
	// TODO: Replace return null with a return call to a listHandler method which adds a new item
	@SuppressWarnings("unlikely-arg-type")
	public Item getItem(CraftingItem cItem) {
		for(Item item: items) {
			if(item.equals(cItem))
				return item;
		}
		
		return askUserForNewItem(cItem.getName());
	}
	public Item getItem(String name) {
		for(Item item: items) {
			if(item.getName().equals(name))
				return item;
		}
		
		return askUserForNewItem(name);
	}
	
	public List<Item> get() { return items; }
	public Item get(int index) { return items.get(index); }
	public static ItemManager getItemManager() { return itemManager; }
}
