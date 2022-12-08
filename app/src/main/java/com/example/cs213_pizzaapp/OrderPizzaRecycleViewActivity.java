package com.example.cs213_pizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderPizzaRecycleViewActivity extends AppCompatActivity {

    private ArrayList<Item> items = new ArrayList<>();
    private int [] itemImages = {R.drawable.ny_byo, R.drawable.ny_bbq, R.drawable.ny_deluxe,
            R.drawable.ny_meatzza, R.drawable.chicago_style_pizza, R.drawable.chicago_bbq, R.drawable.chicago_deluxe,
            R.drawable.chicago_meatzza};

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems();
        ItemsAdapter adapter = new ItemsAdapter(this, items); //create the adapter
        rcview.setAdapter(adapter);

        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], itemImages[i]));
        }
    }

}
