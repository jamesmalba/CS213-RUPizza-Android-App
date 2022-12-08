package com.example.cs213_pizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PizzaOrderSelectedActivity extends AppCompatActivity {
    private Button selectedPizzaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);
        selectedPizzaName = findViewById(R.id.btn1);
        Intent intent = getIntent();
        selectedPizzaName.setText(intent.getStringExtra("ITEM"));
    }
}
