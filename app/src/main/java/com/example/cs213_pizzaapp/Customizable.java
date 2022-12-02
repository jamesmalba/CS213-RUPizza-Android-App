package com.example.cs213_pizzaapp;

/**
 * Customizable interface provides a framework for classes who implement it to add/remove orders, pizzas, or toppings.
 * @author Alexis Wilson, James Alba
 */
public interface Customizable {
    /**
     * Used to add an object
     * @param obj object to be added to an object of the implemented class
     * @return returns true if added. Otherwise, returns false.
     */
    boolean add(Object obj);
    /**
     * Used to remove an object
     * @param obj object to be removed to an object of the implemented class
     * @return returns true if removed. Otherwise, returns false.
     */
    boolean remove(Object obj);
}
