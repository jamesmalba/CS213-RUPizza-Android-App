package com.example.cs213_pizzaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Order class contains all the information needed to handle orders containing the pizza classes and the functions
 * that help support actions for maintaining an order such as adding and removing pizzas from an order.
 * @author Alexis Wilson, James Alba
 */
public class Order implements Customizable {

    public static final double SALES_TAX = 0.06625;

    private ObservableList<Pizza> orderListView;

    private int orderNumber;

    /**
     * Constructor to create a pizza object containing an observable list for all the pizzas.
     */
    public Order() { orderListView = FXCollections.observableArrayList();}

    /**
     * Adds a pizza to the order list.
     * @param obj object to be added to an object of the implemented class
     * @return returns true if pizza added. Otherwise, returns false.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Pizza item) {
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
        if (obj instanceof Pizza item) {
            orderListView.remove(item);
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
    public ObservableList<Pizza> getOrder() {
        return orderListView;
    }

    /**
     * A setter method for the order number for the specific order object.
     * @param orderNumber an integer representing the order number that is to be set for the specific object.
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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
    public ObservableList<Pizza> getOrderList() {
        return this.orderListView;
    }
}
