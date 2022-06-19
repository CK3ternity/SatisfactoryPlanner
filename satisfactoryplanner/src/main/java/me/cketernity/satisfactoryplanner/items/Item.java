package me.cketernity.satisfactoryplanner.items;

import java.util.ArrayList;
import java.util.List;

public class Item {
	private String name;
	private int itemsMade;
	private List<CraftingItem> items = new ArrayList<>();
	
	public Item(String name, int itemsMade) {
		this.name = name;
		this.itemsMade = itemsMade;
	}
	
	public void addItem(CraftingItem i) {
		this.items.add(i);
	}
	
	public String getName() { return name; }
	public int getItemsPerMinute() { return itemsMade; }
	public List<CraftingItem> getCraftingItems() { return items; }
	public void setItems(List<CraftingItem> items) { this.items = items; }
	
	@Override
	public String toString() {
		String str = "";
		
		str += this.getItemsPerMinute() + " " + this.getName() + " per Minute\n";
		for(CraftingItem item: this.getCraftingItems()) {
			str += "\t" + item.toString();
		}
		
		return str;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		
		if(!(other instanceof Item || other instanceof CraftingItem))
			return false;
		
		if(other instanceof CraftingItem) {
			CraftingItem otherCI = (CraftingItem) other;
			
			if(!this.getName().equals(otherCI.getName()))
				return false;
			
			return true;
		}
		
		Item otherItem = (Item) other;
		
		if(!this.getName().equals(otherItem.getName()))
			return false;
		
		if(this.getItemsPerMinute() != otherItem.getItemsPerMinute())
			return false;
		
		if(!this.getCraftingItems().equals(otherItem.getCraftingItems()))
			return false;
		
		return true;
	}
}