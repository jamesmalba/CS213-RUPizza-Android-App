package com.example.cs213_pizzaapp;

/**
 * PizzaFactory provides methods for implementation in different styles of pizza to create different flavors of pizza.
 * @author Alexis wilson, James Alba
 */
public interface PizzaFactory {
    /**
     * Method for creating a deluxe pizza
     * @return deluxe pizza object
     */
    Pizza createDeluxe();
    /**
     * Method for creating a meatzza pizza
     * @return meatzza pizza object
     */
    Pizza createMeatzza();
    /**
     * Method for creating a BBQ Chicken pizza
     * @return bbq chicken pizza object
     */
    Pizza createBBQChicken();
    /**
     * Method for creating a build your own pizza
     * @return build your own pizza object
     */
    Pizza createBuildYourOwn();
}
