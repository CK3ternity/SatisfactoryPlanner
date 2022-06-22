package me.cketernity.satisfactoryplanner.items;

import java.util.ArrayList;
import java.util.List;

public class FactoryManager {
	private static FactoryManager factoryManager = new FactoryManager();
	private static List<Factory> factories;
	
	private FactoryManager() { init(); }
	
	private static void init() {
		factories = new ArrayList<>();
	}
	
	public Factory getFactory(Item item) {
		for(Factory factory: factories) {
			if(factory.getTargetItem().equals(item))
				return factory;
		}
		
		return createNewFactory(item);
	}
	private Factory createNewFactory(Item item) {
		Factory f = new Factory(item);
		factories.add(f);
		return f;
	}
	public void printFactories() {
		for(Factory factory: factories) {
			System.out.println(factory);
		}
	}
	public void printFactory(int i) {
		printFactory(factories.get(i));
	}
	public void printFactory(Item i) {
		for(Factory factory: factories) {
			if(factory.getTargetItem().equals(i)) {
				printFactory(factory);
				return;
			}
		}
	}
	public void printFactory(String i) {
		for(Factory factory: factories) {
			if(factory.getTargetItem().getName().equals(i)) {
				printFactory(factory);
				return;
			}
		}
	}
	private void printFactory(Factory f) {
		System.out.println(f);
	}
	
	public static FactoryManager getFactoryManager() { return factoryManager; }
	List<Factory> getFactories() { return factories; }
}
