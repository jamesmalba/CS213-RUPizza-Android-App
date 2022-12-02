package com.example.cs213_pizzaapp;

/**
 * Crust enum class stores all types of crusts baked at RUPizza as well as their associated pizza style.
 * @author Alexis Wilson, James Alba
 */
public enum Crust {
    BROOKLYN("NEWYORK"),
    THIN("NEWYORK"),
    HANDTOSSED("NEWYORK"),
    DEEPDISH("CHICAGO"),
    PAN("CHICAGO"),
    STUFFED("CHICAGO");

    private final String pizzaStyle;

    /**
     * Constructor that creates an enum with a given pizza style.
     * @param pizzaStyle type of pizza style associated with crust
     */
    Crust(String pizzaStyle) {
        this.pizzaStyle = pizzaStyle;
    }
    /**
     * Gets the pizza style of an enum object
     * @return pizza style of crust
     */
    public String getPizzaStyle() {
        return pizzaStyle;
    }
}
