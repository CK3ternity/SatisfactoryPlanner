package me.cketernity.satisfactoryplanner.items;

public class Factory {
	private static ItemManager itemManager;
	
	private Item targetItem;
	private int numProducers;
	private Factory[] subfactories;
	
	public Factory(Item targetItem) {
		if(itemManager == null)
			itemManager = ItemManager.getItemManager();
		
		System.out.println("Creating initial factory for " + targetItem.getName() + "...");
		
		this.targetItem = targetItem;
		this.numProducers = 1;
		this.subfactories = new Factory[targetItem.getCraftingItems().size()];
		
		for(int i = 0; i < subfactories.length; i++) {
			CraftingItem cItem = targetItem.getCraftingItems().get(i);
			subfactories[i] = new Factory(itemManager.getItem(cItem), cItem.getItemsNeeded() * numProducers);
		}
	}
	private Factory(Item targetItem, int needed) {
		System.out.println("Creating factory for " + targetItem.getName() + "...");
		
		this.targetItem = targetItem;
		this.numProducers = 1;
		this.subfactories = new Factory[targetItem.getCraftingItems().size()];
		
		while(targetItem.getItemsPerMinute() * numProducers < needed) {
			numProducers++;
		}
		
		for(int i = 0; i < subfactories.length; i++) {
			CraftingItem cItem = targetItem.getCraftingItems().get(i);
			subfactories[i] = new Factory(itemManager.getItem(cItem), cItem.getItemsNeeded() * numProducers);
		}
		
	}
	
	public Item getTargetItem() { return targetItem; }
	public int getNumProducers() { return numProducers; }
	public Factory[] getSubfactories() { return subfactories; }
	
	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		
		if(!(other instanceof Factory))
			return false;
		
		Factory otherFactory = (Factory) other;
		
		if(!this.getTargetItem().equals(otherFactory.getTargetItem()))
			return false;
		
		if(this.getNumProducers() != otherFactory.getNumProducers())
			return false;
		
		for(int i = 0; i < this.getSubfactories().length; i++) {
			if(!this.getSubfactories()[i].equals(otherFactory.getSubfactories()[i]))
				return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return recursiveToString("", this.getTargetItem(), 0);
	}
	private String recursiveToString(String str, Item current, int i) {
		System.out.println("\n" + current.getName());
		str += addIndents(i) + current.toString();
		str += addCraftingItemList(i, current);
		
		for(int count = 0; count < current.getCraftingItems().size(); count++) {
			str += recursiveToString(str, itemManager.getItem(current.getCraftingItems().get(count)), i+1);
		}
		
		return str;
	}
	private String addIndents(int i) {
		String str = "";
		for(int count = 0; count < i; count++) {
			str += "-";
		}
		
		return str;
	}
	private String addCraftingItemList(int i, Item current) {
		String str = "";
		
		for(int count = 0; count < current.getCraftingItems().size(); count++) {
			addIndents(i);
			str += current.getCraftingItems().toString() + "\n";
		}
		
		return str;
	}
}
