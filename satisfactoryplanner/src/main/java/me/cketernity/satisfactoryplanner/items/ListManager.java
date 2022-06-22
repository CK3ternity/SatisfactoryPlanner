package me.cketernity.satisfactoryplanner.items;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListHandler {
	private static ListHandler listHandler = new ListHandler();
	private static File file;
	private static File backup;
	private static List<Item> items;
	private static BufferedReader reader;
	private static BufferedWriter writer;
	
	private ListHandler() { init(); }
	
	private void init() {
		file = new File("src/main/resources/ItemList.txt");
		backup = new File("src/main/resources/Backup.txt");
		items = new ArrayList<>();
		backupCurrentFile();
		getFromFile();
	}
	
	public void addListToFile() {
		try {
			writer = new BufferedWriter(new FileWriter(file, false));
			
			for(Item item: items) {
				addToFile(item);
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void addToFile(Item item) throws IOException {
		String writeLine = String.format("%s,%d-", item.getName(), item.getItemsPerMinute());
		for(CraftingItem cItem: item.getCraftingItems()) {
			writeLine += String.format("%s,%d,", cItem.getName(), cItem.getItemsNeeded());
		}
		
		writeLine = writeLine.substring(0, writeLine.length() - 1);
		writeLine += "\n";
		
		writer.write(writeLine);
	}
	private static void getFromFile() {
		try {
			reader = new BufferedReader(new FileReader(file));
			
			String line;
			while((line = reader.readLine()) != null)
				items.add(createItem(line));
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static Item createItem(String line) {
		String[] separateBasicInfoFromList = line.split("-");
		String[] basicInfo = separateBasicInfoFromList[0].split(",");
		Item item = new Item(basicInfo[0], Integer.parseInt(basicInfo[1]));
		
		String[] listInfo = separateBasicInfoFromList[1].split(",");
		ArrayList<CraftingItem> itemList = new ArrayList<>();
		for(int i = 0; i < listInfo.length / 2; i++) {
			itemList.add(new CraftingItem(listInfo[0], Integer.parseInt(listInfo[1])));
		}
		
		item.setItems(itemList);
		
		return item;
	}
	
	private void backupCurrentFile() {
		String content = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			
			String line;
			while((line = reader.readLine()) != null) {
				content += line + "\n";
			}
			
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			writer = new BufferedWriter(new FileWriter(backup, false));
			
			writer.write(content);
			
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Item addItem(String name, int itemsMade) {
		Item i = new Item(name, itemsMade);
		items.add(i);
		return i;
	}
	
	public static ListHandler getListHandler() { return listHandler; }
	List<Item> getItems() { return items; }
}