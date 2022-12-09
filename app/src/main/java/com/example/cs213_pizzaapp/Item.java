package com.example.cs213_pizzaapp;

import java.io.Serializable;

/**
 * This class defines the data structure of an item to be displayed for the RecyclerView.
 * @author Alexis Wilson, James Alba
 */
public class Item implements Serializable {
	private String itemName;
	private int image;

	/**
	 * Parameterized constructor.
	 * @param itemName
	 * @param image
	 */
	public Item(String itemName, int image) {
		this.itemName = itemName;
		this.image = image;
	}

	/**
	 * Getter method that returns item name as a string.
	 * @return String of the item name of the item object.
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Getter method that returns the image of an item.
	 * @return the image of an item.
	 */
	public int getImage() {
		return image;
	}

}
