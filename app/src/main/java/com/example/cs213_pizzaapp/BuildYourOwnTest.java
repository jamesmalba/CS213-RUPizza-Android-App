package com.example.cs213_pizzaapp;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A JUnit class that tests the price() method in BuildYourOwn class
 * @author Alexis Wilson, James Alba
 */
public class BuildYourOwnTest {
    @Test
    public void smallPizza() {
        //creates an instance of Pizza and sets size to medium
        Topping[] toppings = Topping.values();
        PizzaFactory p = new NYPizza();
        Pizza pizza = p.createBuildYourOwn();
        pizza.setSizeToSmall();
        //test 1: price() will output the correct price when zero toppings is added to a small pizza1
        double expectedOutput = 8.99;
        double actualOutput = pizza.price();// rounds to 2 decimals
        //System.out.println(actualOutput);
        assertEquals(expectedOutput, actualOutput, 0.00);
        //test 2: price() will output the correct price when one topping is added to a small pizza1
        pizza.add(toppings[0]);
        expectedOutput = 10.58;
        actualOutput = pizza.price();
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 3: price() will output the correct price when two toppings is added to a small pizza1
        pizza.add(toppings[1]);
        expectedOutput = 12.17;
        actualOutput = pizza.price();
        assertEquals(expectedOutput, actualOutput, 0.00);
        //test 4:  price() will output the correct price when three toppings is added to a small pizza1
        pizza.add(toppings[2]);
        expectedOutput = 13.76;
        actualOutput = pizza.price();
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 5: price() will output the correct price when four toppings is added to a small pizza1
        pizza.add(toppings[3]);
        expectedOutput = 15.35;
        actualOutput = pizza.price();
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 6: price() will output the correct price when five toppings is added to a small pizza1
        pizza.add(toppings[4]);
        expectedOutput = 16.94;
        actualOutput = pizza.price();
        assertEquals(expectedOutput, actualOutput, 0.00);
        //test 7: price() will output the correct price when six toppings is added to a small pizza1
        pizza.add(toppings[5]);
        expectedOutput = 18.53;
        actualOutput = pizza.price();
        assertEquals(expectedOutput, actualOutput, 0.00);
        //test 8: price() will work at the upper bound of a small pizza1 with 7 toppings
        pizza.add(toppings[6]);
        expectedOutput = 20.12;
        actualOutput = pizza.price();
        assertEquals(expectedOutput, actualOutput, 0.00);
    }

    @Test
    public void mediumPizza() {
        //creates an instance of Pizza and sets size to medium
        Topping[] toppings = Topping.values();
        PizzaFactory p = new ChicagoPizza();
        Pizza pizza = p.createBuildYourOwn();
        pizza.setSizeToMedium();

        //test 1: price() will output the correct price when zero toppings is added to a medium pizza
        double expectedOutput = 10.99;
        double actualOutput = (double) Math.round(pizza.price() * 100) / 100; // rounds to 2 decimals
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 2: price() will output the correct price when one topping is added to a medium pizza
        pizza.add(toppings[0]);
        expectedOutput = 12.58;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 3: price() will output the correct price when two toppings is added to a medium pizza
        pizza.add(toppings[1]);
        expectedOutput = 14.17;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 4:  price() will output the correct price when three toppings is added to a medium pizza
        pizza.add(toppings[2]);
        expectedOutput = 15.76;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 5: price() will output the correct price when four toppings is added to a medium pizza
        pizza.add(toppings[3]);
        expectedOutput = 17.35;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 6: price() will output the correct price when five toppings is added to a medium pizza
        pizza.add(toppings[4]);
        expectedOutput = 18.94;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 7: price() will output the correct price when six toppings is added to a medium pizza
        pizza.add(toppings[5]);
        expectedOutput = 20.53;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 8: price() will work at the upper bound of a medium pizza with 7 toppings
        pizza.add(toppings[6]);
        expectedOutput = 22.12;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);
    }

    @Test
    public void largePizza(){
        //creates an instance of Pizza and sets size to large
        Topping[] toppings = Topping.values();
        PizzaFactory p = new ChicagoPizza();
        Pizza pizza = p.createBuildYourOwn();
        pizza.setSizeToLarge();

        //test 1: price() will output the correct price when zero toppings is added to a large pizza
        double expectedOutput = 12.99;
        double actualOutput = (double) Math.round((pizza.price()) * 100) / 100; // rounds to 2 decimals
        assertEquals(expectedOutput, actualOutput, 0.00);
        //test 2: price() will output the correct price when one topping is added to a large pizza
        pizza.add(toppings[0]);
        expectedOutput = 14.58;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 3: price() will output the correct price when two toppings is added to a large pizza
        pizza.add(toppings[1]);
        expectedOutput = 16.17;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 4:  price() will output the correct price when three toppings is added to a large pizza
        pizza.add(toppings[2]);
        expectedOutput = 17.76;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 5: price() will output the correct price when four toppings is added to a large pizza
        pizza.add(toppings[3]);
        expectedOutput = 19.35;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 6: price() will output the correct price when five toppings is added to a large pizza
        pizza.add(toppings[4]);
        expectedOutput = 20.94;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 7: price() will output the correct price when six toppings is added to a large pizza
        pizza.add(toppings[5]);
        expectedOutput = 22.53;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);

        //test 8: price() will work at the upper bound of a large pizza with 7 toppings
        pizza.add(toppings[6]);
        expectedOutput = 24.12;
        actualOutput = (double) Math.round(pizza.price() * 100) / 100;
        assertEquals(expectedOutput, actualOutput, 0.00);
    }

}