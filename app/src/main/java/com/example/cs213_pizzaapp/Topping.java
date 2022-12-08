package com.example.cs213_pizzaapp;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Topping class stores all 13 toppings offered at RUPizza, as well as functions to return as a string or get all Toppings.
 * @author Alexis Wilson, James Alba
 */
public enum Topping {
    SAUSAGE,
    PEPPERONI,
    GREEN_PEPPER,
    ONION,
    MUSHROOM,
    BBQ_CHICKEN,
    PROVOLONE,
    CHEDDAR,
    BEEF,
    PINEAPPLE,
    BLACKOLIVES,
    SPINACH,
    BACON,
    HAM;

    /**
     * Returns all Toppings of the class in an Observable String arraylist
     * @return observable list containing all toppings as strings
     */
    public ArrayList getAllToppings() {
        ArrayList<String> items = new ArrayList<>();
        for(Topping i : Topping.values()) {
            items.add(i.toString());
        }
        return items;
    }

    /**
     * Returns the name of the topping object as a string
     * @return name of topping
     */
    @Override
    public String toString() {
        return name();
    }
}
