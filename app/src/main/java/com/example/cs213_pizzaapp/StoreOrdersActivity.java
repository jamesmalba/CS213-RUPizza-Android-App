package com.example.cs213_pizzaapp;


import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * StoreOrdersActivity. This will list all the orders in store order, and you can view orders and
 * remove orders.
 * @author Alexis Wilson, James Alba
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private ListView storeOrdersListView;
    private Button removeSelectedPizzaOrder;
    private Order currentSelectedOrder;

    /**
     * On Create life cycle method for our Activity
     * @param savedInstanceState The saved instance bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.); //STORE ORDERS ACTIVITY NAME
        currentSelectedOrder = null;
        storeOrdersListView = (ListView) findViewById(R.id.); //STORE ORDERS LIST VIEW ID
        removeSelectedPizzaOrder = (Button) findViewById(R.id.); //REMOVE ORDER BUTTON ID
        storeOrdersListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        storeOrdersListView.setSelector(R.color.);
    }

    /**
     * On Start life cycle method for our Activity
     */
    @Override
    protected void onStart() {
        super.onStart();


        buildOrderList();


        removeSelectedPizzaOrder.setEnabled(false);

        storeOrdersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Find which order it is from string
                String selectedItemString = (String) storeOrdersListView.getItemAtPosition(position);
                for(Order order : StoreOrder.getInstance().getStoreOrderList()) {
                    // we just want to compare the first item in the new line split, since that will be the order string
                    String[] componentsOfOrder = order.toString().split("\n");
                    if(order.toString().equals(componentsOfOrder[0])) {
                        // This is the same order!
                        currentSelectedOrder = order;
                        removeSelectedPizzaOrder.setEnabled(true);
                        return;
                    }
                }
            }
        });

        // Remove selected order listener
        removeSelectedPizzaOrder.setOnClickListener(v -> {
            this.removeSelectedOrder();
        });
    }


    private void removeSelectedOrder() {
        System.out.println(this.currentSelectedOrder);
        // Remove order, reset currently selected order, reset order ids, and rebuild order list
        StoreOrder.getInstance().remove(currentSelectedOrder);
        currentSelectedOrder = null;
        buildOrderList();

        // Show toast
        Toast.makeText(getBaseContext(), R.string.successfully_removed_order, Toast.LENGTH_SHORT).show();

        // Disable remove selected order button
        removeSelectedPizzaOrder.setEnabled(false);
    }


    private void buildOrderList() {
        ArrayList<Order> orders = StoreOrder.getInstance().getStoreOrderList();
        ArrayList<String> parsedOrderContentItems = new ArrayList<>();
        // Build a card for each order
        for(Order order : orders) {
            String orderTitle = order.toString() + "\n";

            // Add each menu item to the string
            String pizzas = "";
            for(Pizza item : order.getOrderList()) {
                pizzas += item.toString() + "\n";
            }

            // Build new card
            parsedOrderContentItems.add(orderTitle + pizzas);
        }

        // set list view content
        storeOrdersListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, parsedOrderContentItems));
    }

}
