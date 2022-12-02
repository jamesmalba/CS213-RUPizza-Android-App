package com.example.cs213_pizzaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * BBQChicken class handles all BBQChicken pizza specific operations in relation to setting the size price and
 * calculating price.
 * @author Alexis Wilson, James Alba
 */
public class BBQChicken extends Pizza {
    private double BBQPrice;
    private static final double SMALL_PRICE = 13.99;
    private static final double MEDIUM_PRICE = 15.99;
    private static final double LARGE_PRICE = 17.99;
    private static final ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.BBQ_CHICKEN,
            Topping.GREEN_PEPPER, Topping.PROVOLONE, Topping.CHEDDAR));

    /**
     * Constructor that sets toppings, crust, and size from superclass Pizza. Also, sets price with default size SMALL.
     * @param crust crust enum to create a pizza for different styles
     */
    public BBQChicken(Crust crust) {
        super(toppings, crust, Size.SMALL);
        this.BBQPrice = setBBQPrice(Size.SMALL);
    }

    /**
     * Calculates the price of a BBQChicken pizza given a size. Returns 0 if no size is picked.
     * @param size the chosen size of the pizza
     * @return price of pizza according to size
     */
    public double setBBQPrice(Size size) {
        if (size.equals(Size.SMALL)) {
            return SMALL_PRICE;
        } else if (size.equals(Size.MEDIUM)) {
            return MEDIUM_PRICE;
        } else if (size.equals(Size.LARGE)) {
            return LARGE_PRICE;
        } return 0;
    }

    /**
     * Adds a topping to the pizza
     * @param obj object to add to pizza
     * @return returns boolean false, because pizza has preset toppings
     */
    @Override
    public boolean add(Object obj) {
        return false;
    }
    /**
     * Removes a topping to the pizza
     * @param obj object to remove to pizza
     * @return returns boolean false, because pizza has preset toppings
     */
    @Override
    public boolean remove(Object obj) {
        return false;
    }

    /**
     * Calculates the price of the pizza based off the chosen size and returns price.
     * @return pizza price
     */
    @Override
    public double price() {
       BBQPrice = setBBQPrice(getSize());
       return BBQPrice;
    }

    /**
     * Returns a string representation of the pizza object displaying all the information.
     * @return String representing the BBQChicken object displaying the name, toppings, and price.
     */
    @Override
    public String toString() {
        return "BBQ Chicken (" + getCrust().getPizzaStyle() + " Style - " + getCrust().toString() + "), " + getStringToppings() + " " + getSize().toString() + " $" + price();
    }
}
