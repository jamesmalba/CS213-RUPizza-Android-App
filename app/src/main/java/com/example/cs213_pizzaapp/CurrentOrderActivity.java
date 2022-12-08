package com.example.cs213_pizzaapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;

/**
 * CurrentOrderActivity. This will list all the info for the current order, and you can remove
 * pizzas, add to store orders, and clear the current order.
 * @author Alexis Wilson, James Alba
 */
public class CurrentOrderActivity extends AppCompatActivity {

    private ListView orderListView;
    private TextInputEditText total, subtotal, tax;
    private Order currentOrder;
    private StoreOrder storeOrders = MainActivity.storeOrders;
    private Pizza currentlySelectedPizza;
    private Button removeSelectedPizza, placePizzaOrder;

    public static final double SALES_TAX = 0.06625;
    public static final double SALES_TAX_MULTIPLIER = 1.06625;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.); //CURRENT ORDER ACTIVITY HERE!!!!!!!!!!!!!!!!
        this.setTitle(R.string.); //TITLE HERE

        currentlySelectedPizza = null;
        currentOrder = Order.getInstance();

        orderListView = findViewById(R.id.); //IDNAME FOR LIST VIEW HERE
        orderListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        orderListView.setSelector(R.color.);

        //SETTING COST INFORMATION
        tax = findViewById(R.id.salestax);
        subtotal = findViewById(R.id.subtotal);
        total = findViewById(R.id.total);
        tax.setEnabled(false);
        subtotal.setEnabled(false);
        total.setEnabled(false);
        removeSelectedPizza = findViewById(R.is.); //ID FOR REMOVE PIZZA HERE
    }

    @Override
    protected void onStart() {
        super.onStart();

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentlySelectedPizza = (Pizza) orderListView.getItemAtPosition(position);
                removeSelectedPizza.setEnabled(true);
            }
        });

        removeSelectedPizza.setOnClickListener(v -> { this.removePizza(); });

        placePizzaOrder.setOnClickListener(v -> { this.placeOrder(); });

        // Update UI and recompute price
        this.updateInfo();
        this.calculatePrices();
    }


    public void placeOrder() {
        // finalize the store order, which adds it to StoreOrders
        currentOrder.addToStoreOrders(this.total);

        // Show toast
        Toast.makeText(getBaseContext(), R.string.successfully_placed_order, Toast.LENGTH_SHORT).show();

        // Navigate back
        Intent gotoMainActivity = new Intent(this, MainActivity.class);
        startActivity(gotoMainActivity);
    }

    public void clearOrder() {
        currentOrder.getOrder().clear();
        this.updateInfo();
        this.calculatePrices();
    }

    public void removePizza() {
        currentOrder.remove(this.currentlySelectedPizza);
        this.updateInfo();
        this.calculatePrices();
    }

    /**
     * Private helper method that calculates the prices for the current order.
     */
    private void calculatePrices() {
        subtotal.setText(String.format("%.2f",  currentOrder.orderTotalPrice()));
        tax.setText(String.format("%.2f", currentOrder.orderTotalPrice() * SALES_TAX));
        total.setText(String.format("%.2f", currentOrder.orderTotalPrice() * SALES_TAX_MULTIPLIER));
    }

    /**
     * Private helper method that updates all the info to current.
     */
    private void updateInfo() {
        orderListView.clearChoices();
        orderListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentOrder.getOrderList()));
        this.removeSelectedPizza.setEnabled(false);

        placePizzaOrder.setEnabled(!this.currentOrder.getOrderList().isEmpty());
    }

}
