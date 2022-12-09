package com.example.cs213_pizzaapp;

import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
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

    /**
     * Creates the view and sets all the content and settings of the current order view to the proper fields
     * @param savedInstanceState instance state of the view
     */
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

    /**
     * Fills in appropriate data to be presented to the user. Also sets up proper listeners for all elements of the view
     */
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
        placePizzaOrder.setOnClickListener(v -> { this.placeOrderPrompt(); });

        this.updateInfo();
        this.calculatePrices();
    }

    /**
     * Adds the current order to the store orders, pushes toast messages and sets up new intent on the placing of an order
     */
    public void placeOrder() {
        Order addOrder = currentOrder;
        addOrder.addToStoreOrders(currentOrder.orderTotalPrice());
        currentOrder = new Order();

        Toast.makeText(getBaseContext(), R.string.order_successfully_added, Toast.LENGTH_SHORT).show();

        Intent gotoMainActivity = new Intent(this, MainActivity.class);
        startActivity(gotoMainActivity);
    }



    /**
     * Removes order from listview and updates all relevant information in view
     */
    public void clearOrder() {
        currentOrder.getOrder().clear();
        this.updateInfo();
        this.calculatePrices();
    }

    /**
     * Removes selected pizza and updates all relevant information in view
     */
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

    public void placeOrderPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CurrentOrderActivity.this);
        builder.setMessage(R.string.place_order_question);
        builder.setTitle(R.string.place);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.yes, (DialogInterface.OnClickListener) (dialog, which) -> {
            placeOrder();
        });
        builder.setNegativeButton(R.string.no, (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
