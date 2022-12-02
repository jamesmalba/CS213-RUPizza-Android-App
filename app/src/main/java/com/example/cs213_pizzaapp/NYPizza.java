package com.example.cs213_pizzaapp;

import java.util.ArrayList;

/**
 * NY Pizza creates the different flavors of NY Pizza and returns each pizza.
 * @author Alexis Wilson, James Alba
 */
public class NYPizza implements PizzaFactory{
    /**
     * Creates a deluxe NY Style pizza
     * @return pizza object of specified flavor
     */
    public Pizza createDeluxe() {
        Pizza deluxePizza = new Deluxe( Crust.BROOKLYN);
        return deluxePizza;
    }
    /**
     * Creates a BBQ Chicken NY Style pizza
     * @return pizza object of specified flavor
     */
    public Pizza createBBQChicken() {
        Pizza bbqPizza = new BBQChicken(Crust.THIN);
        return bbqPizza;
    }
    /**
     * Creates a Meatzza NY Style pizza
     * @return pizza object of specified flavor
     */
    public Pizza createMeatzza() {
        Pizza meatPizza = new Meatzza(Crust.HANDTOSSED);
        return meatPizza;
    }
    /**
     * Creates a BuildYourOwn NY Style pizza
     * @return pizza object of specified flavor
     */
    public Pizza createBuildYourOwn() {
        BuildYourOwn byoPizza = new BuildYourOwn(new ArrayList<>(), Crust.HANDTOSSED, Size.SMALL);
        return byoPizza;
    }
}
