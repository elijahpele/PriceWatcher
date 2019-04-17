package controllers;

import models.Item;

public class ItemManager extends Item {

	Item item = new Item();
	ItemManager list;

	public ItemManager() {
		item.name = this.name;
		item.url = this.url;
		item.currentPrice = this.currentPrice;
		item.dateAdded = this.dateAdded;
	}

	public Item addItem(String name, String url, Float price) {

		return list;

	}
	
	/**
	 * Removes an item according to the user.
	 * 
	 * @param noItem --> numbered items (indexes)
	 */
	public void removeItem(int numItem) {

	}

	public int size() {

		return 0;
	}

}
