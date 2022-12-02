package com.example.cs213_pizzaapp;
import java.util.ArrayList;
/**
 * BuildYourOwn class handles all BuildYourOwn pizza specific operations in relation to setting the size price,
 * add/removing toppings, and calculating price.
 * @author Alexis Wilson, James Alba
 */
public class BuildYourOwn extends Pizza implements Customizable {
    private double BYOPrice;
    private static final double SMALL_PRICE = 8.99;
    private static final double MEDIUM_PRICE = 10.99;
    private static final double LARGE_PRICE = 12.99;
    /**
     * Constructor that sets toppings, crust, and size from superclass Pizza. Also, sets price with default size SMALL.
     * @param crust crust enum to create a pizza for different styles
     */
    public BuildYourOwn(ArrayList<Topping> toppings, Crust crust, Size size) {
        super(toppings, crust,size);
        this.BYOPrice  = setBYOPrice(Size.SMALL);
    }
    /**
     * Calculates the price of a Deluxe pizza given a size. Returns 0 if no size is picked.
     * @param size the chosen size of the pizza
     * @return price of pizza according to size
     */
    public double setBYOPrice(Size size) {
        if (size.equals(Size.SMALL))  {
           return SMALL_PRICE;
        }else if (size.equals(Size.MEDIUM)) {
            return MEDIUM_PRICE;
        }else if (size.equals(Size.LARGE)) {
            return LARGE_PRICE;
        }
        return 0;
    }

    /**
     * toppingsPrice() calculates the total price of all toppings based on the size of the toppings arraylist.
     * @return price of all toppings added
     */
    private double toppingsPrice() {
        double total = 0;
        int counter = 0;
        while (counter < getToppings().size()) {
            total += 1.59;
            counter++;
        }
        return total;
    }

    /**
     * Adds a topping to the pizza.
     * @param obj object to be added to the pizza
     * @return returns true if object is of class Topping. Otherwise, return false.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Topping) {
            Topping top = (Topping)obj;
            getToppings().add(top);
            return true;
        }
        return false;
    }
    /**
     * Removes a topping to the pizza.
     * @param obj object to be removed from the pizza
     * @return returns true if object is of class Topping. Otherwise, return false.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Topping) {
            Topping removedTop = (Topping) obj;
            getToppings().remove(removedTop);
            return true;
        }
        return false;
    }
    /**
     * Calculates the price of the pizza based off the chosen size and amount of toppings and returns the total price.
     * @return pizza price
     */
    @Override
    public double price() {
        double toppingsTotal = toppingsPrice();
        double sizeTotal = setBYOPrice(getSize());
        BYOPrice = (double) Math.round((toppingsTotal + sizeTotal) * 100) / 100;
        return Double.parseDouble(String.format("%.2f",BYOPrice));
    }
    /**
     * Returns a string of information of the Build Your Own pizza
     * @return string of Build Your Own pizza information
     */
    @Override
    public String toString() {
        return "Build your own (" + getCrust().getPizzaStyle() + " Style - " + getCrust().toString() + "), " + getStringToppings() + " " + getSize().toString() + " $" + price();
    }
}
