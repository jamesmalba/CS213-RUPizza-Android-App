package com.example.cs213_pizzaapp;
import java.util.ArrayList;

/**
 * Order class contains all the information needed to handle orders containing the pizza classes and the functions
 * that help support actions for maintaining an order such as adding and removing pizzas from an order.
 * @author Alexis Wilson, James Alba
 */
public class Order implements Customizable {

    public static final double SALES_TAX = 0.06625;

    private ArrayList<Pizza> orderListView;

    private int orderNumber;
    public static int uniqueOrderNumber = 0;

    private static Order instance;

    /**
     * Constructor to create a pizza object containing an observable list for all the pizzas.
     */
    public Order() {
        orderListView = new ArrayList<>();
        orderNumber = Order.uniqueOrderNumber++;
    }

    /**
     * Adds a pizza to the order list.
     * @param obj object to be added to an object of the implemented class
     * @return returns true if pizza added. Otherwise, returns false.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Pizza) {
            Pizza item = (Pizza)obj;
            orderListView.add(item);
            return true;
        }
        return false;
    }

    /**
     * Removes a pizza from the order list.
     * @param obj object to be removed to an object of the implemented class
     * @return returns true if pizza removed. Otherwise, returns false.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Pizza) {
            orderListView.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * Helper method that calculates the total cost of the order.
     * @return Double representing the total cost of the order.
     */
    public double orderTotalPrice() {
        double total = 0;
        for (Pizza item : orderListView) total += item.price();
        return total;
    }

    /**
     * Method that returns an observable list containing the list of all the pizzas in the current order.
     * @return Observable list of pizza containing all the pizzas in the current order.
     */
    public ArrayList<Pizza> getOrder() {
        return orderListView;
    }

    /**
     * A getter method for the order number for the specific order object.
     * @return Integer representing the order number.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Returns a string representation of the order object displaying all the information.
     * @return String representing the order object displaying the order number and all the pizzas in that order.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Order number: ").append(orderNumber).append(", Pizza(s) ordered: ");
        for (Pizza pizza : orderListView) {
            result.append(pizza.toString());
        }
        return result.toString();
    }

    /**
     * A getter method for the order list of pizzas for the specific order object.
     * @return Observable list representing all the pizzas contained in the order.
     */
    public ArrayList<Pizza> getOrderList() {
        return this.orderListView;
    }

    public static Order getInstance() {
        if(instance == null) instance = new Order();
        return instance;
    }

    public void addToStoreOrders(double price) {
        StoreOrder.getInstance().add(this);
        instance = null;
    }

    /**
     * This is a helper function that increases the order number in order to maintain uniqueness. This is used when an
     * order is added to the store orders and a new order number is needed.
     */
    public void addOrderNumber() {
        orderNumber++;
    }
}
