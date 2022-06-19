package me.cketernity.satisfactoryplanner.items;

public class CraftingItem {
	private String name;
	private int itemsNeeded;
	
	public CraftingItem(String name, int itemsNeeded) {
		this.name = name;
		this.itemsNeeded = itemsNeeded;
	}

	public String getName() { return name; }
	public int getItemsNeeded() { return itemsNeeded; }
	
	@Override
	public String toString() {
		String str = "";
		
		str += this.getItemsNeeded() + " " + this.getName() + "(s) per Minute\n";
		
		return str;
	}
	
	@Override
	@SuppressWarnings("unlikely-arg-type")
	public boolean equals(Object other) {
		if(other == null)
			return false;
		
		if(other instanceof Item)
			return ((Item) other).equals(this);
		
		if(!(other instanceof CraftingItem))
			return false;
		
		CraftingItem otherItem = (CraftingItem) other;
		if(!this.getName().equals(otherItem.getName()))
			return false;
		
		if(this.getItemsNeeded() != otherItem.getItemsNeeded())
			return false;
		
		return true;
	}
}