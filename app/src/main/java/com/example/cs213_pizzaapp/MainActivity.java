package com.example.cs213_pizzaapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.cs213_pizzaapp.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

/**
 *
 * @author Alexis Wilson, James Alba
 */
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private Button currentOrderButton, storeOrdersButton, orderPizzaButton;

    private int uniqueOrderNumber = 1;
    protected static StoreOrder storeOrders = new StoreOrder();
    protected static Order totalOrder = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderPizzaButton = findViewById(R.id.order_pizza_button);
        currentOrderButton = findViewById(R.id.MainCurrentOrderButton);
        storeOrdersButton = findViewById(R.id.MainStoreOrderButton);


        // Add on click listener for order donut button
        orderPizzaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchOrderPizzaRecycleView(v);
            }
        });

        // Add on click listener for current order button
        currentOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchCurrentOrder();
            }
        });

        // Add on click listener for store orders button
        storeOrdersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchStoreOrders();
            }
        });
    }

    /**
     * Method to launch ordering pizza screen
     * @param view current view of the image button
     */
    public void launchOrderPizzaRecycleView(View view) {
        Intent openOrderPizza = new Intent(MainActivity.this, OrderPizzaRecycleViewActivity.class);
        startActivity(openOrderPizza);
    }

    /**
     * Method to launch current order screen
     */
    void launchCurrentOrder() {
        Intent openCurrentOrders = new Intent(MainActivity.this, CurrentOrderActivity.class);
        startActivity(openCurrentOrders);
    }

    /**
     * Method to launch the store orders screen
     */
    void launchStoreOrders() {
        Intent openStoreOrders = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(openStoreOrders);
    }


}