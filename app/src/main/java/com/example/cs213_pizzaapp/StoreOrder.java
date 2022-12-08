package com.example.cs213_pizzaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * StoreOrder class contains all the information needed to handle store orders containing an observable list of order
 * and the functions that help support actions for maintaining an order such as adding and removing orders from the
 * store order.
 * @author Alexis Wilson, James Alba
 */
public class StoreOrder implements Customizable {
    private final ArrayList<Order> orderList;
    private static StoreOrder instance = null;

    /**
     * Constructor to create an order object list containing an observable list for all the orders.
     */
    public StoreOrder() {
        orderList = new ArrayList<>();
    }

    /**
     * Adds an order to the store order list.
     * @param obj object to be added to an object of the implemented class
     * @return returns true if order added. Otherwise, returns false.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Order order) {
            orderList.add(order);
            return true;
        }
        return false;
    }

    /**
     * Removes an order from the store order list.
     * @param obj object to be removed to an object of the implemented class
     * @return returns true if order removed. Otherwise, returns false.
     */
    public boolean remove(Object obj) {
        if (obj instanceof Order order) {
            orderList.remove(order);
            return true;
        }
        return false;
    }

    /**
     * Method that returns an observable list containing the list of all the orders in the current store order.
     * @return Observable list of orders containing all the orders in the current store order.
     */
    public ArrayList<Order> getStoreOrderList() {
        return this.orderList;
    }

    /**
     * Method that returns the order object that contains the same order number as the one being searched in store order
     * list.
     * @param orderNumber an integer representing the order number that is to be found.
     * @return Order object representing the order that contains the order number provided by user.
     */
    public Order getOrder(int orderNumber) {
        return orderList.get(orderNumber);
    }

    /**
     * Method that searches for an order that contains the same id provided as a param. Returns null if not found.
     * @param orderID integer representing the order id being looked for
     * @return Order object representing the id that contains the order number.
     */
    public Order getID(int orderID) {
        for(Order order: orderList) {
            if(order.getOrderNumber() == orderID) return order;
        }
        return null;
    }

    /**
     * Method that exports all the orders in store order object into a text file. Returns a boolean if successful or not.
     * @param file a File object that is used to write upon.
     * @return Boolean representing if the operation was successful or not.
     */
    public boolean export(File file) {
        try {
            FileWriter writeOrder = new FileWriter(file);
            for (Order order : orderList) {
                writeOrder.write(order.toString());
                writeOrder.write("\n");
            }
            writeOrder.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Method that returns the size of the order list in the store order.
     * @return Integer representing the size of the orders contained in store order.
     */
    public int getSize() {
        return orderList.size();
    }

    public static StoreOrder getInstance() {
        if(instance == null) instance = new StoreOrder();
        return instance;
    }
}
