package com.example.cs213_pizzaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public ObservableList getAllToppings() {
        ObservableList<String> items = FXCollections.observableArrayList ();
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
