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
        setContentView(R.layout.activity_store_order); //STORE ORDERS ACTIVITY NAME
        currentSelectedOrder = null;
        storeOrdersListView = (ListView) findViewById(R.id.store_order_orderlistview); //STORE ORDERS LIST VIEW ID
        removeSelectedPizzaOrder = (Button) findViewById(R.id.store_remove_order_button); //REMOVE ORDER BUTTON ID
        storeOrdersListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
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

                String selectedItemString = (String) storeOrdersListView.getItemAtPosition(position);
                for(Order order : StoreOrder.getInstance().getStoreOrderList()) {

                    String[] componentsOfOrder = order.toString().split("\n");
                    if(order.toString().equals(componentsOfOrder[0])) {

                        currentSelectedOrder = order;
                        removeSelectedPizzaOrder.setEnabled(true);
                        return;
                    }
                }
            }
        });


        removeSelectedPizzaOrder.setOnClickListener(v -> {
            this.removeSelectedOrder();
        });
    }


    private void removeSelectedOrder() {
        StoreOrder.getInstance().remove(currentSelectedOrder);
        currentSelectedOrder = null;
        buildOrderList();

        Toast.makeText(getBaseContext(), R.string.removed_order_success, Toast.LENGTH_SHORT).show();
        removeSelectedPizzaOrder.setEnabled(false);
    }


    private void buildOrderList() {
        ArrayList<Order> orders = StoreOrder.getInstance().getStoreOrderList();
        ArrayList<String> parsedOrderContentItems = new ArrayList<>();
        for(Order order : orders) {
            String orderTitle = order.toString() + "\n";

            String pizzas = "";
            for(Pizza item : order.getOrderList()) {
                pizzas += item.toString() + "\n";
            }
            pizzas += "Order Total: $" + order.orderTotalPrice() + "\n";
            parsedOrderContentItems.add(orderTitle + pizzas);

        }

        storeOrdersListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, parsedOrderContentItems));
    }

}
