package com.example.cs213_pizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

/**
 * CurrentOrderActivity. This will list all the info for the current order, and you can remove
 * pizzas, add to store orders, and clear the current order.
 * @author Alexis Wilson, James Alba
 */
public class CurrentOrderActivity extends AppCompatActivity {

    private ListView orderListView;
    private EditText total, subtotal, tax;
    private Order currentOrder;
    private StoreOrder storeOrders;
    private Pizza currentlySelectedPizza;
    private Button removeSelectedPizza, placePizzaOrder, clearPizzaOrder;

    public static final double SALES_TAX = 0.06625;
    public static final double SALES_TAX_MULTIPLIER = 1.06625;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        storeOrders = StoreOrder.getInstance();
        currentlySelectedPizza = null;
        currentOrder = Order.getInstance();


        orderListView = findViewById(R.id.current_order_listviews);
        clearPizzaOrder = findViewById(R.id.current_clear_order);
        orderListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);


        tax = findViewById(R.id.editTextNumberDecimal2);
        placePizzaOrder = findViewById(R.id.current_place_order_button);
        subtotal = findViewById(R.id.editTextNumberDecimal);
        total = findViewById(R.id.editTextNumberDecimal3);
        tax.setEnabled(false);
        subtotal.setEnabled(false);
        total.setEnabled(false);
        removeSelectedPizza = findViewById(R.id.current_removesel_button);
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
        clearPizzaOrder.setOnClickListener(v -> { this.clearOrder(); });
        placePizzaOrder.setOnClickListener(v -> { this.placeOrder(); });

        this.updateInfo();
        this.calculatePrices();
    }


    public void placeOrder() {
        currentOrder.addToStoreOrders(currentOrder.orderTotalPrice());

        Toast.makeText(getBaseContext(), R.string.order_successfully_added, Toast.LENGTH_SHORT).show();

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
